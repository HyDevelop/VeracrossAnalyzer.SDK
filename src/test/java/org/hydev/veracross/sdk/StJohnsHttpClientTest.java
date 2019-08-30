package org.hydev.veracross.sdk;

import com.google.gson.Gson;
import org.hydev.veracross.sdk.model.VeracrossCourse;
import org.junit.jupiter.api.*;

import java.io.IOException;
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
        stJohns.login(username, password);
    }

    @Test
    @Order(2)
    void veracross() throws IOException
    {
        veracross = stJohns.veracrossLoginSSO();
    }

    @Test
    @Order(3)
    void veracrossCourses() throws IOException
    {
        List<VeracrossCourse> courses = veracross.getCourses();
        System.out.println(new Gson().toJson(courses));
    }
}
