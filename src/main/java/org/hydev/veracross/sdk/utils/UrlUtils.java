package org.hydev.veracross.sdk.utils;

import org.apache.http.client.utils.URIBuilder;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 * TODO: Write a description for this class!
 * <p>
 * Class created by the HyDEV Team on 2019-08-30!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2019-08-30 09:32
 */
public class UrlUtils
{
    /**
     * Build url params with a map of params.
     *
     * @param base Base url
     * @param params Param key value pairs (Eg. ["key", "value"])
     * @return URL params
     */
    public static String buildURL(String base, Object... params) throws MalformedURLException
    {
        // Null / empty cases
        if (params == null || params.length < 2) return "";

        try
        {
            // Create url builder
            URIBuilder builder = new URIBuilder(base);

            // Add params
            for (int i = 0; i < params.length; i += 2)
            {
                builder.addParameter(params[i].toString(), params[i + 1].toString());
            }

            // Return the result.
            return builder.build().toURL().toString();
        }
        catch (URISyntaxException e)
        {
            throw new MalformedURLException("URI Syntax error: " + e.getMessage());
        }
    }
}
