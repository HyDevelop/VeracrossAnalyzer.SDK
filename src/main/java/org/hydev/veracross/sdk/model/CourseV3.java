package org.hydev.veracross.sdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Course data model for Veracross Portal V3
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
    private Integer id;

    @SerializedName("class_id")
    @Expose
    private String nameId;

    @SerializedName("class_name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("teacher_full_name")
    @Expose
    private String teacherName;

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
    private Double numericGrade;

    @SerializedName("ptd_letter_grade")
    @Expose
    private String letterGrade;

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

    /**
     * Copy constructor
     *
     * @param o Other course
     */
    public CourseV3(CourseV3 o)
    {
        this.enrollmentPk = o.enrollmentPk;
        this.id = o.id;
        this.nameId = o.nameId;
        this.name = o.name;
        this.description = o.description;
        this.teacherName = o.teacherName;
        this.courseType = o.courseType;
        this.status = o.status;
        this.type = o.type;
        this.meetsToday = o.meetsToday;
        this.todaysTimes = o.todaysTimes;
        this.todaysStartTime = o.todaysStartTime;
        this.todaysBlocks = o.todaysBlocks;
        this.numericGrade = o.numericGrade;
        this.letterGrade = o.letterGrade;
        this.displayAssignments = o.displayAssignments;
        this.displayNotifications = o.displayNotifications;
        this.notificationsCount = o.notificationsCount;
        this.classConfiguration = o.classConfiguration;
        this.statusSortKey = o.statusSortKey;
        this.typeSortKey = o.typeSortKey;
        this.subjectSortKey = o.subjectSortKey;
        this.courseSortKey = o.courseSortKey;
    }
}
