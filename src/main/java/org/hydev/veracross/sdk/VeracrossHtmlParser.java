package org.hydev.veracross.sdk;

import org.hydev.veracross.sdk.model.VeraCourseGrading;
import org.hydev.veracross.sdk.model.VeraCourseGrading.GradingMethod;
import org.hydev.veracross.sdk.model.VeraPerson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.util.regex.Pattern.*;
import static org.hydev.veracross.sdk.VeracrossConstants.VERSION;

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
    private static final Pattern URL_NUMBER_PATTERN = compile("(?<=/).[0-9]*(?=/)");
    private static final Pattern V2_USERNAME_PATTERN = compile("(?<=Portals\\.currentUser\\.username = \").*(?=@)");
    private static final Pattern V2_USERNAME_PATTERN_FALLBACK = compile("(?<=Portals\\.currentUser\\.username = \").*(?=\";)");
    private static final Pattern V2_PERSON_PK_PATTERN = compile("(?<=Portals\\.currentUser\\.personPk = ).*(?=;)");
    private static final Pattern V3_USERNAME_PATTERN = compile("(?<=username: \").*(?=\",)");
    private static final Pattern V3_PERSON_PK_PATTERN = compile("(?<=user_id: ).*(?=,)");

    /**
     * Get basic person info
     *
     * @param pageHtml Html
     * @param legacy Is legacy html page or not
     * @return Person info
     */
    public static VeraPerson parsePerson(String pageHtml, boolean legacy)
    {
        String username = "";
        int personPk = -1;

        if (!legacy)
        {
            // Get username and person pk
            Matcher matcher = V2_USERNAME_PATTERN.matcher(pageHtml);
            if (matcher.find()) username = matcher.group(0);
            if (username.equals(""))
            {
                matcher = V2_USERNAME_PATTERN_FALLBACK.matcher(pageHtml);
                if (matcher.find()) username = matcher.group(0);
            }
            matcher = V2_PERSON_PK_PATTERN.matcher(pageHtml);
            if (matcher.find()) personPk = parseInt(matcher.group(0));
        }
        else
        {
            // Get username and person pk
            Matcher matcher = V3_USERNAME_PATTERN.matcher(pageHtml);
            if (matcher.find()) username = matcher.group(0).toLowerCase();
            matcher = V3_PERSON_PK_PATTERN.matcher(pageHtml);
            if (matcher.find()) personPk = parseInt(matcher.group(0));
        }

        return new VeraPerson(username, personPk);
    }

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

        if (matcher.find()) return parseLong(matcher.group());
        return -1;
    }

    /**
     * Parse grading scheme information
     *
     * @param gradingHtml Grading page html
     * @return Grading scheme information
     */
    public static VeraCourseGrading parseGrading(String gradingHtml)
    {
        // Create weighting map
        Map<String, Double> weightingMap = new HashMap<>();

        // If it says it's graded by assignment points, we are done.
        if (gradingHtml.contains("Assignment Points"))
            return new VeraCourseGrading(GradingMethod.TOTAL_MEAN, null);

        // I have no idea how this works yet
        if (gradingHtml.contains("Assignment Type and Points"))
            return new VeraCourseGrading(GradingMethod.TOTAL_MEAN, null);

        // Parse document
        Document doc = Jsoup.parse(gradingHtml);

        // Find table element
        Element table = doc.selectFirst("#assignment_type_summary .data_table tbody");

        // There are no table
        if (table == null || table.html().contains("No records found"))
            return new VeraCourseGrading(GradingMethod.NOT_GRADED, null);

        // Loop through table rows
        for (Element tr : table.select("tr"))
        {
            // Find name (Eg. "Homework" or "Quiz")
            Element name = tr.selectFirst("td.description.text strong");

            // Find weight
            Element weight = tr.selectFirst("td.weight div span.label");

            // Add it to the weighting map
            weightingMap.put(name.html(), Double.parseDouble(weight.html().replace("%", "")) / 100d);
        }

        return new VeraCourseGrading(GradingMethod.PERCENT_TYPE, weightingMap);
    }

    private static final Pattern CSRF_PATTERN = compile("(?<=<meta name=\"csrf-token\" content=\").*(?=\")");

    /**
     * Find the csrf token in any page
     *
     * @param html HTML page
     * @return CSRF token
     */
    public static String findCsrfToken(String html)
    {
        Matcher matcher = CSRF_PATTERN.matcher(html);
        if (matcher.find()) return matcher.group();
        throw new RuntimeException("CSRF Token Not Found! " +
                "Please check for update, VXAnalyzer.Server v" + VERSION + " might be deprecated.");
    }
}
