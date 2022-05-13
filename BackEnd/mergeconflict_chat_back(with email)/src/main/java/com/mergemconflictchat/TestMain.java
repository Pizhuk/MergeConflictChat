package com.mergemconflictchat;

import com.mergemconflictchat.exceptions.RegistrationValidationException;
import com.mergemconflictchat.model.entity.RegistrationDTO;
import com.mergemconflictchat.services.dbservices.PostgresqlDBService;
import com.mergemconflictchat.util.validation.Validation;

public class TestMain {

    public static void main(String[] args) {

        PostgresqlDBService postgresqlDBService = new PostgresqlDBService();
        RegistrationDTO regDTO = new RegistrationDTO(null, "!password", "someemail@email.com",
                "+380970004444", "company");
        Validation validation = new Validation();

        try {
            validation.registrationValidation(regDTO, postgresqlDBService);
        } catch (RegistrationValidationException e) {
            System.out.println(e.getMessage());
        }

    }


}
