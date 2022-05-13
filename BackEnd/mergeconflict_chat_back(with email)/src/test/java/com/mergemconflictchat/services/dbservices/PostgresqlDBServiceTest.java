package com.mergemconflictchat.services.dbservices;

import com.mergemconflictchat.model.User;
import com.mergemconflictchat.services.dataservices.ApplicationProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import java.sql.*;
import static com.mergemconflictchat.constants.ConstantsDbScripts.CREATE_USER_SCRIPT;

class PostgresqlDBServiceTest {

    private final Connection connection = Mockito.mock(Connection.class);
    private final PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
    private final User userTest = new User("ggfg", "blabla@gmail.com", "Alfa1234"
            , "+380948534", "DevEdu");
    private final PostgresqlDBService cut = new PostgresqlDBService();

    static Arguments[] createTestArgs() {
        return new Arguments[] {
                Arguments.arguments(1, 1, 1, 1, 1, 1, new User("ggfg", "blabla@gmail.com", "Alfa1234"
                        , "+380948534", "Kuzhka")),
                Arguments.arguments(1, 1, 1, 1, 1, 1, new User("AAa", "asasa@gmail.com", "Akdjd54"
                        , "+380948534", "Rozetka")),
                Arguments.arguments(1, 1, 1, 1, 1, 1, new User("ggfg", "blabla@gmail.com", "Aaaa"
                        , "+38008887", "Fox")),
        };
    }

    @ParameterizedTest
    @MethodSource("createTestArgs")
    void createTest(int timesForSetLogin, int timesForSetEmail, int timesForSetPassword, int timesForSetPhoneNumber,
                    int timesForSetCompany, int timesForExecuteUpdate, User user) {
        try (MockedStatic<DriverManager> mockDriveManager = Mockito.mockStatic(DriverManager.class)) {
            mockDriveManager.when(() -> DriverManager.getConnection(ApplicationProperties.getDbSettings().getUrl(), ApplicationProperties.getDbSettings().getUser(),
                    ApplicationProperties.getDbSettings().getPassword())).thenReturn(connection);
            Mockito.when(connection.prepareStatement(CREATE_USER_SCRIPT)).thenReturn(preparedStatement);

            cut.createUserRecord(user);

            Mockito.verify(preparedStatement, Mockito.times(timesForSetLogin)).setString(1, user.getLogin());
            Mockito.verify(preparedStatement, Mockito.times(timesForSetEmail)).setString(2, user.getPassword());
            Mockito.verify(preparedStatement, Mockito.times(timesForSetPassword)).setString(3, user.getEmail());
            Mockito.verify(preparedStatement, Mockito.times(timesForSetPhoneNumber)).setString(4, user.getPhoneNumber());
            Mockito.verify(preparedStatement, Mockito.times(timesForSetCompany)).setString(5, user.getCompany());
            Mockito.verify(preparedStatement, Mockito.times(timesForExecuteUpdate)).executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void createExceptionTest() {
        try (MockedStatic<DriverManager> mockDriveManager = Mockito.mockStatic(DriverManager.class)) {
            mockDriveManager.when(() -> DriverManager.getConnection(ApplicationProperties.getDbSettings().getUrl(), ApplicationProperties.getDbSettings().getUser(),
                    ApplicationProperties.getDbSettings().getPassword())).thenReturn(connection);
            Mockito.when(connection.prepareStatement(CREATE_USER_SCRIPT)).thenReturn(preparedStatement);
            Mockito.doThrow(SQLException.class).when(preparedStatement).setString(2, userTest.getPassword());

            cut.createUserRecord(userTest);

            Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, userTest.getLogin());
            Mockito.verify(preparedStatement, Mockito.times(1)).setString(2, userTest.getPassword());
            Mockito.verify(preparedStatement, Mockito.times(0)).setString(3, userTest.getEmail());
            Mockito.verify(preparedStatement, Mockito.times(0)).setString(4, userTest.getPhoneNumber());
            Mockito.verify(preparedStatement, Mockito.times(0)).setString(5, userTest.getCompany());
            Mockito.verify(preparedStatement, Mockito.times(0)).executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}