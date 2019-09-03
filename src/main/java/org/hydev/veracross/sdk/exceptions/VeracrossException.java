package org.hydev.veracross.sdk.exceptions;

/**
 * This is thrown when the Veracross SDK encounters an error.
 * <p>
 * Class created by the HyDEV Team on 2019-09-03!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2019-09-03 09:13
 */
public class VeracrossException extends Exception
{
    public VeracrossException(String msg)
    {
        super(msg);
    }

    public VeracrossException(String msg, Throwable cause)
    {
        super(msg, cause);
    }
}
