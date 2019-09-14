package org.hydev.veracross.sdk.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 此类由 Hykilpikonna 在 2018/10/25 创建!
 * Created by Hykilpikonna on 2018/10/25!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@Data
@AllArgsConstructor
@Builder
public class VeracrossCourse extends VeracrossData<VeracrossCourse>
{
    // Name of the course
    private String name;

    // Name of the teacher teaching the course
    private String teacherName;

    // ID to access the course details page
    private long id;

    // ID to access the course assignments page
    private long assignmentsId;

    // Letter grade (nullable)
    private String letterGrade;

    // Numeric grade (nullable)
    private Double numericGrade;

    /**
     * Self-constructor that copies all field from the other
     *
     * @param other Other
     */
    public VeracrossCourse(VeracrossCourse other)
    {
        this(other.name, other.teacherName, other.id, other.assignmentsId,
                other.letterGrade, other.numericGrade);
    }
}
