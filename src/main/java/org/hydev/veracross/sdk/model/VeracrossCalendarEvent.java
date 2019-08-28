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
public class VeracrossCalendarEvent implements VeracrossData
{
    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("record_type")
    @Expose
    public Long recordType;

    @SerializedName("start_date")
    @Expose
    public String startDate;

    @SerializedName("start_time")
    @Expose
    public Object startTime;

    @SerializedName("end_date")
    @Expose
    public String endDate;

    @SerializedName("end_time")
    @Expose
    public Object endTime;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("tooltip")
    @Expose
    public String tooltip;

    @SerializedName("location")
    @Expose
    public Object location;

    @SerializedName("link_style")
    @Expose
    public String linkStyle;

    @SerializedName("event_url")
    @Expose
    public String eventUrl;
}
