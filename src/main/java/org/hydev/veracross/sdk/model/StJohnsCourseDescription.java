package org.hydev.veracross.sdk.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Model for course desc: https://www.stjohnsprep.org/page.cfm?p=9248
 * <p>
 * Class created by the HyDEV Team on 2019-11-02!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2019-11-02 21:22
 */
@Data
@AllArgsConstructor
public class StJohnsCourseDescription
{
    private String title;
    private String name;
    private String levels;
    private String credit;
    private String description;
    private String prerequisites;
}
