package org.hydev.veracross.sdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeraStudent implements VeraData
{
    @SerializedName("person_pk")
    @Expose
    private Integer personPk;

    @SerializedName("name_sort")
    @Expose
    private String nameSort;

    @SerializedName("student")
    @Expose
    private String fullName;

    @SerializedName("first_name")
    @Expose
    private String firstName;

    @SerializedName("last_name")
    @Expose
    private String lastName;

    @SerializedName("birthday")
    @Expose
    private String birthday;

    @SerializedName("photo_url")
    @Expose
    private String photoUrl;

    @SerializedName("sibilings")
    @Expose
    private String sibilings;

    @SerializedName("school_level_id")
    @Expose
    private Short schoolLevelId;

    @SerializedName("grade_level")
    @Expose
    private String gradeLevel;

    @SerializedName("current_grade_id")
    @Expose
    private Short currentGradeId;

    @SerializedName("graduation_year")
    @Expose
    private Short graduationYear;

    @SerializedName("student_group")
    @Expose
    private String studentGroup;

    @SerializedName("student_group_id")
    @Expose
    private Integer studentGroupId;

    @SerializedName("dorm")
    @Expose
    private String dorm;

    @SerializedName("dorm_id")
    @Expose
    private Integer dormId;

    @SerializedName("boarding_day")
    @Expose
    private String boardingDay;

    @SerializedName("campus")
    @Expose
    private String campus;

    @SerializedName("campus_id")
    @Expose
    private Integer campusId;

    @SerializedName("homeroom")
    @Expose
    private String homeroom;

    @SerializedName("homeroom_id")
    @Expose
    private Integer homeroomId;

    @SerializedName("advisor")
    @Expose
    private String advisor;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("address_url")
    @Expose
    private String mapUrl;

    @SerializedName("phone_home")
    @Expose
    private String phoneHome;

    @SerializedName("phone_mobile")
    @Expose
    private String phoneMobile;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("sports")
    @Expose
    private String sports;

    @SerializedName("all_classes")
    @Expose
    private String allClasses;

    @SerializedName("resident_household_fk")
    @Expose
    private Integer residentHouseholdFk;

    @SerializedName("resident_parents")
    @Expose
    private String residentParents;

    @SerializedName("resident_address")
    @Expose
    private String residentAddress;

    @SerializedName("resident_address_url")
    @Expose
    private String residentMapUrl;

    @SerializedName("resident_phone")
    @Expose
    private String residentPhone;

    @SerializedName("p1_name")
    @Expose
    private String p1Name;

    @SerializedName("p1_mobile")
    @Expose
    private String p1Mobile;

    @SerializedName("p1_email")
    @Expose
    private String p1Email;

    @SerializedName("p2_name")
    @Expose
    private String p2Name;

    @SerializedName("p2_mobile")
    @Expose
    private String p2Mobile;

    @SerializedName("p2_email")
    @Expose
    private String p2Email;

    @SerializedName("nonresident_household_fk")
    @Expose
    private Integer nonresidentHouseholdFk;

    @SerializedName("nonresident_parents")
    @Expose
    private String nonresidentParents;

    @SerializedName("nonresident_address")
    @Expose
    private String nonresidentAddress;

    @SerializedName("nonresident_address_url")
    @Expose
    private String nonresidentAddressUrl;

    @SerializedName("nonresident_phone")
    @Expose
    private String nonresidentPhone;

    @SerializedName("p3_name")
    @Expose
    private String p3Name;

    @SerializedName("p3_mobile")
    @Expose
    private String p3Mobile;

    @SerializedName("p3_email")
    @Expose
    private String p3Email;

    @SerializedName("p4_name")
    @Expose
    private String p4Name;

    @SerializedName("p4_mobile")
    @Expose
    private String p4Mobile;

    @SerializedName("p4_email")
    @Expose
    private String p4Email;

    /**
     * Get the ids of all classes
     *
     * @return List of ids.
     */
    public List<Integer> getClassesIds()
    {
        List<Integer> list = new ArrayList<>();
        for (String classId : allClasses.split("\\|"))
        {
            list.add(Integer.parseInt(classId));
        }
        return list;
    }
}
