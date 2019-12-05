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
 * Meow!
 *
 * @author Hykilpikonna
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeraCalendarEvent implements VeraData
{
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("record_type")
    @Expose
    private Long recordType;

    @SerializedName("start_date")
    @Expose
    private String startDate;

    @SerializedName("start_time")
    @Expose
    private Object startTime;

    @SerializedName("end_date")
    @Expose
    private String endDate;

    @SerializedName("end_time")
    @Expose
    private Object endTime;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("tooltip")
    @Expose
    private String tooltip;

    @SerializedName("location")
    @Expose
    private Object location;

    @SerializedName("link_style")
    @Expose
    private String linkStyle;

    @SerializedName("event_url")
    @Expose
    private String eventUrl;
}
