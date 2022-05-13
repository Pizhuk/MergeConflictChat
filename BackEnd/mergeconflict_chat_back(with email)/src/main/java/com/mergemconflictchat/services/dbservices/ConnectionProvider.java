package com.mergemconflictchat.services.dbservices;

import com.mergemconflictchat.exceptions.FailedConnectionException;
import com.mergemconflictchat.services.dataservices.ApplicationProperties;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionProvider {

    private static final Logger logger = Logger.getLogger(ConnectionProvider.class);

    public Connection getConnectionToPostgresql() throws FailedConnectionException {

        try {
            Class.forName(ApplicationProperties.getDbSettings().getDriver());
            return DriverManager.getConnection(ApplicationProperties.getDbSettings().getUrl(), ApplicationProperties.getDbSettings().getUser(),
                    ApplicationProperties.getDbSettings().getPassword());
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new FailedConnectionException(e.getMessage());
        }

    }

}
