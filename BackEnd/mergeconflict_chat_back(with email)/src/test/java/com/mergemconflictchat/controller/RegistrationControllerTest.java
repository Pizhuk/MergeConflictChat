package com.mergemconflictchat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mergemconflictchat.exceptions.RegistrationValidationException;
import com.mergemconflictchat.model.entity.RegistrationDTO;
import com.mergemconflictchat.services.dbservices.PostgresqlDBService;
import com.mergemconflictchat.util.validation.PasswordEncoder;
import com.mergemconflictchat.util.validation.Validation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static com.mergemconflictchat.testdata.controllerdata.RegistrationControllerTestData.*;

class RegistrationControllerTest {

    private ObjectMapper objectMapper = Mockito.mock(ObjectMapper.class);
    private Validation validation = Mockito.mock(Validation.class);
    private BufferedReader reader = Mockito.mock(BufferedReader.class);
    private Stream<String> stream = Mockito.mock(Stream.class);
    private PrintWriter writer = Mockito.mock(PrintWriter.class);
    private Collector collector = Mockito.mock(Collector.class);
    private PostgresqlDBService postgresqlDBService = Mockito.mock(PostgresqlDBService.class);
    private PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
    private HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    private HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

    RegistrationController cut = new RegistrationController(objectMapper, postgresqlDBService, validation, passwordEncoder);

    static Arguments[] doPostExceptionsTestArgs() {
        return new Arguments[]{

                Arguments.arguments(BODY_INCORRECT_LOGIN, INCORRECT_LOGIN_REG_DTO_1, MSG_INVALID_INCORRECT_LOGIN, 1, 1, 400),
                Arguments.arguments(BODY_INCORRECT_PASSWORD, INCORRECT_PASSWORD_REG_DTO_1, MSG_INVALID_INCORRECT_PASSWORD, 1, 1, 400),
                Arguments.arguments(BODY_INCORRECT_EMAIL, INCORRECT_EMAIL_REG_DTO_1, MSG_INVALID_INCORRECT_EMAIL, 1, 1, 400),
                Arguments.arguments(BODY_INCORRECT_NUMBER, INCORRECT_PHONE_NUMBER_REG_DTO_1, MSG_INVALID_INCORRECT_NUMBER, 1, 1, 400),
                Arguments.arguments(BODY_INCORRECT_COMPANY, INCORRECT_COMPANY_NAME_REG_DTO_1, MSG_INVALID_COMPANY_EXCEEDED_CHARACTERS, 1, 1, 400),

        };
    }

    @MethodSource("doPostExceptionsTestArgs")
    @ParameterizedTest
    void doPostExceptionsTest(String body, RegistrationDTO regDTO,
                    String validateExceptionMessage, int times1, int times2, int status)
            throws IOException, ServletException {

        try (MockedStatic<Collectors> mockedCollectors = Mockito.mockStatic(Collectors.class)) {

            Mockito.when(request.getReader()).thenReturn(reader);
            Mockito.when(reader.lines()).thenReturn(stream);
            mockedCollectors.when(() -> Collectors.joining(System.lineSeparator())).thenReturn(collector);
            Mockito.when(stream.collect(Collectors.joining(System.lineSeparator()))).thenReturn(body);
            Mockito.when(objectMapper.readValue(body, RegistrationDTO.class)).thenReturn(regDTO);
            Mockito.when(passwordEncoder.encodePassword(regDTO.getPassword())).thenReturn(HASHED_PASSWORD);
            Mockito.when(response.getWriter()).thenReturn(writer);
            Mockito.doThrow(new RegistrationValidationException(validateExceptionMessage)).when(validation).registrationValidation(regDTO, postgresqlDBService);

            cut.doPost(request, response);

            Mockito.verify(writer, Mockito.times(times1)).write(validateExceptionMessage);
            Mockito.verify(response, Mockito.times(times2)).setStatus(status);
        }
    }
}