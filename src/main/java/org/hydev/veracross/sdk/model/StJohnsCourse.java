package org.hydev.veracross.sdk.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

/**
 * This data model class represents a course in St. John's.
 * <p>
 * Class created by the HyDEV Team on 2019-09-03!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2019-09-03 16:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StJohnsCourse extends VeracrossCourse
{
    private static final String[][] LEVEL_ENDING_ALIAS =
    {
            {"H", " h", " honors"},
            {"A", " a", " acc", " accelerated"},
            {"CP", " c", " college prep"}
    };

    private static final Map<String, Float> LEVEL_SCALE_MAP;

    static
    {
        // Initialize level scale map
        LEVEL_SCALE_MAP = new HashMap<>();
        LEVEL_SCALE_MAP.put("AP", 1f);
        LEVEL_SCALE_MAP.put("H", 0.75f);
        LEVEL_SCALE_MAP.put("A", 0.5f);
        LEVEL_SCALE_MAP.put("CP", 0.25f);
    }

    // Level of the course
    private String level;

    // How many points to scale up in the GPA from 4.0.
    private double scaleUp;

    /**
     * Try to convert a general Veracross course into St. John's course.
     *
     * @param course Veracross course
     */
    public StJohnsCourse(VeracrossCourse course)
    {
        super(course);

        // Detect level
        String name = course.getName().toLowerCase();
        if (name.startsWith("ap "))
        {
            level = "AP";
            scaleUp = 1;
        }
        else if (course.getName().endsWith("H"))
        {
            level = "H";
            scaleUp = 0.75;
        }
        else if (course.getName().endsWith("A"))
        {
            level = "A";
            scaleUp = 0.5;
        }
        else if (course.getName().endsWith("CP"))
        {
            level = "CP";
            scaleUp = 0.25;
        }
        else
        {
            level = "None";
            scaleUp = -1;
        }
    }
}
