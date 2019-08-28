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
public class VeracrossMessage implements VeracrossData
{
    @SerializedName("id")
    @Expose
    public Long id;

    @SerializedName("type")
    @Expose
    public Long type;

    @SerializedName("message_person_pk")
    @Expose
    public Long messagePersonPk;

    @SerializedName("message_pk")
    @Expose
    public Long messagePk;

    @SerializedName("subject")
    @Expose
    public String subject;

    @SerializedName("date_sent")
    @Expose
    public String dateSent;

    @SerializedName("date")
    @Expose
    public String date;

    @SerializedName("opened")
    @Expose
    public Boolean opened;
}
