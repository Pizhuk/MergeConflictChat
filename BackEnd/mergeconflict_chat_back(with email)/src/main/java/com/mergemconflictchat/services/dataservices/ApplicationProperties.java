package com.mergemconflictchat.services.dataservices;

import com.mergemconflictchat.services.dbservices.DbSettings;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import static com.mergemconflictchat.constants.ConstantsProperties.*;

public class ApplicationProperties {

    private static final Logger logger = Logger.getLogger(ApplicationProperties.class);
    private static DbSettings dbSettings;
    private static String salt;
    private static int serverPort;

    public ApplicationProperties() {
        extractData();
    }
    public static DbSettings getDbSettings() {
        return dbSettings;
    }
    public static String getSalt() {
        return salt;
    }
    public static int getServerPort() {
        return serverPort;
    }

    private static void extractData() {

        try (InputStream input = ApplicationProperties.class.getClassLoader().getResourceAsStream(NAME_FILE_PROPERTIES)) {
            Properties prop = new Properties();
            prop.load(input);
            salt = prop.getProperty(SALT_FOR_PASSWORDS);
            serverPort = Integer.parseInt(prop.getProperty(SERVER_PORT));
            dbSettings = new DbSettings(prop.getProperty(USER_DB_POSTGRES), prop.getProperty(PASSWORD_DB_POSTGRES),
                    prop.getProperty(URL_DB_POSTGRES), prop.getProperty(DRIVER_DB_POSTGRES));

        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }

    }

}
