package org.hydev.veracross.sdk;

import com.google.gson.reflect.TypeToken;
import org.hydev.veracross.sdk.model.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * HTTP Client for the veracross sdk (Currently, this SDK is for St.
 * John's only, because logging in directly to Veracross requires a
 * captcha verification).
 * <p>
 * Class created by the HyDEV Team on 2019-08-19!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2019-08-19 13:45
 */
public class VeracrossHttpClient extends GeneralHttpClient
{
    private static final String URL_BASE = "https://portals-app.veracross.com/sjp/";
    private static final String API_MESSAGES = "mailbox/messages";
    private static final String API_CALENDAR_EVENTS = "student/calendar/student/calendar_events";
    private static final String API_COURSE_ASSIGNMENTS = "student/enrollment/%s/assignments";
    private static final String API_COURSE_FEEDBACK = "student/enrollment/%s/feedback";
    private static final String API_DIRECTORY = "directory/entries.json";

    private static final String LEGACY_URL_BASE = "https://portals.veracross.com/sjp/";
    private static final String LEGACY_API_COURSE = "student/component/ClassListStudent/1308/load_data";

    /**
     * Login and save the session
     *
     * @param username Username
     * @param ssoToken SJP Single sign on token
     */
    public void loginSJP(String username, String ssoToken) throws IOException
    {
        // Post response
        postForm("https://portals.veracross.com/sjp/login?WCI=Login&WCE=Submit", null,
                        "username", username,
                        "token", ssoToken).close();
    }

    /**
     * Get the list of courses.
     *
     * @return List of courses.
     */
    public List<VeracrossCourse> getCourses() throws IOException
    {
        // Create result array
        List<VeracrossCourse> result = new ArrayList<>();

        // Get html
        String responseHtml = getBody(URL_BASE);

        // The old version website uses XHR to display course list, which
        // means there is an api for it. However, the new website shows
        // a generated static page for courses, which means information
        // can only be obtained from the html.
        //
        // More details in the JavaDoc comments for getWebsiteVersion().

        // Check version
        if (getWebsiteVersion().equals("portals"))
        {
            // Parse HTML, because for the new veracross, the course list
            // info are all in the html.
            return VeracrossHtmlParser.parseCourses(responseHtml);
        }
        else
        {
            // Access api url, because for the old veracross, the course
            // list is accessed with a XHR request.

        }
    }

    /**
     * Get the version of the website.
     *
     * Check if the website is the portals (old) version or the
     * portals-app (new) version. They are in very different formats.
     * The old version website accounts can access new version apis,
     * but the new version accounts cannot access the old version
     * apis. Also, if you try to access the old version website with
     * a new version account, you would get redirected back.
     *
     * TODO: Ask someone to test accessing the new website with an old account.
     *
     * New version: portals-app.veracross.com
     * Old version: portals.veracross.com
     *
     * @return Version ("portals-app" or "portals")
     */
    public String getWebsiteVersion() throws IOException
    {
        // Get the fully redirected url. Why this can be used to detect
        // the version is explained above.
        String url = getRedirectedUrl(URL_BASE);
        System.out.println(url);

        // Check domain name
        return url.contains("portals-app.veracross.com") ? "portals-app" :
                url.contains("portals.veracross.com") ? "portals" : "Error";
    }

    /**
     * Get the list of assignments from the start of the semester for the
     * specified course.
     *
     * @param courseId ID of the course
     * @return List of assignments
     */
    public VeracrossAssignments getAssignments(long courseId) throws IOException
    {
        String url = URL_BASE + String.format(API_COURSE_ASSIGNMENTS, courseId);
        return getJson(url, VeracrossAssignments.class);
    }

    /**
     * Get the list of messages from the specified starting index to the
     * latest.
     *
     * @param start Starting index.
     * @return List of messages
     */
    public List<VeracrossMessage> getMessages(long start) throws IOException
    {
        // Since I can't pass in list of a specific type as a type,
        // a TypeToken is used to generate the type.
        Type type = new TypeToken<List<VeracrossMessage>>(){}.getType();

        return getJson(URL_BASE + API_MESSAGES, type, "start", start);
    }

    /**
     * Get the list of calendar events in between two dates.
     *
     * @param begin Begin date.
     * @param end Ending date.
     * @return List of Calendar Events
     */
    public List<VeracrossCalendarEvent> getEvents(Date begin, Date end) throws IOException
    {
        return getJson(URL_BASE + API_CALENDAR_EVENTS, new TypeToken<List<VeracrossCalendarEvent>>(){}.getType()
                ,"begin_date", toVeracrossDate(begin)
                ,"end_date", toVeracrossDate(end));
    }

    /**
     * Get the list of calendar events in between two dates, represented
     * by offsets to today.
     *
     * @param begin Begin offset.
     * @param end Ending offset.
     * @return Calendar Events
     */
    public List<VeracrossCalendarEvent> getEvents(int begin, int end) throws IOException
    {
        return getEvents(getDate(begin), getDate(end));
    }

    /**
     * Convert Date object to Veracross parameter date format.
     *
     * @param date Date object
     * @return Date string.
     */
    private static String toVeracrossDate(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new SimpleDateFormat("MM/dd/yyyy").format(date);
    }

    /**
     * Get date with day offset.
     * Eg. getDate(-5) would return a Date from 5 days ago.
     *
     * @param dayOffset Day offset
     * @return Date
     */
    private static Date getDate(int dayOffset)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, dayOffset);
        return calendar.getTime();
    }

    /**
     * Get information of all the students in a directory.
     *
     * @return All students
     */
    public List<VeracrossStudent> getDirectoryStudents() throws IOException
    {
        return getJson(URL_BASE + API_DIRECTORY, new TypeToken<List<VeracrossStudent>>(){}.getType(),
                "directory", "student",
                "portal", "student",
                "refresh", 0);
    }

    /**
     * Get information of all the faculties in a directory.
     *
     * @return All faculties
     */
    public List<VeracrossFaculty> getDirectoryFaculties() throws IOException
    {
        return getJson(URL_BASE + API_DIRECTORY, new TypeToken<List<VeracrossFaculty>>(){}.getType(),
                "directory", "faculty",
                "portal", "student",
                "refresh", 0);
    }
}
