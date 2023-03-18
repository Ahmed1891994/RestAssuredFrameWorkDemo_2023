package utils;

import java.util.Base64;

public class AuthenticationHandling
{
    public String BasicAuthenticationencoding(String text)
    {
        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    public String BasicAuthenticationDecoding(String text)
    {
        return Base64.getDecoder().decode(text).toString();
    }


}
