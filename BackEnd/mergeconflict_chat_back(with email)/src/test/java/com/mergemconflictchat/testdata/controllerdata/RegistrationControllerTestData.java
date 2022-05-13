package com.mergemconflictchat.testdata.controllerdata;

import com.mergemconflictchat.model.User;
import com.mergemconflictchat.model.entity.RegistrationDTO;

public class RegistrationControllerTestData {

    private static final String CORRECT_LOGIN_1 = "userLogin";
    private static final String CORRECT_LOGIN_2 = "donatello";
    private static final String INCORRECT_LOGIN_1 = "user_login";
    private static final String INCORRECT_LOGIN_2 = "u";

    private static final String CORRECT_PASSWORD_1 = "passworD1";
    private static final String CORRECT_PASSWORD_2 = "Donatello07";
    private static final String CORRECT_PASSWORD_3 = "!Donatello07";
    private static final String INCORRECT_PASSWORD_1 = "passworD";

    private static final String CORRECT_EMAIL_1 = "email@qwe.com";
    private static final String CORRECT_EMAIL_2 = "e-mail@qwe.ua";
    private static final String INCORRECT_EMAIL_1 = "email.qwe.com";

    private static final String CORRECT_PHONE_NUMBER = "+380669801221";
    private static final String CORRECT_PHONE_NUMBER_2 = "+80066980777";
    private static final String INCORRECT_PHONE_NUMBER_1 = "380669801221111";

    private static final String CORRECT_COMPANY_NAME_1 = "";
    private static final String CORRECT_COMPANY_NAME_2 = "Afrodita";
    private static final String INCORRECT_COMPANY_NAME_1 = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901";

    public static String CORRECT_BODY_1 = "{\n" +
            "    \"login\":" + CORRECT_LOGIN_1 + ",\n" +
            "    \"password\":" + CORRECT_PASSWORD_1 + ",\n" +
            "    \"email\":" + CORRECT_EMAIL_1 + ",\n" +
            "    \"phoneNumber\":" + CORRECT_PHONE_NUMBER + ",\n" +
            "    \"company\":" + CORRECT_COMPANY_NAME_1 + "\n" +
            "}";

    public static String CORRECT_BODY_2 = "{\n" +
            "    \"login\":" + CORRECT_LOGIN_2 + ",\n" +
            "    \"password\":" + CORRECT_PASSWORD_2 + ",\n" +
            "    \"email\":" + CORRECT_EMAIL_2 + ",\n" +
            "    \"phoneNumber\":" + CORRECT_PHONE_NUMBER_2 + ",\n" +
            "    \"company\":" + CORRECT_COMPANY_NAME_2 + "\n" +
            "}";

    public static final RegistrationDTO CORRECT_REG_DTO_1 =
            new RegistrationDTO(CORRECT_LOGIN_1, CORRECT_PASSWORD_1,
                    CORRECT_EMAIL_1, CORRECT_PHONE_NUMBER, CORRECT_COMPANY_NAME_1);

    public static final RegistrationDTO CORRECT_REG_DTO_2 =
            new RegistrationDTO(CORRECT_LOGIN_2, CORRECT_PASSWORD_2,
                    CORRECT_EMAIL_2, CORRECT_PHONE_NUMBER_2, CORRECT_COMPANY_NAME_2);

    public static final RegistrationDTO CORRECT_REG_DTO_3 =
            new RegistrationDTO(CORRECT_LOGIN_2, CORRECT_PASSWORD_2,
                    CORRECT_EMAIL_2, CORRECT_PHONE_NUMBER_2, CORRECT_COMPANY_NAME_2);

    public static final String HASHED_PASSWORD = "HASHED_PASSWORD";

    public static final User USER_1 = new User(CORRECT_LOGIN_1, HASHED_PASSWORD, CORRECT_EMAIL_1, CORRECT_PHONE_NUMBER, CORRECT_COMPANY_NAME_1);

    public static final User USER_2 = new User(CORRECT_LOGIN_2, HASHED_PASSWORD, CORRECT_EMAIL_2, CORRECT_PHONE_NUMBER_2, CORRECT_COMPANY_NAME_2);


    //Incorrect


    //Ex
    public static String BODY_EXCEPTION = "{\n" +
            "loginjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj\n}";


    //inc
    public static final RegistrationDTO INCORRECT_LOGIN_REG_DTO_1 = new RegistrationDTO(INCORRECT_LOGIN_1, CORRECT_PASSWORD_1,  CORRECT_EMAIL_1, CORRECT_PHONE_NUMBER, CORRECT_COMPANY_NAME_1);

