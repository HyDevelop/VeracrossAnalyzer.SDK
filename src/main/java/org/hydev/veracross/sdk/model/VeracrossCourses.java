package org.hydev.veracross.sdk.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

/**
 * TODO: Write a description for this class!
 * <p>
 * Class created by the HyDEV Team on 2019-11-29!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2019-11-29 07:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class VeracrossCourses extends ArrayList<VeracrossCourse>
{
    private String username;
    private Integer personPk;

    public VeracrossCourses setPerson(VeracrossPerson person)
    {
        username = person.getUsername();
        personPk = person.getPersonPk();

        return this;
    }
}
