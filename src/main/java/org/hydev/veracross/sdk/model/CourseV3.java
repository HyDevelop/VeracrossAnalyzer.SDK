package org.hydev.veracross.sdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class CourseV3
{
    @SerializedName("enrollment_pk")
    @Expose
    private Integer enrollmentPk;

    @SerializedName("class_pk")
    @Expose
    private Integer classPk;

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
    private Integer courseType;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("meets_today")
    @Expose
    private Integer meetsToday;

    @SerializedName("todays_times")
    @Expose
    private String todaysTimes;

    @SerializedName("todays_start_time")
    @Expose
    private String todaysStartTime;

    @SerializedName("todays_blocks")
    @Expose
    private String todaysBlocks;

    @SerializedName("ptd_grade")
    @Expose
    private Double ptdGrade;

    @SerializedName("ptd_letter_grade")
    @Expose
    private String ptdLetterGrade;

    @SerializedName("display_assignments")
    @Expose
    private Integer displayAssignments;

    @SerializedName("display_notifications")
    @Expose
    private Integer displayNotifications;

    @SerializedName("notifications_count")
    @Expose
    private Integer notificationsCount;

    @SerializedName("class_configuration")
    @Expose
    private String classConfiguration;

    @SerializedName("status_sort_key")
    @Expose
    private Integer statusSortKey;

    @SerializedName("type_sort_key")
    @Expose
    private Integer typeSortKey;

    @SerializedName("subject_sort_key")
    @Expose
    private Integer subjectSortKey;

    @SerializedName("course_sort_key")
    @Expose
    private Integer courseSortKey;
}
