package org.hydev.veracross.sdk;

import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.hydev.veracross.sdk.model.*;
import org.hydev.veracross.sdk.model.VeracrossLegacyCourse.CourseListContainer;

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
    public static String SCHOOL_CODE = "sjp";

    public static String URL_BASE = "https://portals-app.veracross.com/" + SCHOOL_CODE + "/";
    public static final String API_MESSAGES = "mailbox/messages";
    public static final String API_CALENDAR_EVENTS = "student/calendar/student/calendar_events";
    public static final String API_COURSE_ASSIGNMENTS = "student/enrollment/%s/assignments";
    public static final String API_COURSE_FEEDBACK = "student/enrollment/%s/feedback";
    public static final String API_DIRECTORY = "directory/entries.json";
    public static final String API_ASSIGNMENT_MARK_READ = "enrollment/mark_notification_read";

    public static String WEB_GRADING = "https://documents.veracross.com/" + SCHOOL_CODE + "/grade_detail/%s?grading_period=1&key=_";
    public static final String WEB_CSRF_TOKEN = "student/directory"; // Because it has the smallest html

    public static String LEGACY_URL_BASE = "https://portals.veracross.com/" + SCHOOL_CODE + "/";
    public static final String LEGACY_API_COURSE = "student/component/ClassListStudent/1308/load_data";

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
    public VeracrossCourses getCourses() throws IOException
    {
        // Get html
        String responseHtml = getBody(URL_BASE);

        // The old version website uses XHR to display course list, which
        // means there is an api for it. However, the new website shows
        // a generated static page for courses, which means information
        // can only be obtained from the html.
        //
        // More details in the JavaDoc comments for getWebsiteVersion().

        // Check version
        if (getWebsiteVersion().equals("portals-app"))
        {
            // Parse HTML, because for the new veracross, the course list
            // info are all in the html.
            return VeracrossHtmlParser.parseCourses(responseHtml);
        }
        else
        {
            // Access api url, because for the old veracross, the course
            // list is accessed with a XHR request.
            return getJson(LEGACY_URL_BASE + LEGACY_API_COURSE, CourseListContainer.class).convert();
        }
    }

    /**
     * Get the list of courses with grading
     *
     * @return Courses
     */
    public VeracrossCourses getCoursesWithGrading() throws IOException
    {
        return loadGradings(getCourses());
    }

    /**
     * Check if logged in.
     *
     * @return Logged in or not
     * @throws IOException Exception
     */
    public boolean validateLogin() throws IOException
    {
        return !getRedirectedUrl(URL_BASE).contains("accounts.veracross.com");
    }

    /**
     * Get the version of the website.
     *
     * Check if the website is the portals (old) version or the
     * portals-app (new) version. They are in very different formats.
     * The old version website accounts can access the new version apis,
     * but the new version accounts cannot access the old version
     * apis. Also, if you try to access the old version website with
     * a new version account, you would get redirected back.
     *
     * New version: portals-app.veracross.com
     * Old version: portals.veracross.com
     *
     * @return Version ("portals-app" or "portals")
     */
    public String getWebsiteVersion() throws IOException
    {
        // Get the fully redirected url. Why this can be used to detect
        // the reason is explained above.
        String url = getRedirectedUrl(URL_BASE);

        // Check domain name
        String result =  url.contains("portals-app.veracross.com") ? "portals-app" :
                url.contains("portals.veracross.com") ? "portals" : null;

        // Check error
        if (result == null)
        {
            throw new RuntimeException("Veracross Error: Unsupported Version! Redirected URL: " + url);
        }

        return result;
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

    /**
     * Get the grading scheme information
     *
     * @param assignmentsId Assignments ID of a course
     * @return Grading scheme info for a course
     */
    public VeracrossCourseGrading getGrading(long assignmentsId) throws IOException
    {
        // Get HTML
        String html = getBody(String.format(WEB_GRADING, assignmentsId));

        // Parse it
        return VeracrossHtmlParser.parseGrading(html);
    }

    /**
     * Get the grading scheme information
     *
     * @param course Course
     * @return Grading scheme info of the course
     */
    public VeracrossCourseGrading getGrading(VeracrossCourse course) throws IOException
    {
        return getGrading(course.getAssignmentsId());
    }

    /**
     * Get the grading scheme information of a list of courses
     *
     * @param courses Courses
     * @return Grading scheme info list
     */
    public List<VeracrossCourseGrading> getGradings(VeracrossCourses courses) throws IOException
    {
        List<VeracrossCourseGrading> result = new ArrayList<>();
        for (VeracrossCourse course : courses)
        {
            result.add(getGrading(course));
        }
        return result;
    }

    /**
     * Load grading scheme information to courses
     *
     * @param courses Courses
     * @return Courses with gradings (but it actually modifies the object)
     */
    public VeracrossCourses loadGradings(VeracrossCourses courses) throws IOException
    {
        for (VeracrossCourse course : courses)
        {
            course.setGrading(getGrading(course));
        }
        return courses;
    }

    /**
     * Get a csrf token.
     * Learn more about csrf tokens here:
     * https://stackoverflow.com/q/5207160/7346633
     *
     * @return csrf token
     */
    public String getCsrfToken() throws IOException
    {
        // Fetch html body
        String html = getBody(URL_BASE + WEB_CSRF_TOKEN);

        // Get token
        return VeracrossHtmlParser.findCsrfToken(html);
    }

    /**
     * Mark assignment as read
     *
     * @param csrf CSRF Token
     * @param scoreId Score id in Assignment
     * @return Success or not
     */
    public boolean markAssignmentAsRead(String csrf, long scoreId) throws IOException
    {
        // Fetch request
        CloseableHttpResponse response = postForm(URL_BASE + API_ASSIGNMENT_MARK_READ, (request) ->
        {
            request.addHeader("accept", "*/*");
            request.addHeader("accept-encoding", "gzip, deflate, br");
            request.addHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
            request.addHeader("dnt", "1");
            request.addHeader("origin", "https://portals-app.veracross.com");
            request.addHeader("sec-fetch-mode", "cors");
            request.addHeader("sec-fetch-site", "same-origin");
            request.addHeader("x-requested-with", "XMLHttpRequest");
            request.addHeader("x-csrf-token", csrf);
            return request;
        }, "class_assignment_person_pk", scoreId + "");

        // Check for success status
        boolean success = response.getStatusLine().getStatusCode() == 200;

        // Close response
        response.close();

        // Return success status
        return success;
    }

    /**
     * Get csrf token and mark assignment as read.
     *
     * @param scoreIds Score ids in Assignment
     * @return Success or not
     */
    public boolean markAssignmentAsRead(long... scoreIds) throws IOException
    {
        // Get token
        String token = getCsrfToken();

        // Loop through all scores
        boolean allSuccess = true;
        for (long scoreId : scoreIds)
        {
            if (!markAssignmentAsRead(token, scoreId))
            {
                allSuccess = false;
            }
        }
        return allSuccess;
    }
}
