package org.hydev.veracross.sdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is the data POJO class for faculty and staff directory:
 * https://portals-app.veracross.com/.../student/directory/faculty
 * API:
 * https://portals-app.veracross.com/.../directory/entries.json?directory=faculty&portal=student&refresh=0
 * <p>
 * Class created by the HyDEV Team on 2019-09-14!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2019-09-14 15:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeracrossFaculty implements VeracrossData
{
    @SerializedName("person_pk")
    @Expose
    private Integer personPk;

    @SerializedName("is_faculty")
    @Expose
    private Byte isFaculty;

    @SerializedName("first_name")
    @Expose
    private String firstName;

    @SerializedName("nick_name")
    @Expose
    private String nickName;

    @SerializedName("last_name")
    @Expose
    private String lastName;

    @SerializedName("spouse")
    @Expose
    private String spouse;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("job_title")
    @Expose
    private String jobTitle;

    @SerializedName("faculty_type")
    @Expose
    private String facultyType;

    @SerializedName("phone_home")
    @Expose
    private String phoneHome;

    @SerializedName("phone_business")
    @Expose
    private String phoneBusiness;

    @SerializedName("cell")
    @Expose
    private String cell;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("photo_url")
    @Expose
    private String photoUrl;

    @SerializedName("map_url")
    @Expose
    private String mapUrl;

    @SerializedName("school_level_id")
    @Expose
    private String schoolLevelId;

    @SerializedName("department_id")
    @Expose
    private Short departmentId;

    @SerializedName("campus_id")
    @Expose
    private Short campusId;

    @SerializedName("faculty_type_id")
    @Expose
    private Short facultyTypeId;
}
