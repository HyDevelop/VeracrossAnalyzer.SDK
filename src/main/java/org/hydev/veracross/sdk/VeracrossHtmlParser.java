package org.hydev.veracross.sdk;

import org.hydev.veracross.sdk.model.VeracrossCourse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
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
     * Parse courses information from the html on the main page.
     *
     * @param mainPageHtml Html string on the main page.
     * @return A list of courses
     */
    public static List<VeracrossCourse> parseCourses(String mainPageHtml)
    {
        // Create result list.
        List<VeracrossCourse> result = new ArrayList<>();

        // Parse with Jsoup
        Document doc = Jsoup.parse(mainPageHtml);

        // Search for class list elements.
        Elements courseElements = doc.select(".class-list.clear > li");

        // Loops through each course to find detailed information.
        for (Element courseElement : courseElements)
        {
            // Find course name element and extract the course name.
            Element courseNameElement = courseElement.selectFirst(".class-name");
            String courseName = courseNameElement.html();

            // Find teacher name element and extract the teacher name.
            String teacherName = courseElement.selectFirst(".teacher-name").html();

            // Find course information link, and find the course id in it.
            long courseId = findNumberInUrl(courseNameElement.attr("href"));

            // Find assignments info link, and find the assignment id in it.
            long assignmentsId = findNumberInUrl(courseElement.selectFirst(".view-assignments").attr("href"));

            // Add to result.
            result.add(VeracrossCourse.builder().name(courseName).teacherName(teacherName)
                    .id(courseId).assignmentsId(assignmentsId).build());
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

        if (matcher.find()) return Long.parseLong(matcher.group());
        return -1;
    }
}
