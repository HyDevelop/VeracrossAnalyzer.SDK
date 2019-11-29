package org.hydev.veracross.sdk;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.hydev.veracross.sdk.exceptions.VeracrossException;
import org.hydev.veracross.sdk.model.*;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class tests {@code VeracrossHttpClient} class.
 * <p>
 * Class created by the HyDEV Team on 2019-08-19!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2019-08-19 14:29
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StJohnsHttpClientTest
{
    private StJohnsHttpClient stJohns;
    private VeracrossHttpClient veracross;

    /*
    @Test @Order(1)
    void login() throws IOException, VeracrossException
    {
        // Login St Johns
        stJohns.login(username, password);
    }

    @Test @Order(2)
    void veracross() throws IOException, VeracrossException
    {
        // Login Veracross
        veracross = stJohns.veracrossLoginSSO();
    }
     */

    @Test @Order(1)
    void veracross() throws IOException, VeracrossException
    {
        // Login Veracross
        veracross = new VeracrossHttpClient();
        veracross.restoreCookies(new Gson().fromJson("-- HIDDEN --",
                new TypeToken<List<BasicClientCookie>>(){}.getType()));
    }

    private VeracrossCourses courses;

    @Test @Order(3)
    void veracrossCourses() throws IOException
    {
        // Get courses
        VeracrossCourses courses = veracross.getCourses();
        log(courses);

        // Convert to st johns course
        List<StJohnsCourse> stJohnsCourses = new ArrayList<>();
        courses.forEach(course -> stJohnsCourses.add(new StJohnsCourse(course)));
        log(stJohnsCourses);

        // Get ready to test assignments
        this.courses = courses;
    }

    @Test @Order(4)
    void veracrossAssignments() throws IOException
    {
        // Get assignments of the course at index 1 of the list.
        VeracrossAssignments assignments = veracross.getAssignments(courses.get(0).getAssignmentsId());
        log(assignments);
    }

    @Test @Order(5)
    void veracrossMessages() throws IOException
    {
        // Get messages starting at index 0.
        List<VeracrossMessage> messages = veracross.getMessages(0);
        log(messages);
    }

    @Test @Order(6)
    void veracrossEvents1() throws IOException
    {
        // Get calendar events from 5 days ago to 5 days later.
        List<VeracrossCalendarEvent> events = veracross.getEvents(-5, 5);
        log(events);
    }

    @Test @Order(7)
    void veracrossEvents2() throws IOException
    {
        // Get calendar events in between two dates.
        List<VeracrossCalendarEvent> events = veracross.getEvents(new Date(), new Date());
        log(events);
    }

    @Test @Order(8)
    void veracrossDirectoryStudents() throws IOException
    {
        // Get all student info
        List<VeracrossStudent> students = veracross.getDirectoryStudents();

        // Filter them, select the students in Yoga course
        students.removeIf(student -> !student.getAllClasses().contains("33070"));

        // Print results
        log(students);
    }

    @Test @Order(9)
    void veracrossDirectoryFaculty() throws IOException
    {
        // Get all faculty info
        List<VeracrossFaculty> faculties = veracross.getDirectoryFaculties();

        // Filter them, select the faculty in Information Services department
        faculties.removeIf(faculty -> !faculty.getFacultyType().contains("Information Services"));

        // Print results
        log(faculties);
    }

    @Test @Order(10)
    void testGrading() throws IOException
    {
        // Get grading info
        List<VeracrossCourseGrading> gradings = veracross.getGradings(courses);
        log(gradings);
    }

    private String csrf = "";

    @Test @Order(11)
    void testGetCsrf() throws IOException
    {
        csrf = veracross.getCsrfToken();
    }

    @Test @Order(12)
    void testMarkAsRead() throws IOException
    {
        boolean success = veracross.markAssignmentAsRead(csrf, 5677615);
        assert success;
    }

    @Test @Order(13)
    void testMarkAsReadMultiple() throws IOException
    {
        boolean success = veracross.markAssignmentAsRead(5677615, 5677615, 5677615);
        assert success;
    }

    List<Cookie> savedCookies;

    @Test @Order(50)
    void saveCookies()
    {
        // Save cookies
        savedCookies = veracross.getCookies().getCookies();
        log(savedCookies);
    }

    @Test @Order(51)
    void restoreCookies() throws IOException
    {
        // Create new instance
        VeracrossHttpClient veracross = new VeracrossHttpClient();
        veracross.restoreCookies(savedCookies);

        // Test it
        VeracrossCourses courses = veracross.getCourses();
        log(courses);
    }

    private static void log(Object object)
    {
        System.out.println(String.valueOf(new Gson().toJson(object)));
        System.out.println("--------======= LINE OF SEPARATION =======--------");
    }
}
