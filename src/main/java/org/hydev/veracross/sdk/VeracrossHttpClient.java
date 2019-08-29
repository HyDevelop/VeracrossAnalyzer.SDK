package org.hydev.veracross.sdk;

import org.apache.http.HttpResponse;

import java.io.IOException;

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
        HttpResponse response = postForm("https://portals.veracross.com/sjp/login?WCI=Login&WCE=Submit", null,
                        "username", username,
                        "token", ssoToken);
    }

        {
            // There is not much to do.
            throw new RuntimeException("Veracross SDK: Login Failed", e);
        }
    }
}
