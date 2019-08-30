package org.hydev.veracross.sdk;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains utility methods that parse the html pages on
 * Veracross into more useful information.
 * <p>
 * Class created by the HyDEV Team on 2019-08-29!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2019-08-29 18:47
 */
public class VeracrossHtmlParser
{
    private static final Pattern URL_NUMBER_PATTERN = Pattern.compile("(?<=/).[0-9]*(?=/)");
    
    /**
     * Find the first number in a url.
     *
     * @param url Url (eg. "/course/12345/website/")
     * @return The first number, -1 if not found.
     */
    private static long findNumberInUrl(String url)
    {
        url += "/";
        Matcher matcher = URL_NUMBER_PATTERN.matcher(url);

        if (matcher.find()) return Long.parseLong(matcher.group());
        return -1;
    }
}
