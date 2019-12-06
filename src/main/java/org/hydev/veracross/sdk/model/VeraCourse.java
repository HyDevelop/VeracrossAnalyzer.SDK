package org.hydev.veracross.sdk.model;

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
public class VeraCourse implements VeraData
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

    // Future / Present (Active) / Past
    private String status;

    // Grading
    private VeraCourseGrading grading;

    /**
     * Self-constructor that copies all field from the other
     *
     * @param other Other
     */
    public VeraCourse(VeraCourse other)
    {
        this(other.name, other.teacherName, other.id, other.assignmentsId,
                other.letterGrade, other.numericGrade, other.status, other.grading);
    }

    /**
     * Convert a legacy course to a course.
     *
     * @param other Legacy course.
     */
    public VeraCourse(VeraLegacyCourse other)
    {
        this(VeraCourse.builder()
                .name(other.getClassName())
                .teacherName(other.getTeacherFullName())
                .id(other.getClassPk())
                .assignmentsId(other.getEnrollmentPk())
                .letterGrade(other.getPtdLetterGrade() == null ? null :
                        other.getPtdLetterGrade().replace(" ", ""))
                .numericGrade(other.getPtdGrade())
                .status(other.getStatus())
                .build());
    }
}