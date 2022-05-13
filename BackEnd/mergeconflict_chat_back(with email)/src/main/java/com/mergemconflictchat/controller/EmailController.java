package com.mergemconflictchat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mergemconflictchat.exceptions.EmailValidationException;
import com.mergemconflictchat.model.entity.ActivationCodeDTO;
import com.mergemconflictchat.model.entity.EmailDTO;
import com.mergemconflictchat.services.Mail;
import com.mergemconflictchat.services.SecureKey;
import com.mergemconflictchat.services.dataservices.ApplicationProperties;
import com.mergemconflictchat.util.validation.Validation;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class EmailController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ApplicationProperties.class);
    private ObjectMapper objectMapper;
    private Validation validation;
    private Mail mail;
    private SecureKey secureKey;
    private ActivationCodeDTO activationCodeDTO;
    private Gson gson;

    public EmailController(ObjectMapper objectMapper, Validation validation, Mail mail, SecureKey secureKey,
                           ActivationCodeDTO activationCodeDTO, Gson gson) {
        this.objectMapper = objectMapper;
        this.validation = validation;
        this.mail = mail;
        this.secureKey = secureKey;
        this.activationCodeDTO = activationCodeDTO;
        this.gson = gson;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        int activationCode = secureKey.getSecureKey();
        activationCodeDTO.setActivationCode(activationCode);
        System.out.println(activationCodeDTO + "\n");
        EmailDTO emailDTO = objectMapper.readValue(body, EmailDTO.class);
        System.out.println(emailDTO.toString());

        try {
            validation.mailValidation(emailDTO);
            mail.sendEmail(emailDTO.getEmail(),activationCode);
            resp.getWriter().write(gson.toJson(activationCodeDTO));
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);

        } catch (EmailValidationException e) {
            resp.getWriter().write(e.getMessage());
        }

    }
}
