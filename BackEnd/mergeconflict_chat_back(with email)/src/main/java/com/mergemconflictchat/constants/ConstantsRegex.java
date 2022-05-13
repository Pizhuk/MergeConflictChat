package com.mergemconflictchat.constants;

public class ConstantsRegex {

    public static final String REGEX_CORRECT_PASSWORD_2 = "^.*(?=.{6,})(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$";

    public static final String REGEX_CORRECT_LOGIN = "[a-zA-Z0-9_-]{6,30}";

    public static final String REGEX_CORRECT_EMAIL = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";

    public static final String REGEX_CORRECT_PHONE_NUMBER = "^(([+]?([0-9]){6,15}))";

}
