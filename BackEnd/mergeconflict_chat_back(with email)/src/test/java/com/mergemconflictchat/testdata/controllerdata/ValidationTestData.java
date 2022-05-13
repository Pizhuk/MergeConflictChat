package com.mergemconflictchat.testdata.controllerdata;

import com.mergemconflictchat.model.entity.AuthDTO;
import com.mergemconflictchat.model.entity.RegistrationDTO;

public class ValidationTestData {

    private static final String CORRECT_LOGIN_1 = "userLogin";
    private static final String TAKEN_LOGIN = "logPlayer1";
    private static final String CORRECT_LOGIN_2 = "donatello";
    private static final String INCORRECT_LOGIN_1 = "user_login123!";
    private static final String INCORRECT_LOGIN_2 = "u";
    private static final String INCORRECT_LOGIN_3 = "";

    private static final String CORRECT_PASSWORD_1 = "!passworD1";
    private static final String CORRECT_PASSWORD_2 = "!!Donatello07";
    private static final String INCORRECT_PASSWORD_1 = "passworD";

    private static final String CORRECT_EMAIL_1 = "email@qwe.com";
    private static final String TAKEN_EMAIL = "some@gmail.com";
    private static final String CORRECT_EMAIL_2 = "e-mail@qwe.ua";
    private static final String INCORRECT_EMAIL_1 = "email.qwe.com";

    private static final String CORRECT_PHONE_NUMBER = "+380669801221";
    private static final String CORRECT_PHONE_NUMBER_2 = "+80066980777";
    private static final String INCORRECT_PHONE_NUMBER_1 = "123";

    private static final String CORRECT_COMPANY_NAME_1 = "";
    private static final String CORRECT_COMPANY_NAME_2 = "Afrodita";
    private static final String INCORRECT_COMPANY_NAME_1 = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901";

    public static final RegistrationDTO EMPTY_FIELD_REG_DTO =
            new RegistrationDTO(INCORRECT_LOGIN_3, CORRECT_PASSWORD_2,
                    INCORRECT_EMAIL_1, CORRECT_PHONE_NUMBER_2, CORRECT_COMPANY_NAME_2);

    public static final RegistrationDTO NULL_FIELD_REG_DTO =
            new RegistrationDTO(null, CORRECT_PASSWORD_2,
                    INCORRECT_EMAIL_1, CORRECT_PHONE_NUMBER_2, CORRECT_COMPANY_NAME_2);

    public static final RegistrationDTO CORRECT_REG_DTO_1 =
            new RegistrationDTO(CORRECT_LOGIN_1, CORRECT_PASSWORD_1,
                    CORRECT_EMAIL_1, CORRECT_PHONE_NUMBER, CORRECT_COMPANY_NAME_1);

    public static final RegistrationDTO CORRECT_REG_DTO_2 =
            new RegistrationDTO(CORRECT_LOGIN_2, CORRECT_PASSWORD_2,
                    CORRECT_EMAIL_2, CORRECT_PHONE_NUMBER_2, CORRECT_COMPANY_NAME_2);

    public static final RegistrationDTO INCORRECT_LOGIN_REG_DTO =
            new RegistrationDTO(INCORRECT_LOGIN_1, CORRECT_PASSWORD_2,
                    CORRECT_EMAIL_2, CORRECT_PHONE_NUMBER_2, CORRECT_COMPANY_NAME_2);

    public static final RegistrationDTO INCORRECT_PASSWORD_REG_DTO =
            new RegistrationDTO(CORRECT_LOGIN_1, INCORRECT_PASSWORD_1,
                    CORRECT_EMAIL_2, CORRECT_PHONE_NUMBER_2, CORRECT_COMPANY_NAME_2);

    public static final RegistrationDTO INCORRECT_EMAIL_REG_DTO =
            new RegistrationDTO(CORRECT_LOGIN_1, CORRECT_PASSWORD_2,
                    INCORRECT_EMAIL_1, CORRECT_PHONE_NUMBER_2, CORRECT_COMPANY_NAME_2);

    public static final RegistrationDTO INCORRECT_PHONENUMBER_REG_DTO =
            new RegistrationDTO(CORRECT_LOGIN_1, CORRECT_PASSWORD_2,
                    CORRECT_EMAIL_2, INCORRECT_PHONE_NUMBER_1, CORRECT_COMPANY_NAME_2);

    public static final RegistrationDTO INCORRECT_COMPANY_REG_DTO =
            new RegistrationDTO(CORRECT_LOGIN_1, CORRECT_PASSWORD_2,
                    CORRECT_EMAIL_2, CORRECT_PHONE_NUMBER_2, INCORRECT_COMPANY_NAME_1);

    public static final RegistrationDTO TAKEN_LOGIN_REG_DTO =
            new RegistrationDTO(TAKEN_LOGIN, CORRECT_PASSWORD_2,
                    CORRECT_EMAIL_2, CORRECT_PHONE_NUMBER_2, CORRECT_COMPANY_NAME_2);

    public static final RegistrationDTO TAKEN_EMAIL_REG_DTO =
            new RegistrationDTO(CORRECT_LOGIN_1, CORRECT_PASSWORD_2,
                    TAKEN_EMAIL, CORRECT_PHONE_NUMBER_2, CORRECT_COMPANY_NAME_2);

}
