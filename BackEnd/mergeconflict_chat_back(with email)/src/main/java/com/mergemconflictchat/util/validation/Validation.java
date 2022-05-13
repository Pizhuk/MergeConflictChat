package com.mergemconflictchat.util.validation;

import com.mergemconflictchat.exceptions.AuthValidationException;
import com.mergemconflictchat.exceptions.EmailValidationException;
import com.mergemconflictchat.exceptions.RegistrationValidationException;
import com.mergemconflictchat.model.entity.AuthDTO;
import com.mergemconflictchat.model.entity.EmailDTO;
import com.mergemconflictchat.model.entity.RegistrationDTO;
import com.mergemconflictchat.services.dbservices.PostgresqlDBService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.mergemconflictchat.constants.ConstantsRegex.*;
import static com.mergemconflictchat.constants.ExceptionMessages.*;
import static com.mergemconflictchat.constants.ConstantsRegistration.*;

public class Validation {

    public void mailValidation(EmailDTO emailDTO) {

        if (!isCorrect(emailDTO.getEmail(), REGEX_CORRECT_EMAIL)) {
            throw new EmailValidationException(MSG_INVALID_INCORRECT_EMAIL);
        }
    }

    public void authValidation(AuthDTO authDTO, PostgresqlDBService postgresqlDBService,
                               PasswordEncoder passwordEncoder) throws AuthValidationException {

        if (!isSameRecordInDb(authDTO.getLogin(), authDTO.getPassword(), postgresqlDBService, passwordEncoder)) {
            System.out.println(MSG_INVALID_CREDENTIALS);
            throw new AuthValidationException(MSG_INVALID_CREDENTIALS);
        }

    }

    public void registrationValidation(RegistrationDTO regDTO, PostgresqlDBService postgresqlDBService)
            throws RegistrationValidationException {

        if(anyFieldNull(regDTO)) {
            throw new RegistrationValidationException(MSG_INVALID_NULL);
        }

        if (anyFieldEmpty(regDTO)) {
            throw new RegistrationValidationException(MSG_INVALID_EMPTY_FIELD);
        }

        if (!isCorrect(regDTO.getLogin(), REGEX_CORRECT_LOGIN)) {
            throw new RegistrationValidationException(MSG_INVALID_INCORRECT_LOGIN);
        }

        if (!isCorrect(regDTO.getPassword(), REGEX_CORRECT_PASSWORD_2)) {
            throw new RegistrationValidationException(MSG_INVALID_INCORRECT_PASSWORD);
        }

        if (!isCorrect(regDTO.getEmail(), REGEX_CORRECT_EMAIL)) {
            throw new RegistrationValidationException(MSG_INVALID_INCORRECT_EMAIL);
        }

        if (!isCorrect(regDTO.getPhoneNumber(), REGEX_CORRECT_PHONE_NUMBER)) {
            throw new RegistrationValidationException(MSG_INVALID_INCORRECT_NUMBER);
        }

        if (postgresqlDBService.isLoginUniqueInDb(regDTO.getLogin())) {
            System.out.println(MSG_TAKEN_LOGIN);
            throw new RegistrationValidationException(MSG_TAKEN_LOGIN);
        }

        if (postgresqlDBService.isEmailUniqueInDb(regDTO.getEmail())) {
            System.out.println(MSG_TAKEN_EMAIL);
            throw new RegistrationValidationException(MSG_TAKEN_EMAIL);
        }

        if (!isCorrectCompanyName(regDTO.getCompany())) {
            throw new RegistrationValidationException(MSG_INVALID_COMPANY_EXCEEDED_CHARACTERS);
        }



    }

    private boolean isSameRecordInDb(String login, String password, PostgresqlDBService postgresqlDBService,
                                     PasswordEncoder passwordEncoder) {
        return postgresqlDBService.isSameCredentialsInDb(login, passwordEncoder.encodePassword(password));
    }

    private boolean checkIfUniqueLogin(String login, PostgresqlDBService postgresqlDBService) {
        return postgresqlDBService.isLoginUniqueInDb(login);
    }

    private boolean checkIfProperPassword(String password, PostgresqlDBService postgresqlDBService, PasswordEncoder passwordEncoder) {
        return postgresqlDBService.comparingEncodedPasswordWithDb(passwordEncoder.encodePassword(password));
    }

    private boolean anyFieldEmpty(RegistrationDTO regDTO) {
        return regDTO.getLogin().isEmpty() || regDTO.getPassword().isEmpty() || regDTO.getEmail().isEmpty()
                || regDTO.getPhoneNumber().isEmpty() || regDTO.getCompany().isEmpty();
    }

    private boolean anyFieldNull(RegistrationDTO regDTO) {
        return regDTO.getLogin() == null || regDTO.getPassword() == null || regDTO.getEmail() == null
                || regDTO.getPhoneNumber() == null || regDTO.getCompany() == null;
    }

    private boolean isCorrect(String checkField, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(checkField);
        return matcher.matches();
    }

    private boolean isCorrectCompanyName(String companyName) {
        return companyName.length() < MAX_SYMBOLS;
    }

}