package org.hydev.veracross.sdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 此类由 Hykilpikonna 在 2018/10/25 创建!
 * Created by Hykilpikonna on 2018/10/25!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
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
