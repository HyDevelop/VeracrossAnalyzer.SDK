package org.hydev.veracross.sdk;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.hydev.veracross.sdk.exceptions.VeracrossException;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * HTTP Client for the veracross sdk specific to SJP.
 * <p>
 * Class created by the HyDEV Team on 2019-08-19!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2019-08-19 13:45
 */
public class StJohnsHttpClient extends GeneralHttpClient
{
    private static final Pattern SSO_USERNAME_PATTERN = Pattern.compile("(?<=name=\"username\" value=\").*(?=\">)");
    private static final Pattern SSO_TOKEN_PATTERN = Pattern.compile("(?<=name=\"token\" value=\").*(?=\">)");

    private String username;

    /**
     * Login and save the session
     */
    public void login(String username, String password) throws IOException, VeracrossException
    {
        // Keep the username
        this.username = username;

        // Post request
        CloseableHttpResponse response = postForm("https://www.stjohnsprep.org/userlogin.cfm?do=login&p=114", null,
                        "username", username,
                        "password", password,
                        "submit", "login");

        // Get response
        int status = response.getStatusLine().getStatusCode();
        String responseText = getBody(response);

        // Close it
        response.close();

        // Unknown http problem
        if (status != 200 && status != 301 && status != 302)
        {
            throw new VeracrossException("Login Failed: HTTP Connection Problem, status code: " + status);
        }

        // Invalid password
        if (responseText.contains("<div class=\"fsLoginMessage\">Invalid username or password. Please try again.</div><br>"))
        {
            throw new VeracrossException("Login Failed: Invalid username or password.");
        }
    }

    /**
     * Get the veracross single sign on token.
     *
     * @return SSO Token string
     */
    public String getVeracrossSSOToken() throws VeracrossException
    {
        // Create request
        HttpGet get = new HttpGet("https://www.stjohnsprep.org/fs/sso/?type=Veracross-SSO");

        try
        {
            // Send it
            CloseableHttpResponse response = httpClient.execute(get);

            // Get text
            String responseText = EntityUtils.toString(response.getEntity(), "UTF-8");

            // Match text
            Matcher matcher = SSO_TOKEN_PATTERN.matcher(responseText);
            if (!matcher.find())
            {
                // There's not much to do on runtime.
                throw new RuntimeException("SSO Login Failed: Unsupported response: " + responseText);
            }

            // Return result
            return matcher.group();
        }
        catch (IOException e)
        {
            // There is not much to do.
            throw new VeracrossException("SSO Login Failed: Failed to obtain token", e);
        }
    }

    /**
     * Login to Veracross via the single sign on interface.
     *
     * @return Veracross http client
     */
    public VeracrossHttpClient veracrossLoginSSO() throws IOException, VeracrossException
    {
        // Create veracross client
        VeracrossHttpClient client = new VeracrossHttpClient();

        // Obtain token and login with it
        client.loginSJP(username, getVeracrossSSOToken());

        return client;
    }
}
