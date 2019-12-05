package org.hydev.veracross.sdk.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * This models the weighting method when grading a course.
 * <p>
 * Class created by the HyDEV Team on 2019-09-30!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2019-09-30 15:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VeracrossCourseGrading
{
    // Weighting method: total_mean vs. percent_type
    private GradingMethod method;

    // Weighting maps if it is weighted by type
    // Eg. weightingMaps[0] is the map for the 1st term
    private List<Map<String, Double>> weightingMaps;

    public enum GradingMethod
    {
        TOTAL_MEAN, PERCENT_TYPE, NOT_GRADED
    }
}
