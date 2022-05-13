package com.mergemconflictchat.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mergemconflictchat.controller.AuthController;
import com.mergemconflictchat.controller.EmailController;
import com.mergemconflictchat.controller.RegistrationController;
import com.mergemconflictchat.model.entity.ActivationCodeDTO;
import com.mergemconflictchat.services.CORSFilter;
import com.mergemconflictchat.services.Mail;
import com.mergemconflictchat.services.SecureKey;
import com.mergemconflictchat.services.dataservices.ApplicationProperties;
import com.mergemconflictchat.services.dbservices.PostgresqlDBService;
import com.mergemconflictchat.util.validation.PasswordEncoder;
import com.mergemconflictchat.util.validation.Validation;
import com.mergemconflictchat.websocket.WebsocketHandler;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class ServerConfiguration {

    public Server buildServer() {

        ApplicationProperties configGetter = new ApplicationProperties(); //
        Server server = new Server();
        ObjectMapper objectMapper = new ObjectMapper();
        PostgresqlDBService postgresqlDBService = new PostgresqlDBService();
        Validation validation = new Validation();
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        SecureKey secureKey = new SecureKey();
        Gson gson = new Gson();

        Mail mail = new Mail();
        ActivationCodeDTO activationCodeDTO = new ActivationCodeDTO();
        EmailController emailController = new EmailController(objectMapper, validation, mail, secureKey, activationCodeDTO, gson);
        RegistrationController registrationController = new RegistrationController(objectMapper, postgresqlDBService,
                validation, passwordEncoder);
        ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setPort(ApplicationProperties.getServerPort());
        server.setConnectors(new ServerConnector[]{serverConnector});

        ServletContextHandler context = new ServletContextHandler();
        ServletHolder regServletHolder = new ServletHolder("regHolder", registrationController);
        ServletHolder emaServletHolder = new ServletHolder("emaHolder", emailController);
        ServletHolder authServletHolder = new ServletHolder("authHolder",
                new AuthController(objectMapper, validation, passwordEncoder, postgresqlDBService));

        ServletHolder ws = new ServletHolder("webSocketHolder", WebsocketHandler.class);

        context.addServlet(authServletHolder, "/auth");
        context.addServlet(emaServletHolder, "/ema");
        context.addServlet(regServletHolder, "/reg");
        context.addServlet(ws, "/chat");
        context.addFilter(CORSFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));

        HandlerCollection hc = new HandlerCollection();
        hc.setHandlers(new Handler[]{context});
        server.setHandler(hc);
        return server;
    }
}

