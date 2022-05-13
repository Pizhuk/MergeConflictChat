package com.mergemconflictchat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mergemconflictchat.model.entity.AuthDTO;
import com.mergemconflictchat.services.dbservices.PostgresqlDBService;
import com.mergemconflictchat.util.validation.PasswordEncoder;
import com.mergemconflictchat.util.validation.Validation;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    Stream<String> stream = mock(Stream.class);
    Collector collector = mock(Collector.class);

    HttpServletResponse resp = mock(HttpServletResponse.class);
    HttpServletRequest req = mock(HttpServletRequest.class);
    PostgresqlDBService db = mock(PostgresqlDBService.class);
    BufferedReader bufferedReader = mock(BufferedReader.class);
    ObjectMapper objectMapper = mock(ObjectMapper.class);
    Validation validation = mock(Validation.class);
    PasswordEncoder pas = mock(PasswordEncoder.class);

    AuthDTO authDTO = new AuthDTO("alyona", "Dev123");
    AuthController cut = new AuthController(objectMapper, validation, pas, db);

    @Test
    void doPostTest() throws IOException, ServletException {

        String body = "{\"login\":\"alyona\",\"password\":\"Dev123\"}";
        try (MockedStatic<Collectors> mockedCollectors = mockStatic(Collectors.class)) {
            when(req.getReader()).thenReturn(bufferedReader);
            when(bufferedReader.lines()).thenReturn(stream);
            mockedCollectors.when(() -> Collectors.joining(System.lineSeparator())).thenReturn(collector);
            when(stream.collect(Collectors.joining(System.lineSeparator()))).thenReturn(body);
            when(objectMapper.readValue(body, AuthDTO.class)).thenReturn(authDTO);

            cut.doPost(req, resp);

            verify(validation, times(1)).authValidation(authDTO, db, pas);
            verify(resp, times(1)).setStatus(HttpServletResponse.SC_ACCEPTED);
        }
    }

}