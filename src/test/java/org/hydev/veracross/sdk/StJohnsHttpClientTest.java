package org.hydev.veracross.sdk;

import com.google.gson.Gson;
import org.hydev.veracross.sdk.model.VeracrossAssignments;
import org.hydev.veracross.sdk.model.VeracrossCalendarEvent;
import org.hydev.veracross.sdk.model.VeracrossCourse;
import org.hydev.veracross.sdk.model.VeracrossMessage;
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
class StJohnsHttpClientTest
{
    private StJohnsHttpClient stJohns;
    private VeracrossHttpClient veracross;

    private String username;
    private String password;

    @BeforeAll
    void init()
    {
        stJohns = new StJohnsHttpClient();

        Scanner in = new Scanner(System.in);

        // Ask for username
        System.out.println("Veracross Username?");
        username = in.nextLine();

        // Ask for passwd
        System.out.println("Veracross Password?");
        password = in.nextLine();
    }

    @Test
    @Order(1)
    void login() throws IOException
    {
        // Login St Johns
        stJohns.login(username, password);
    }

    @Test
    @Order(2)
    void veracross() throws IOException
    {
        // Login Veracross
        veracross = stJohns.veracrossLoginSSO();
    }

    @Test
    @Order(3)
    void veracrossCourses() throws IOException
    {
        // Get courses
        List<VeracrossCourse> courses = veracross.getCourses();
        log(courses);
    }

    @Test
    @Order(4)
    void veracrossAssignments() throws IOException
    {
        // Get assignments of the course at index 1 of the list.
        VeracrossAssignments assignments = veracross.getAssignments(10934147);
        log(assignments);
    }

    @Test
    @Order(5)
    void veracrossMessages() throws IOException
    {
        // Get messages starting at index 0.
        List<VeracrossMessage> messages = veracross.getMessages(0);
        log(messages);
    }

    @Test
    @Order(6)
    void veracrossEvents1() throws IOException
    {
        // Get calendar events from 5 days ago to 5 days later.
        List<VeracrossCalendarEvent> events = veracross.getEvents(-5, 5);
        log(events);
    }

    @Test
    @Order(7)
    void veracrossEvents2() throws IOException
    {
        // Get calendar events from 5 days ago to 5 days later.
        List<VeracrossCalendarEvent> events = veracross.getEvents(new Date(), new Date());
        log(events);
    }

    private static void log(Object object)
    {
        System.out.println(String.valueOf(new Gson().toJson(object)));
        System.out.println("--------======= LINE OF SEPARATION =======--------");
    }
}
