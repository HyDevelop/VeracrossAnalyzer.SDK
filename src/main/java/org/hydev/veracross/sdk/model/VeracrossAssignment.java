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
    public Long scoreId;

    @SerializedName("id")
    @Expose
    public Long id;

    @SerializedName("assignment_id")
    @Expose
    public Long assignmentId;

    @SerializedName("assignment_type_id")
    @Expose
    public Long assignmentTypeId;

    @SerializedName("assignment_type")
    @Expose
    public String assignmentType;

    @SerializedName("assignment_type_sort_key")
    @Expose
    public Long assignmentTypeSortKey;

    @SerializedName("assignment_description")
    @Expose
    public String assignmentDescription;

    @SerializedName("assignment_notes")
    @Expose
    public Object assignmentNotes;

    @SerializedName("grading_period")
    @Expose
    public String gradingPeriod;

    @SerializedName("assignment_date_long")
    @Expose
    public String assignmentDateLong;

    @SerializedName("due_date_long")
    @Expose
    public String dueDateLong;

    @SerializedName("due_date")
    @Expose
    public String dueDate;

    @SerializedName("due_day")
    @Expose
    public String dueDay;

    @SerializedName("_date")
    @Expose
    public String date;

    @SerializedName("include_in_calculated_grade")
    @Expose
    public Long includeInCalculatedGrade;

    @SerializedName("extra_credit")
    @Expose
    public Object extraCredit;

    @SerializedName("dropbox_status")
    @Expose
    public Object dropboxStatus;

    @SerializedName("display_dropbox_submit")
    @Expose
    public Object displayDropboxSubmit;

    @SerializedName("num_attachments")
    @Expose
    public Long numAttachments;

    @SerializedName("num_criteria")
    @Expose
    public Long numCriteria;

    @SerializedName("num_feedback")
    @Expose
    public Long numFeedback;

    @SerializedName("maximum_score")
    @Expose
    public Long maximumScore;

    @SerializedName("points_possible")
    @Expose
    public Long pointsPossible;

    @SerializedName("raw_score")
    @Expose
    public String rawScore;

    @SerializedName("percent_grade")
    @Expose
    public String percentGrade;

    @SerializedName("completion_status_id")
    @Expose
    public Long completionStatusId;

    @SerializedName("completion_status")
    @Expose
    public String completionStatus;

    @SerializedName("is_unread")
    @Expose
    public Object isUnread;

    @SerializedName("is_notification")
    @Expose
    public Long isNotification;

    @SerializedName("is_problem")
    @Expose
    public Long isProblem;

    @SerializedName("display_grade")
    @Expose
    public Long displayGrade;

    @SerializedName("display_score")
    @Expose
    public Long displayScore;

    @SerializedName("display_maximum_score")
    @Expose
    public Long displayMaximumScore;

    @SerializedName("display_percent_grade")
    @Expose
    public Long displayPercentGrade;

    @SerializedName("display_points_possible")
    @Expose
    public Long displayPointsPossible;

    @SerializedName("allow_student_feedback")
    @Expose
    public Long allowStudentFeedback;
}
