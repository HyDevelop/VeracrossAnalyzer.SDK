package org.hydev.veracross.sdk;

import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

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
}
