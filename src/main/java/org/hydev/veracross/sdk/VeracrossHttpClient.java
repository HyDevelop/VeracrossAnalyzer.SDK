package org.hydev.veracross.sdk;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * HTTP Client for the veracross sdk.
 * <p>
 * Class created by the HyDEV Team on 2019-08-19!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2019-08-19 13:45
 */
public class VeracrossHttpClient
{
    private final String schoolCode;

    private final CloseableHttpClient httpClient;

    /**
     * Construct a veracross http client.
     *
     * @param schoolCode Veracross school code (Eg. "sjp")
     */
    public VeracrossHttpClient(String schoolCode)
    {
        this.schoolCode = schoolCode;
    }

    /**
     * Login and save the session
     */
    public void login(String username, String password)
    {

    }
}
