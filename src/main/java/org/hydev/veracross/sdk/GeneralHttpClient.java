package org.hydev.veracross.sdk;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
public class GeneralHttpClient
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


    protected HttpResponse postForm(String url, PreProcessor preProcessor, String... entity) throws IOException
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
    protected HttpResponse postForm(String url, PreProcessor preProcessor, UrlEncodedFormEntity entity) throws IOException
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
}
