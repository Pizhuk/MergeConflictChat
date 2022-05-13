package com.mergemconflictchat.services.dbservices;

import com.mergemconflictchat.exceptions.FailedConnectionException;
import com.mergemconflictchat.model.User;
import com.mergemconflictchat.services.dataservices.ApplicationProperties;
import com.mergemconflictchat.websocket.entitywebsocket.Message;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import static com.mergemconflictchat.constants.ConstantsDbScripts.*;

public class PostgresqlDBService {

    private static final Logger logger = Logger.getLogger(PostgresqlDBService.class);
    ConnectionProvider connectionProvider = new ConnectionProvider();
    ApplicationProperties applicationProperties = new ApplicationProperties();

    public void createUserRecord(User p) {

        try (
                Connection connection = connectionProvider.getConnectionToPostgresql();
                PreparedStatement ps = connection.prepareStatement(CREATE_USER_SCRIPT)
        ) {
            ps.setString(1, p.getLogin());
            ps.setString(2, p.getPassword());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getPhoneNumber());
            ps.setString(5, p.getCompany());
            ps.executeUpdate();
        } catch (SQLException | FailedConnectionException e) {
            logger.error(e.getMessage());
        }
    }

    public void saveMessage(Message msg) {

        try (
                Connection connection = connectionProvider.getConnectionToPostgresql();
                PreparedStatement ps = connection.prepareStatement(SAVE_MESSAGE_SCRIPT)
        ) {
            ps.setString(1, msg.getSentTo());
            ps.setString(2, msg.getSentFrom());
            ps.setString(3, msg.getDate());
            ps.setString(4, msg.getText());
            ps.setBoolean(5, msg.isGeneralChat());
            ps.executeUpdate();
        } catch (SQLException | FailedConnectionException e) {
            logger.error(e.getMessage());
        }
    }

    public ArrayList<Message> getPrivateHistoryFromDb(String sent_to, String sent_from) {
        ArrayList<Message> privateMessages = new ArrayList<>();
        try (
                Connection connection = connectionProvider.getConnectionToPostgresql();
                PreparedStatement ps = connection.prepareStatement(READ_PRIVATE_MESSAGES_HISTORY_SCRIPT)
        ) {
            ps.setString(1, sent_to);
            ps.setString(2, sent_from);
            ps.setString(3, sent_from);
            ps.setString(4, sent_to);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                privateMessages.add(new Message(rs.getString("sent_to"), rs.getString("sent_from"
                ), rs.getString("create_time"), rs.getString("text"), rs.getBoolean("general_chat")));
            }
        } catch (SQLException | FailedConnectionException e) {
            logger.error(e.getMessage());
        }
        return privateMessages;
    }


    public ArrayList<Message> getGeneralHistoryFromDb() {
        ArrayList<Message> privateMessages = new ArrayList<>();
        try (
                Connection connection = connectionProvider.getConnectionToPostgresql();
                PreparedStatement ps = connection.prepareStatement(READ_GENERAL_MESSAGES_HISTORY_SCRIPT)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                privateMessages.add(new Message(rs.getString("sent_to"), rs.getString("sent_from"
                ), rs.getString("create_time"), rs.getString("text"), rs.getBoolean("general_chat")));
            }
        } catch (SQLException | FailedConnectionException e) {
            logger.error(e.getMessage());
        }
        return privateMessages;
    }


    public ArrayList<String> getListAllUsersLoginFromDb() {
        ArrayList<String> allUsers = new ArrayList<>();
        try (
                Connection connection = connectionProvider.getConnectionToPostgresql();
                PreparedStatement ps = connection.prepareStatement(READ_ALL_REGISTERED_USERS)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                allUsers.add(rs.getString("login"));
            }
        } catch (SQLException | FailedConnectionException e) {
            logger.error(e.getMessage());
        }
        return allUsers;
    }

    public boolean comparingEncodedPasswordWithDb(String password) {

        int result = -1;

        try (
                Connection connection = connectionProvider.getConnectionToPostgresql();
                PreparedStatement ps = connection.prepareStatement(IF_SAME_PASSWORD_IN_DB);
        ) {
            ps.setString(1, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = Integer.parseInt(rs.getString(1));
            }
            if (result != 0) {
                return true;
            }
        } catch (SQLException | FailedConnectionException ex1) {
            logger.error(ex1.getMessage());
            return false;
        }
        return false;
    }

    public boolean isLoginUniqueInDb(String login) {

        int result = -1;

        try (
                Connection connection = connectionProvider.getConnectionToPostgresql();
                PreparedStatement ps = connection.prepareStatement(IF_SAME_LOGIN_IN_DB);
        ) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = Integer.parseInt(rs.getString(1));
            }
            if (result != 0) {
                return true;
            }
        } catch (SQLException | FailedConnectionException ex1) {
            logger.error(ex1.getMessage());
            return false;
        }
        return false;
    }

    public boolean isSameCredentialsInDb(String login, String password) {

        int result = -1;

        try (
                Connection connection = connectionProvider.getConnectionToPostgresql();
                PreparedStatement ps = connection.prepareStatement(IF_RECORD_WITH_SAME_LOGIN_AND_PASSWORD_IN_DB);
        ) {
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = Integer.parseInt(rs.getString(1));
            }
            if (result != 0) {
                return true;
            }
        } catch (SQLException | FailedConnectionException ex1) {
            logger.error(ex1.getMessage());
            return false;
        }
        return false;
    }

    public boolean isEmailUniqueInDb(String email) {

        int result = -1;

        try (
                Connection connection = connectionProvider.getConnectionToPostgresql();
                PreparedStatement ps = connection.prepareStatement(IF_SAME_EMAIL_IN_DB);
        ) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = Integer.parseInt(rs.getString(1));
            }
            if (result != 0) {
                return true;
            }
        } catch (SQLException | FailedConnectionException ex1) {
            logger.error(ex1.getMessage());
            return false;
        }
        return false;
    }

}
