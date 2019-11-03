package org.hydev.veracross.sdk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hydev.veracross.sdk.model.StJohnsCourseDescription;

import java.util.List;

/**
 * TODO: Write a description for this class!
 * <p>
 * Class created by the HyDEV Team on 2019-11-02!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2019-11-02 21:48
 */
public class StJohnsCoursesDescriptionTest
{
    public static void main(String[] args)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        StJohnsHttpClient client = new StJohnsHttpClient();
        List<StJohnsCourseDescription> list = client.getCourseDescriptions();
        System.out.println(gson.toJson(list));
    }
}
