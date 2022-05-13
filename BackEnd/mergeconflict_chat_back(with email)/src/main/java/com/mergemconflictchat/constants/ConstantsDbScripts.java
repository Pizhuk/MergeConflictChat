package com.mergemconflictchat.constants;

import static com.mergemconflictchat.constants.ConstantsTableName.TABLE_NAME_MESSAGES;
import static com.mergemconflictchat.constants.ConstantsTableName.TABLE_NAME_USERS;

public class ConstantsDbScripts {

    public static final String CREATE_USER_SCRIPT = String.format("INSERT INTO %s  (login, password, email, phone_number" +
            ", company) VALUES (?, ?, ?, ?, ?)", TABLE_NAME_USERS);

    public static final String READ_PRIVATE_MESSAGES_HISTORY_SCRIPT = String.format("SELECT * FROM %s WHERE (sent_to = ? AND sent_from = ? ) OR (sent_to = ? AND sent_from = ? ) ORDER BY id ASC", TABLE_NAME_MESSAGES);
    public static final String READ_GENERAL_MESSAGES_HISTORY_SCRIPT = String.format("SELECT * FROM %s WHERE general_chat = true", TABLE_NAME_MESSAGES);
    public static final String SAVE_MESSAGE_SCRIPT = String.format("INSERT INTO %s (sent_to, sent_from, create_time, text, general_chat) VALUES (?, ?, ?, ?, ?)", TABLE_NAME_MESSAGES);
    public static final String READ_ALL_REGISTERED_USERS = String.format("SELECT login FROM users");

    public static final String IF_SAME_PASSWORD_IN_DB = String.format(
            "SELECT CASE WHEN EXISTS (\n" +
                    "    SELECT *\n" +
                    "    FROM " + TABLE_NAME_USERS + "\n" +
                    "    WHERE password = ?\n" +
                    ")\n" +
                    "THEN CAST(1 AS BIT)\n" +
                    "ELSE CAST(0 AS BIT) END");

    public static final String IF_SAME_EMAIL_IN_DB = String.format(
            "SELECT CASE WHEN EXISTS (\n" +
                    "    SELECT *\n" +
                    "    FROM " + TABLE_NAME_USERS + "\n" +
                    "    WHERE email = ?\n" +
                    ")\n" +
                    "THEN CAST(1 AS BIT)\n" +
                    "ELSE CAST(0 AS BIT) END");

    public static final String IF_SAME_LOGIN_IN_DB = String.format(
            "SELECT CASE WHEN EXISTS (\n" +
                    "    SELECT *\n" +
                    "    FROM " + TABLE_NAME_USERS + "\n" +
                    "    WHERE login = ?\n" +
                    ")\n" +
                    "THEN CAST(1 AS BIT)\n" +
                    "ELSE CAST(0 AS BIT) END");

    public static final String IF_RECORD_WITH_SAME_LOGIN_AND_PASSWORD_IN_DB = String.format(
            "SELECT CASE WHEN EXISTS (\n" +
                    "    SELECT *\n" +
                    "    FROM " + TABLE_NAME_USERS + "\n" +
                    "    WHERE login = ? and password = ?\n" +
                    ")\n" +
                    "THEN CAST(1 AS BIT)\n" +
                    "ELSE CAST(0 AS BIT) END");

    private ConstantsDbScripts() {
    }
}
