package org.hydev.veracross.sdk;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

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
    public void login(String username, String password)
    {
        // Keep the username
        this.username = username;

        try
        {
            // Post request
            HttpResponse response = postForm("https://www.stjohnsprep.org/userlogin.cfm?do=login&p=114", null,
                            "username", username,
                            "password", password,
                            "submit", "login");

            int status = response.getStatusLine().getStatusCode();
            String responseText = getResponseBody(response);
        }
        catch (IOException e)
        {
            // There is not much to do.
            throw new RuntimeException("Veracross SDK: Login Failed", e);
        }
    }

    /**
     * Get the veracross single sign on token.
     *
     * @return SSO Token string
     */
    public String getVeracrossSSOToken()
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
                throw new RuntimeException("Veracross SDK: Unsupported response: " + responseText);
            }

            // Return result
            return matcher.group();
        }
        catch (IOException e)
        {
            // There is not much to do.
            throw new RuntimeException("Veracross SDK: Failed to obtain SSO token", e);
        }
    }

    /**
     * Login to Veracross via the single sign on interface.
     *
     * @return Veracross http client
     */
    public VeracrossHttpClient veracrossLoginSSO()
    {
        // Create veracross client
        VeracrossHttpClient client = new VeracrossHttpClient();

        // Obtain token and login with it
        client.loginSJP(username, getVeracrossSSOToken());

        return client;
    }
}
