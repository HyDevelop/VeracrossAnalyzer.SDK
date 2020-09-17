package org.hydev.veracross.sdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventV3 implements VeraData
{
    @SerializedName("record_type")
    @Expose
    public Long recordType;

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("record_identifier")
    @Expose
    public Long recordIdentifier;

    @SerializedName("start_date")
    @Expose
    public String startDate;

    @SerializedName("start_time")
    @Expose
    public String startTime;

    @SerializedName("end_date")
    @Expose
    public String endDate;

    @SerializedName("end_time")
    @Expose
    public String endTime;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("tooltip")
    @Expose
    public String tooltip;

    @SerializedName("location")
    @Expose
    public String location;

    @SerializedName("link_style")
    @Expose
    public String linkStyle;

    @SerializedName("event_url")
    @Expose
    public String eventUrl;

    @SerializedName("type")
    @Expose
    public String type;

    @Data
    public static class EventListV3
    {
        @SerializedName("events")
        @Expose
        private List<EventV3> events = null;
    }
}
