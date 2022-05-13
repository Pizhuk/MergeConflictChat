package com.mergemconflictchat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mergemconflictchat.exceptions.AuthValidationException;
import com.mergemconflictchat.model.entity.AuthDTO;
import com.mergemconflictchat.services.dataservices.ApplicationProperties;
import com.mergemconflictchat.services.dbservices.PostgresqlDBService;
import com.mergemconflictchat.util.validation.PasswordEncoder;
import com.mergemconflictchat.util.validation.Validation;
import org.apache.log4j.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class AuthController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ApplicationProperties.class);
    private ObjectMapper objectMapper;
    private Validation validation;
    private PostgresqlDBService postgresqlDBService;
    private PasswordEncoder passwordEncoder;

    public AuthController(ObjectMapper objectMapper, Validation validation, PasswordEncoder passwordEncoder,
                          PostgresqlDBService postgresqlDBService) {
        this.objectMapper = objectMapper;
        this.validation = validation;
        this.postgresqlDBService = postgresqlDBService;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        logger.info("Init auth");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        AuthDTO authDTO = objectMapper.readValue(body, AuthDTO.class);

        try {
            validation.authValidation(authDTO, postgresqlDBService, passwordEncoder);
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
        } catch (AuthValidationException e) {
            resp.getWriter().write(e.getMessage());
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }

}
