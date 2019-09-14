package org.hydev.veracross.sdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This class is the data POJO class for student directory:
 * https://portals-app.veracross.com/.../student/directory/students
 * API:
 * https://portals-app.veracross.com/.../directory/entries.json?directory=student&portal=student&refresh=0
 * <p>
 * Class created by the HyDEV Team on 2019-09-14!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2019-09-14 15:54
 */
public class VeracrossStudent
{
    @SerializedName("person_pk")
    @Expose
    public Integer personPk;

    @SerializedName("name_sort")
    @Expose
    public String nameSort;

    @SerializedName("student")
    @Expose
    public String fullName;

    @SerializedName("first_name")
    @Expose
    public String firstName;

    @SerializedName("last_name")
    @Expose
    public String lastName;

    @SerializedName("birthday")
    @Expose
    public String birthday;

    @SerializedName("photo_url")
    @Expose
    public String photoUrl;

    @SerializedName("sibilings")
    @Expose
    public String sibilings;

    @SerializedName("school_level_id")
    @Expose
    public Short schoolLevelId;

    @SerializedName("grade_level")
    @Expose
    public String gradeLevel;

    @SerializedName("current_grade_id")
    @Expose
    public Short currentGradeId;

    @SerializedName("graduation_year")
    @Expose
    public Short graduationYear;

    @SerializedName("student_group")
    @Expose
    public String studentGroup;

    @SerializedName("student_group_id")
    @Expose
    public Integer studentGroupId;

    @SerializedName("dorm")
    @Expose
    public String dorm;

    @SerializedName("dorm_id")
    @Expose
    public Integer dormId;

    @SerializedName("boarding_day")
    @Expose
    public String boardingDay;

    @SerializedName("campus")
    @Expose
    public String campus;

    @SerializedName("campus_id")
    @Expose
    public Integer campusId;

    @SerializedName("homeroom")
    @Expose
    public String homeroom;

    @SerializedName("homeroom_id")
    @Expose
    public Integer homeroomId;

    @SerializedName("advisor")
    @Expose
    public String advisor;

    @SerializedName("address")
    @Expose
    public String address;

    @SerializedName("address_url")
    @Expose
    public String mapUrl;

    @SerializedName("phone_home")
    @Expose
    public String phoneHome;

    @SerializedName("phone_mobile")
    @Expose
    public String phoneMobile;

    @SerializedName("email")
    @Expose
    public String email;

    @SerializedName("sports")
    @Expose
    public String sports;

    @SerializedName("all_classes")
    @Expose
    public String allClasses;

    @SerializedName("resident_household_fk")
    @Expose
    public Integer residentHouseholdFk;

    @SerializedName("resident_parents")
    @Expose
    public String residentParents;

    @SerializedName("resident_address")
    @Expose
    public String residentAddress;

    @SerializedName("resident_address_url")
    @Expose
    public String residentMapUrl;

    @SerializedName("resident_phone")
    @Expose
    public String residentPhone;

    @SerializedName("p1_name")
    @Expose
    public String p1Name;

    @SerializedName("p1_mobile")
    @Expose
    public String p1Mobile;

    @SerializedName("p1_email")
    @Expose
    public String p1Email;

    @SerializedName("p2_name")
    @Expose
    public String p2Name;

    @SerializedName("p2_mobile")
    @Expose
    public String p2Mobile;

    @SerializedName("p2_email")
    @Expose
    public String p2Email;

    @SerializedName("nonresident_household_fk")
    @Expose
    public Integer nonresidentHouseholdFk;

    @SerializedName("nonresident_parents")
    @Expose
    public String nonresidentParents;

    @SerializedName("nonresident_address")
    @Expose
    public String nonresidentAddress;

    @SerializedName("nonresident_address_url")
    @Expose
    public String nonresidentAddressUrl;

    @SerializedName("nonresident_phone")
    @Expose
    public String nonresidentPhone;

    @SerializedName("p3_name")
    @Expose
    public String p3Name;

    @SerializedName("p3_mobile")
    @Expose
    public String p3Mobile;

    @SerializedName("p3_email")
    @Expose
    public String p3Email;

    @SerializedName("p4_name")
    @Expose
    public String p4Name;

    @SerializedName("p4_mobile")
    @Expose
    public String p4Mobile;

    @SerializedName("p4_email")
    @Expose
    public String p4Email;
}
