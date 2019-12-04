package org.hydev.veracross.sdk;

import org.hydev.veracross.sdk.model.VeracrossCourse;
import org.hydev.veracross.sdk.model.VeracrossCourse.VeracrossCourseBuilder;
import org.hydev.veracross.sdk.model.VeracrossCourseGrading;
import org.hydev.veracross.sdk.model.VeracrossCourseGrading.GradingMethod;
import org.hydev.veracross.sdk.model.VeracrossCourses;
import org.hydev.veracross.sdk.model.VeracrossPerson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
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
    private static final Pattern URL_NUMBER_PATTERN = Pattern.compile("(?<=/).[0-9]*(?=/)");
    private static final Pattern USERNAME_PATTERN =
            Pattern.compile("(?<=Portals\\.currentUser\\.username = \").*(?=\";)");
    private static final Pattern PERSON_PK_PATTERN =
            Pattern.compile("(?<=Portals\\.currentUser\\.personPk = ).*(?=;)");
    private static final Pattern USERNAME_PATTERN_LEGACY =
            Pattern.compile("(?<=username: \").*(?=\",)");
    private static final Pattern PERSON_PK_PATTERN_LEGACY =
            Pattern.compile("(?<=user_id: ).*(?=,)");

    /**
     * Get basic person info
     *
     * @param pageHtml Html
     * @param legacy Is legacy html page or not
     * @return Person info
     */
    public static VeracrossPerson parsePerson(String pageHtml, boolean legacy)
    {
        String username = "";
        int personPk = -1;

        if (!legacy)
        {
            // Get username and person pk
            Matcher matcher = USERNAME_PATTERN.matcher(pageHtml);
            if (matcher.find()) username = matcher.group(0);
            matcher = PERSON_PK_PATTERN.matcher(pageHtml);
            if (matcher.find()) personPk = parseInt(matcher.group(0));
        }
        else
        {
            // Get username and person pk
            Matcher matcher = USERNAME_PATTERN_LEGACY.matcher(pageHtml);
            if (matcher.find()) username = matcher.group(0);
            matcher = USERNAME_PATTERN_LEGACY.matcher(pageHtml);
            if (matcher.find()) personPk = parseInt(matcher.group(0));
        }

        return new VeracrossPerson(username, personPk);
    }

    /**
     * Parse courses information from the html on the main page.
     *
     * @param mainPageHtml The html string of the main page.
     * @return A list of courses
     */
    public static VeracrossCourses parseCourses(String mainPageHtml)
    {
        // Create result list.
        VeracrossCourses result = new VeracrossCourses();

        // Get username and person pk
        Matcher matcher = USERNAME_PATTERN.matcher(mainPageHtml);
        if (matcher.find()) result.setUsernameEmail(matcher.group(0));
        matcher = PERSON_PK_PATTERN.matcher(mainPageHtml);
        if (matcher.find()) result.setPersonPk(parseLong(matcher.group(0)));

        // Parse with Jsoup
        Document doc = Jsoup.parse(mainPageHtml);

        // Search for class list elements.
        Elements courseElements = doc.select(".class-list.clear > li");

        // Loops through each course to find detailed information.
        for (Element courseElement : courseElements)
        {
            // Create builder
            VeracrossCourseBuilder builder = VeracrossCourse.builder();

            // Find course name element and extract the course name.
            Element courseNameElement = courseElement.selectFirst(".class-name");
            builder.name(courseNameElement.html());

            // Find teacher name element and extract the teacher name.
            builder.teacherName(courseElement.selectFirst(".teacher-name").html());

            // Find course information link, and find the course id in it.
            builder.id(findNumberInUrl(courseNameElement.attr("href")));

            // Find assignments info link, and find the assignment id in it.
            builder.assignmentsId(findNumberInUrl(courseElement.selectFirst(".view-assignments").attr("href")));

            // Find grades
            if (courseElement.select(".calculated-grade").size() != 0)
            {
                builder.letterGrade(courseElement.selectFirst(".letter-grade").html().replace(" ", ""));
                builder.numericGrade(parseDouble(courseElement.selectFirst(".numeric-grade").html().replace("%", "")));
            }

            // Find status
            builder.status(courseElement.attr("data-status"));

            // Add to result
            result.add(builder.build());
        }

        return result;
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
    public static VeracrossCourseGrading parseGrading(String gradingHtml)
    {
        // Create weighting map
        Map<String, Double> weightingMap = new HashMap<>();

        // If it says it's graded by assignment points, we are done.
        if (gradingHtml.contains("Assignment Points"))
        {
            return new VeracrossCourseGrading(GradingMethod.TOTAL_MEAN, null);
        }

        // Parse document
        Document doc = Jsoup.parse(gradingHtml);

        // Find table element
        Element table = doc.selectFirst("#assignment_type_summary .data_table tbody");

        // There are no table
        if (table == null) return new VeracrossCourseGrading(GradingMethod.NOT_GRADED, null);

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

        return new VeracrossCourseGrading(GradingMethod.PERCENT_TYPE, weightingMap);
    }

    private static final Pattern CSRF_PATTERN =
            Pattern.compile("(?<=<meta name=\"csrf-token\" content=\").*(?=\")");

    /**
     * Find the csrf token in any page
     *
     * @param html HTML page
     * @return CSRF token
     */
    public static String findCsrfToken(String html)
    {
        Matcher matcher = CSRF_PATTERN.matcher(html);
        if (matcher.find())
        {
            return matcher.group();
        }
        throw new RuntimeException("CSRF Token Not Found! " +
                "Please check for update, VXAnalyzer.Server v" + VERSION + " might be deprecated.");
    }
}
