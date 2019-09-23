package org.hydev.veracross.sdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * This class contains information about a course in Veracross
 * <p>
 * Class created by the HyDEV Team on 2019-09-23!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2018-10-25
 */
@Data
@AllArgsConstructor
@Builder
public class VeracrossCourse implements VeracrossData
{
    // Name of the course
    @SerializedName("class_name")
    private String name;

    // Name of the teacher teaching the course
    @SerializedName("teacher_full_name")
    private String teacherName;

    // ID to access the course details page
    @SerializedName("class_pk")
    private long id;

    // ID to access the course assignments page
    @SerializedName("enrollment_pk")
    private long assignmentsId;

    // Letter grade (nullable)
    @SerializedName("ptd_letter_grade")
    private String letterGrade;

    // Numeric grade (nullable)
    @Expose @SerializedName("ptd_grade")
    private Double numericGrade;

    // Future / Present (Active) / Past
    @SerializedName("status")
    private String status;

    /**
     * Self-constructor that copies all field from the other
     *
     * @param other Other
     */
    public VeracrossCourse(VeracrossCourse other)
    {
        this(other.name, other.teacherName, other.id, other.assignmentsId,
                other.letterGrade, other.numericGrade, other.status);
    }
}
