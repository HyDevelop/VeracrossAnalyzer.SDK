package org.hydev.veracross.sdk.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
     * @param params Param key value pairs (Eg. ["key", "value"])
     * @return URL params
     */
    public static String buildParams(Object... params)
    {
        // Null / empty cases
        if (params == null || params.length < 2) return "";

        try
        {
            // Create result variable
            StringBuilder result = new StringBuilder("?");

            for (int i = 0; i < params.length; i += 2)
            {
                // Append "&" if it is not the first
                if (i != 0) result.append("&");

                // Append the param pair
                result.append(params[i]).append("=").append(params[i + 1]);
            }

            // Return result
            return URLEncoder.encode(result.toString(), "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            // This should never happen.
            // If it does, there's nothing to do with it.
            throw new RuntimeException(e);
        }
    }
}
