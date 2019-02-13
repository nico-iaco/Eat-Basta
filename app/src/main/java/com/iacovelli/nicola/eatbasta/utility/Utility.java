package com.iacovelli.nicola.eatbasta.utility;

import android.util.Patterns;

import java.util.regex.Pattern;

public class Utility {
    public static boolean checkEmail(String email) {
        return Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(), email);
    }

    public static boolean checkPassword(String pwd) {
        return pwd.length() > 6;
    }

    public static boolean checkPassword(String pwd, String pwdVerify) {
        return (pwd.length() > 6 && pwd.equals(pwdVerify));
    }

    public static boolean checkPhone(String phone) {
        return Pattern.matches(Patterns.PHONE.pattern(), phone);
    }
}
