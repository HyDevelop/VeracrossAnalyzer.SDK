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
public class VeracrossMessage extends VeracrossData<VeracrossMessage>
{
    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("type")
    @Expose
    private Long type;

    @SerializedName("message_person_pk")
    @Expose
    private Long messagePersonPk;

    @SerializedName("message_pk")
    @Expose
    private Long messagePk;

    @SerializedName("subject")
    @Expose
    private String subject;

    @SerializedName("date_sent")
    @Expose
    private String dateSent;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("opened")
    @Expose
    private Boolean opened;
}
