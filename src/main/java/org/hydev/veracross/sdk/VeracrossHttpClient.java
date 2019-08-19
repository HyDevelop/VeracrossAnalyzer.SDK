package org.hydev.veracross.sdk;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * HTTP Client for the veracross sdk.
 * <p>
 * Class created by the HyDEV Team on 2019-08-19!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2019-08-19 13:45
 */
public class VeracrossHttpClient
{
    private final String schoolCode;

    private CloseableHttpClient httpClient;

    /**
     * Construct a veracross http client.
     *
     * @param schoolCode Veracross school code (Eg. "sjp")
     */
    public VeracrossHttpClient(String schoolCode)
    {
        this.schoolCode = schoolCode;

        initHttpClient();
    }

    /**
     * Initialize the apache http client
     */
    private void initHttpClient()
    {
        // Create a http client builder
        HttpClientBuilder builder = HttpClients.custom();

        // Set the user agent
        builder.setUserAgent(VeracrossConstants.USER_AGENT);

        // Build http client
        httpClient = builder.build();
    }

    /**
     * Login and save the session
     */
    public void login(String username, String password)
    {
        // Combine the login url
        String loginUrl = "https://accounts.veracross.com/" + schoolCode + "/portals/login";

        // Create param pairs
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));

        // Create a post request.
        HttpPost post = new HttpPost(loginUrl);

        
    }
}
