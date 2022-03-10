package org.hydev.veracross.sdk;

import com.google.gson.Gson;
import org.apache.http.cookie.Cookie;
import org.hydev.veracross.sdk.exceptions.VeracrossException;
import org.hydev.veracross.sdk.model.*;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
class VeraTest
{
    private VeracrossHttpClient veracross;

    @Test @BeforeAll
    void init() throws IOException
    {
        // Login Veracross
        veracross = new VeracrossHttpClient();
        veracross.restoreSession(new Scanner(VeraTest.class.getResourceAsStream("/session.txt"), "UTF-8").useDelimiter("\\A").next().split("\n")[0]);
    }

    private CourseListV3 courses;

    @Test
    void sjpCsrf() throws IOException
    {
        StJohnsHttpClient sj = new StJohnsHttpClient();
        String csrf = sj.getCsrf();
        System.out.println(csrf);
    }

    @Test @Order(3)
    void veracrossCourses() throws IOException
    {
        // Get courses
        courses = veracross.getCourses();
        System.out.println(courses.getPersonPk());
        System.out.println(courses.getUsername());
        log(courses);
    }

    @Test @Order(4)
    void veracrossAssignments() throws IOException
    {
        // Get assignments of the course at index 1 of the list.
        VeraAssignments assignments = veracross.getAssignments(courses.getCourses().get(0).getEnrollmentPk());
        log(assignments);
    }

    @Test @Order(5)
    void veracrossMessages() throws IOException
    {
        // Get messages starting at index 0.
        List<VeraMessage> messages = veracross.getMessages(0);
        log(messages);
    }

    @Test @Order(6)
    void veracrossEvents1() throws IOException
    {
        // Get calendar events from 5 days ago to 5 days later.
        List<EventV3> events = veracross.getEvents(-5, 5);
        log(events);
    }

    @Test @Order(7)
    void veracrossEvents2() throws IOException
    {
        // Get calendar events in between two dates.
        List<EventV3> events = veracross.getEvents(new Date(), new Date());
        log(events);
    }

    @Test @Order(8)
    void veracrossDirectoryStudents() throws IOException
    {
        // Get all student info
        List<VeraStudent> students = veracross.getDirectoryStudents();

        // Filter them, select ony seniors
        students.removeIf(student -> student.getCurrentGradeId() != 12);

        // Print results
        log(students);
    }

    @Test @Order(9)
    void veracrossDirectoryFaculty() throws IOException
    {
        // Get all faculty info
        List<VeraFaculty> faculties = veracross.getDirectoryFaculties();

        // Filter them, select the faculty in Information Services department
        faculties.removeIf(faculty -> !faculty.getFacultyType().contains("Information Services"));

        // Print results
        log(faculties);
    }

    @Test @Order(10)
    void testGrading() throws IOException
    {
        // Get grading info
        for (CourseV3 course : courses.getCourses())
        {
            List<VeraCourseGrading> gradings = veracross.getGradings(course);
            log(gradings);
        }
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
        CourseListV3 courses = veracross.getCourses();
        log(courses);
    }

    private static void log(Object object)
    {
        System.out.println(new Gson().toJson(object));
        System.out.println("--------======= LINE OF SEPARATION =======--------");
    }
}
