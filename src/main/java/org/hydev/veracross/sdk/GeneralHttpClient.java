package org.hydev.veracross.sdk;

import com.google.gson.Gson;
import lombok.Getter;
import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.hydev.veracross.sdk.utils.UrlUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.apache.http.protocol.HttpCoreContext.HTTP_REQUEST;
import static org.apache.http.protocol.HttpCoreContext.HTTP_TARGET_HOST;

/**
 * TODO: Write a description for this class!
 * <p>
 * Class created by the HyDEV Team on 2019-08-28!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2019-08-28 17:12
 */
@Getter
public abstract class GeneralHttpClient
{
    protected CloseableHttpClient httpClient;
    protected CookieStore cookies;
    protected HttpClientContext context;

    public GeneralHttpClient()
    {
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

        // Init http client context
        context = HttpClientContext.create();

        // Init cookie store
        cookies = new BasicCookieStore();
        context.setCookieStore(cookies);
        builder.setDefaultCookieStore(cookies);

        // Build http client
        httpClient = builder.build();
    }

    /**
     * Replace the current cookies with a new list of cookies.
     *
     * @param cookies Cookies
     */
    public void restoreCookies(List<Cookie> cookies)
    {
        // Clear current cookies
        this.cookies.clear();

        // Add the new cookies in
        for (Cookie cookie : cookies)
        {
            this.cookies.addCookie(cookie);
        }
    }
    
    protected CloseableHttpResponse postForm(String url, PreProcessor preProcessor, String... entity)
            throws IOException
    {
        try
        {
            // Map param pairs
            List<NameValuePair> params = new ArrayList<>();
            for (int i = 0; i < entity.length; i += 2)
            {
                params.add(new BasicNameValuePair(entity[i], entity[i + 1]));
            }

            // Create form entity
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");

            return postForm(url, preProcessor, formEntity);
        }
        catch (UnsupportedEncodingException e)
        {
            // Never happenes
            throw new RuntimeException(e);
        }
    }

    /**
     * Post a form
     *
     * @param url Request URL.
     * @param preProcessor Pre-processor
     * @param entity Form entity.
     * @return HTTP Response
     */
    protected CloseableHttpResponse postForm(String url, PreProcessor preProcessor, UrlEncodedFormEntity entity)
            throws IOException
    {
        // Create a post request.
        HttpPost request = new HttpPost(url);

        // Add headers
        request.addHeader("content-type", "application/x-www-form-urlencoded");

        // Create from entity from param pairs
        request.setEntity(entity);

        // PreProcess
        if (preProcessor != null)
        {
            request = (HttpPost) preProcessor.process(request);
        }

        // Send request
        return httpClient.execute(request);
    }

    /**
     * This interface is used to preprocess the request before it is sent.
     */
    protected interface PreProcessor
    {
        HttpRequest process(HttpRequest request);
    }

    /**
     * Get the response body from a response
     *
     * @param response Http response
     * @return String
     */
    protected String getBody(HttpResponse response) throws IOException
    {
        return EntityUtils.toString(response.getEntity(), "UTF-8");
    }

    /**
     * Send a GET request
     *
     * @param url Request URL
     * @return Response
     */
    protected CloseableHttpResponse get(String url) throws IOException
    {
        // Create request
        HttpGet get = new HttpGet(url);

        // Send request
        return httpClient.execute(get);
    }

    /**
     * Get the final url after all the redirection
     * Credit to: https://stackoverflow.com/a/1457173
     *
     * @param url The requested url
     * @return The final url
     */
    protected String getRedirectedUrl(String url) throws IOException
    {
        // Create request and temporary context
        HttpGet httpget = new HttpGet(url);
        HttpContext context = new BasicHttpContext();
        CloseableHttpResponse response = httpClient.execute(httpget, context);

        // Handle error
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
        {
            throw new IOException(response.getStatusLine().toString());
        }

        // Get URI
        HttpUriRequest currentReq = (HttpUriRequest) context.getAttribute(HTTP_REQUEST);
        HttpHost currentHost = (HttpHost)  context.getAttribute(HTTP_TARGET_HOST);
        String finalUrl = (currentReq.getURI().isAbsolute())
                ? currentReq.getURI().toString()
                : (currentHost.toURI() + currentReq.getURI());

        // Close connection
        response.close();

        // Return
        return finalUrl;
    }

    /**
     * Send a GET request and return the response body.
     *
     * @param url Request URL
     * @return Response
     */
    protected String getBody(String url) throws IOException
    {
        // Get response
        CloseableHttpResponse response = get(url);

        // Read response body
        String body = getBody(response);

        // Close response
        response.close();

        // Return result
        return body;
    }

    /**
     * Send a GET request to a url and parse the result as a JSON object.
     *
     * @param url Request URL
     * @param type JSON Pojo Type
     * @param params URL Parameters
     * @param <T> Return type (Same as the type param)
     * @return The requested json pojo object.
     */
    protected <T> T getJson(String url, Type type, Object... params) throws IOException
    {
        // Get json string
        String jsonString = getBody(UrlUtils.buildURL(url, params));

        // Parse to object
        return new Gson().fromJson(jsonString, type);
    }

    /**
     * Send a GET request to a url and parse the result as a JSON object.
     *
     * @param url Request URL
     * @param type JSON Pojo Type
     * @param params URL Parameters
     * @param <T> Return type (Same as the type param)
     * @return The requested json pojo object.
     */
    protected <T> T getJson(String url, Class<T> type, Object... params) throws IOException
    {
        // Get json string
        String jsonString = getBody(UrlUtils.buildURL(url, params));

        // Parse to object
        return new Gson().fromJson(jsonString, type);
    }
}
