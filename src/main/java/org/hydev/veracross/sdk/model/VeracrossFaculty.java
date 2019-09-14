package org.hydev.veracross.sdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
public class VeracrossFaculty
{
    @SerializedName("person_pk")
    @Expose
    public Integer personPk;

    @SerializedName("is_faculty")
    @Expose
    public Boolean isFaculty;

    @SerializedName("first_name")
    @Expose
    public String firstName;

    @SerializedName("nick_name")
    @Expose
    public String nickName;

    @SerializedName("last_name")
    @Expose
    public String lastName;

    @SerializedName("spouse")
    @Expose
    public String spouse;

    @SerializedName("address")
    @Expose
    public String address;

    @SerializedName("job_title")
    @Expose
    public String jobTitle;

    @SerializedName("faculty_type")
    @Expose
    public String facultyType;

    @SerializedName("phone_home")
    @Expose
    public String phoneHome;

    @SerializedName("phone_business")
    @Expose
    public String phoneBusiness;

    @SerializedName("cell")
    @Expose
    public String cell;

    @SerializedName("email")
    @Expose
    public String email;

    @SerializedName("photo_url")
    @Expose
    public String photoUrl;

    @SerializedName("map_url")
    @Expose
    public String mapUrl;

    @SerializedName("school_level_id")
    @Expose
    public String schoolLevelId;

    @SerializedName("department_id")
    @Expose
    public Short departmentId;

    @SerializedName("campus_id")
    @Expose
    public Short campusId;

    @SerializedName("faculty_type_id")
    @Expose
    public Short facultyTypeId;
}