    public static final String BODY_INCORRECT_LOGIN = "{\n" +
            "    \"login\":user_login,\n" +
            "    \"password\":passworD1,\n" +
            "    \"confirmPassword\":passworD1,\n" +
            "    \"email\":email@qwe.com,\n" +
            "    \"phoneNumber\":+380669801221,\n" +
            "    \"company\":\n" +
            "}";

    //  public static final User USER_INCORRECT_LOGIN = new User(INCORRECT_LOGIN_1, CORRECT_PASSWORD_1, CORRECT_EMAIL_1, CORRECT_PHONE_NUMBER, CORRECT_COMPANY_NAME_1);

    public static final RegistrationDTO INCORRECT_PASSWORD_REG_DTO_1 = new RegistrationDTO(CORRECT_LOGIN_1, INCORRECT_PASSWORD_1, CORRECT_EMAIL_1, CORRECT_PHONE_NUMBER, CORRECT_COMPANY_NAME_1);

    public static final String BODY_INCORRECT_PASSWORD = "{\n" +
            "    \"login\":userLogin,\n" +
            "    \"password\":passworD,\n" +
            "    \"email\":email@qwe.com,\n" +
            "    \"phoneNumber\":+380669801221,\n" +
            "    \"company\":\n" +
            "}";

    public static final RegistrationDTO INCORRECT_CONFIRM_REG_DTO_1 = new RegistrationDTO(CORRECT_LOGIN_1, CORRECT_PASSWORD_1, CORRECT_EMAIL_1, CORRECT_PHONE_NUMBER, CORRECT_COMPANY_NAME_1);

    public static final String BODY_INCORRECT_CONFIRM = "{\n" +
            "    \"login\":userLogin,\n" +
            "    \"password\":passworD1,\n" +
            "    \"email\":email@qwe.com,\n" +
            "    \"phoneNumber\":+380669801221,\n" +
            "    \"company\":\n" +
            "}";

    public static final RegistrationDTO INCORRECT_EMAIL_REG_DTO_1 = new RegistrationDTO(CORRECT_LOGIN_1, CORRECT_PASSWORD_1, INCORRECT_EMAIL_1, CORRECT_PHONE_NUMBER, CORRECT_COMPANY_NAME_1);

    public static final String BODY_INCORRECT_EMAIL = "{\n" +
            "    \"login\":userLogin,\n" +
            "    \"password\":passworD1,\n" +
            "    \"email\":email.qwe.com,\n" +
            "    \"phoneNumber\":+380669801221,\n" +
            "    \"company\":\n" +
            "}";

    public static final RegistrationDTO INCORRECT_PHONE_NUMBER_REG_DTO_1 = new RegistrationDTO(CORRECT_LOGIN_1, CORRECT_PASSWORD_1, CORRECT_EMAIL_1, INCORRECT_PHONE_NUMBER_1, CORRECT_COMPANY_NAME_1);

    public static final String BODY_INCORRECT_NUMBER = "{\n" +
            "    \"login\":userLogin,\n" +
            "    \"password\":passworD1,\n" +
            "    \"email\":email@qwe.com,\n" +
            "    \"phoneNumber\":380669801221,\n" +
            "    \"company\":\n" +
            "}";

    public static final RegistrationDTO INCORRECT_COMPANY_NAME_REG_DTO_1 = new RegistrationDTO(CORRECT_LOGIN_1, CORRECT_PASSWORD_1,  CORRECT_EMAIL_1, CORRECT_PHONE_NUMBER, INCORRECT_COMPANY_NAME_1);

    public static final String BODY_INCORRECT_COMPANY = "{\n" +
            "    \"login\":userLogin,\n" +
            "    \"password\":passworD1,\n" +
            "    \"email\":email@qwe.com,\n" +
            "    \"phoneNumber\":+380669801221,\n" +
            "    \"company\":12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901\n" +
            "}";


    public static final String MSG_SUCCESS = "OK";

    public static final String MSG_INVALID_EMPTY_FIELD = "Fields cannot be empty";

    public static final String MSG_INVALID_NULL = "Fields cannot be null";

    public static final String MSG_INVALID_CREDENTIALS = "Invalid credentials";

    public static final String MSG_INVALID_INCORRECT_LOGIN = "Incorrect login";

    public static final String MSG_INVALID_INCORRECT_PASSWORD = "Incorrect password";

    public static final String MSG_INVALID_CONFIRM_PASSWORD_MISMATCH = "Password mismatch";

    public static final String MSG_INVALID_INCORRECT_EMAIL = "Incorrect email";

    public static final String MSG_INVALID_INCORRECT_NUMBER = "Incorrect number";

    public static final String MSG_INVALID_COMPANY_EXCEEDED_CHARACTERS = "Exceeded number of characters";

}
