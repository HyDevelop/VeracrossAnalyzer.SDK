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
    long personPk;
    String username; // Email
    String securityRoles;

    int schoolYear;

    String client;
    String clientRoute;
    String context; // "student"
    String env; // "production"

    String forkliftDomain;
    String forkliftAuth;
    String tld;
}
