package org.hydev.veracross.sdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * TODO: Write a description for this class!
 * <p>
 * Class created by the HyDEV Team on 2019-09-23!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2019-09-23 18:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeracrossLegacyCourse
{
    @SerializedName("enrollment_pk")
    @Expose
    private Long enrollmentPk;

    @SerializedName("class_pk")
    @Expose
    private Long classPk;

    @SerializedName("class_id")
    @Expose
    private String classId;

    @SerializedName("class_name")
    @Expose
    private String className;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("teacher_full_name")
    @Expose
    private String teacherFullName;

    @SerializedName("course_type")
    @Expose
    private Long courseType;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("meets_today")
    @Expose
    private Long meetsToday;

    @SerializedName("todays_times")
    @Expose
    private Object todaysTimes;

    @SerializedName("todays_start_time")
    @Expose
    private Object todaysStartTime;

    @SerializedName("todays_blocks")
    @Expose
    private Object todaysBlocks;

    @SerializedName("ptd_grade")
    @Expose
    private Double ptdGrade;

    @SerializedName("ptd_letter_grade")
    @Expose
    private String ptdLetterGrade;

    @SerializedName("display_assignments")
    @Expose
    private Short displayAssignments;

    @SerializedName("display_notifications")
    @Expose
    private Short displayNotifications;

    @SerializedName("notifications_count")
    @Expose
    private Long notificationsCount;

    @SerializedName("class_configuration")
    @Expose
    private String classConfiguration;

    @SerializedName("status_sort_key")
    @Expose
    private Long statusSortKey;

    @SerializedName("type_sort_key")
    @Expose
    private Long typeSortKey;

    @SerializedName("subject_sort_key")
    @Expose
    private Long subjectSortKey;

    @SerializedName("course_sort_key")
    @Expose
    private Long courseSortKey;

    /**
     * This is used because the list is contained under the "courses" key
     * instead of the json base.
     */
    @Data
    public static class CourseListContainer
    {
        @SerializedName("courses")
        @Expose
        private List<VeracrossLegacyCourse> courses = null;

        /**
         * Convert legacy course to course
         *
         * @return List of courses
         */
        public VeracrossCourses convert()
        {
            VeracrossCourses result = new VeracrossCourses();
            courses.forEach(course -> result.add(new VeracrossCourse(course)));
            return result;
        }
    }
}
