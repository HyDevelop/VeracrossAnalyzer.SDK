package org.hydev.veracross.sdk;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
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
    /**
     * Login and save the session
     */
    public void login(String username, String password)
    {
        // Combine the login url
        String loginUrl = "https://www.stjohnsprep.org/userlogin.cfm?do=login&p=114";

        // Create param pairs
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("submit", "login"));

        try
        {
            // Create a post request.
            HttpPost request = new HttpPost(loginUrl);

            // Add headers
            request.addHeader("accept", "text/html");
            request.addHeader("accept-language", "en-US");
            request.addHeader("content-type", "application/x-www-form-urlencoded");

            // Create from entity from param pairs
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");
            request.setEntity(formEntity);

            // Send request
            CloseableHttpResponse response = httpClient.execute(request);
            int status = response.getStatusLine().getStatusCode();
            String responseText = EntityUtils.toString(response.getEntity(), "UTF-8");

            // Debug output TODO: Remove this
            System.out.println("Status: " + status);
            System.out.println("Text: " + responseText);

        }
        catch (UnsupportedEncodingException e)
        {
            // This would never happen during runtime if the JVM encoding
            // is correctly configured to UTF-8.
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            // There is not much to do.
            throw new RuntimeException("Veracross SDK: Login Failed", e);
        }
    }

}
