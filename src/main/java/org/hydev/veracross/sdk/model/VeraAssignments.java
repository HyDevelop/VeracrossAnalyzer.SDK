package org.hydev.veracross.sdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 此类由 Hykilpikonna 在 2018/11/07 创建!
 * Created by Hykilpikonna on 2018/11/07!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeraAssignments implements VeraData
{
    @SerializedName("assignments")
    @Expose
    private List<VeraAssignment> assignments = null;

    // The type for attachments, criteria, and criteriaGradeScaleLevels are not sure
    //   because I don't have them in any class I have.

    @SerializedName("attachments")
    @Expose
    @Deprecated
    private List<Object> attachments = null;

    @SerializedName("criteria")
    @Expose
    @Deprecated
    private List<Object> criteria = null;

    @SerializedName("criteria_grade_scale_levels")
    @Expose
    @Deprecated
    private List<Object> criteriaGradeScaleLevels = null;
}
