package org.hydev.veracross.sdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * This is used because the list is contained under the "courses" key
 * instead of the json base.
 */
@Data
public class CourseListV3
{
    @SerializedName("courses")
    @Expose
    private List<CourseV3> courses = null;

    private String username;
    private String personPk;
}
