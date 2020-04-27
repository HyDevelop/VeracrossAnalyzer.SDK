package org.hydev.veracross.sdk.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Instances of this class contains the login info of a student.
 * <p>
 * Class created by the HyDEV Team on 2020-04-25!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2020-04-25 15:04
 */
@Data
@Accessors(fluent = true)
public class VeraLoginInfo
{
    int personPk;
    String username; // Email
    String securityRoles; // []

    int schoolYear; // 2019

    String client; // "sjp"
    String clientRoute; // "sjp"

    String forkliftDomain; // "https://files.veracross.com"
    String forkliftAuth; // "eyJ......." (Base 64)
    String tld; // "com"
}
