package org.hydev.veracross.sdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 此类由 Hykilpikonna 在 2018/11/07 创建!
 * Created by Hykilpikonna on 2018/11/07!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeracrossAssignment implements VeracrossData
{
    @SerializedName("score_id")
    @Expose
    private Long scoreId;

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("assignment_id")
    @Expose
    private Long assignmentId;

    @SerializedName("assignment_type_id")
    @Expose
    private Long assignmentTypeId;

    @SerializedName("assignment_type")
    @Expose
    private String assignmentType;

    @SerializedName("assignment_type_sort_key")
    @Expose
    private Long assignmentTypeSortKey;

    @SerializedName("assignment_description")
    @Expose
    private String assignmentDescription;

    @SerializedName("assignment_notes")
    @Expose
    private Object assignmentNotes;

    @SerializedName("grading_period")
    @Expose
    private String gradingPeriod;

    @SerializedName("assignment_date_long")
    @Expose
    private String assignmentDateLong;

    @SerializedName("due_date_long")
    @Expose
    private String dueDateLong;

    @SerializedName("due_date")
    @Expose
    private String dueDate;

    @SerializedName("due_day")
    @Expose
    private String dueDay;

    @SerializedName("_date")
    @Expose
    private String date;

    @SerializedName("include_in_calculated_grade")
    @Expose
    private Long includeInCalculatedGrade;

    @SerializedName("extra_credit")
    @Expose
    private Object extraCredit;

    @SerializedName("dropbox_status")
    @Expose
    private Object dropboxStatus;

    @SerializedName("display_dropbox_submit")
    @Expose
    private Object displayDropboxSubmit;

    @SerializedName("num_attachments")
    @Expose
    private Long numAttachments;

    @SerializedName("num_criteria")
    @Expose
    private Long numCriteria;

    @SerializedName("num_feedback")
    @Expose
    private Long numFeedback;

    @SerializedName("maximum_score")
    @Expose
    private Long maximumScore;

    @SerializedName("points_possible")
    @Expose
    private Long pointsPossible;

    @SerializedName("raw_score")
    @Expose
    private String rawScore;

    @SerializedName("percent_grade")
    @Expose
    private String percentGrade;

    @SerializedName("completion_status_id")
    @Expose
    private Long completionStatusId;

    @SerializedName("completion_status")
    @Expose
    private String completionStatus;

    @SerializedName("is_unread")
    @Expose
    private Object isUnread;

    @SerializedName("is_notification")
    @Expose
    private Long isNotification;

    @SerializedName("is_problem")
    @Expose
    private Long isProblem;

    @SerializedName("display_grade")
    @Expose
    private Long displayGrade;

    @SerializedName("display_score")
    @Expose
    private Long displayScore;

    @SerializedName("display_maximum_score")
    @Expose
    private Long displayMaximumScore;

    @SerializedName("display_percent_grade")
    @Expose
    private Long displayPercentGrade;

    @SerializedName("display_points_possible")
    @Expose
    private Long displayPointsPossible;

    @SerializedName("allow_student_feedback")
    @Expose
    private Long allowStudentFeedback;
}
