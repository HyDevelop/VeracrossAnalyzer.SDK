package org.hydev.veracross.sdk;

import org.hydev.veracross.sdk.model.VeracrossAssignments;
import org.hydev.veracross.sdk.model.VeracrossCourse;

import java.io.IOException;
import java.util.ArrayList;
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

        // Parse HTML
        return VeracrossHtmlParser.parseCourses(responseHtml);
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
}
