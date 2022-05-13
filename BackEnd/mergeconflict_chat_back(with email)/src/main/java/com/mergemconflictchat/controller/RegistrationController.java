package com.mergemconflictchat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mergemconflictchat.exceptions.RegistrationValidationException;
import com.mergemconflictchat.model.User;
import com.mergemconflictchat.model.entity.RegistrationDTO;
import com.mergemconflictchat.services.dataservices.ApplicationProperties;
import com.mergemconflictchat.services.dbservices.PostgresqlDBService;
import com.mergemconflictchat.util.validation.PasswordEncoder;
import com.mergemconflictchat.util.validation.Validation;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RegistrationController extends HttpServlet {

    public static final String RESPONSE_SUCCESS = "OK";
    public static final String RESPONSE_INCORRECT_DATA = "Incorrect data";

    private static final Logger logger = Logger.getLogger(ApplicationProperties.class);
    private final ObjectMapper objectMapper;
    private final Validation validation;
    private final PasswordEncoder passwordEncoder;
    private final PostgresqlDBService postgresqlDBService;

    public RegistrationController(ObjectMapper objectMapper, PostgresqlDBService postgresqlDBService,
                                  Validation validation, PasswordEncoder passwordEncoder) {
        this.objectMapper = objectMapper;
        this.validation = validation;
        this.passwordEncoder = passwordEncoder;
        this.postgresqlDBService = postgresqlDBService;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BufferedReader bufferedReader = request.getReader();
        Stream<String> stream = bufferedReader.lines();
        String body = stream.collect(Collectors.joining(System.lineSeparator()));

        RegistrationDTO regDTO;

        try {
            regDTO = objectMapper.readValue(body, RegistrationDTO.class);
        } catch (Exception e) {
            response.getWriter().write(RESPONSE_INCORRECT_DATA);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            validation.registrationValidation(regDTO, postgresqlDBService);
        } catch (RegistrationValidationException e) {
            response.getWriter().write(e.getMessage());
            response.setStatus (HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String secretPassword = null;

        try {
            secretPassword = passwordEncoder.encodePassword(regDTO.getPassword());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        User user = new User(regDTO.getLogin(), regDTO.getEmail(), secretPassword, regDTO.getPhoneNumber(), regDTO.getCompany());

        postgresqlDBService.createUserRecord(user);

        response.getWriter().write(RESPONSE_SUCCESS);
        response.setStatus(HttpServletResponse.SC_CREATED);

    }
}



