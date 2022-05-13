package com.mergemconflictchat.util.validation;

import com.mergemconflictchat.exceptions.AuthValidationException;
import com.mergemconflictchat.exceptions.RegistrationValidationException;
import com.mergemconflictchat.model.entity.AuthDTO;
import com.mergemconflictchat.model.entity.RegistrationDTO;
import com.mergemconflictchat.services.dataservices.ApplicationProperties;
import com.mergemconflictchat.services.dbservices.PostgresqlDBService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static com.mergemconflictchat.testdata.controllerdata.ValidationTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidationTest {

    private final Validation cut = new Validation();

    ApplicationProperties confing = new ApplicationProperties();

    private final static PostgresqlDBService postgresqlDBService = new PostgresqlDBService();
    private final static PasswordEncoder passwordEncoder = new PasswordEncoder();


    static Arguments[] authValidationExceptionTestArgs() {
        return new Arguments[]{
                Arguments.arguments(new AuthDTO("markomark", "Q!werty"), postgresqlDBService, passwordEncoder),
                Arguments.arguments(new AuthDTO("logPlayer1", "pasPlayer12"), postgresqlDBService, passwordEncoder)
        };
    }

    @ParameterizedTest
    @MethodSource("authValidationExceptionTestArgs")
    void authValidationExceptionTest(AuthDTO authDTO, PostgresqlDBService postgresqlDBService,
                                     PasswordEncoder passwordEncoder) {
        assertThrows(AuthValidationException.class, () -> {
            cut.authValidation(authDTO, postgresqlDBService, passwordEncoder);
        });
    }

    static Arguments[] registrationValidationExceptionTestArgs() {
        return new Arguments[]{
                Arguments.arguments(NULL_FIELD_REG_DTO, postgresqlDBService),
                Arguments.arguments(EMPTY_FIELD_REG_DTO, postgresqlDBService),
                Arguments.arguments(INCORRECT_LOGIN_REG_DTO, postgresqlDBService),
                Arguments.arguments(INCORRECT_PASSWORD_REG_DTO, postgresqlDBService),
                Arguments.arguments(INCORRECT_EMAIL_REG_DTO, postgresqlDBService),
                Arguments.arguments(INCORRECT_PHONENUMBER_REG_DTO, postgresqlDBService),
                Arguments.arguments(INCORRECT_COMPANY_REG_DTO, postgresqlDBService)
        };
    }

    @ParameterizedTest
    @MethodSource("registrationValidationExceptionTestArgs")
    void registrationValidationExceptionTest(RegistrationDTO regDTO, PostgresqlDBService postgresqlDBService) {

        assertThrows(RegistrationValidationException.class,
                () -> {
                    cut.registrationValidation(regDTO, postgresqlDBService);
                });
    }

}