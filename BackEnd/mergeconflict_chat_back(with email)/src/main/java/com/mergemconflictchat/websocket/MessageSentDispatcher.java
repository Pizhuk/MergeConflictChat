package com.mergemconflictchat.websocket;

import com.google.gson.Gson;
import com.mergemconflictchat.services.dbservices.PostgresqlDBService;
import com.mergemconflictchat.websocket.entitywebsocket.Envelop;
import com.mergemconflictchat.websocket.entitywebsocket.Message;
import com.mergemconflictchat.websocket.entitywebsocket.PrivateChat;
import com.mergemconflictchat.websocket.entitywebsocket.Topic;
import org.apache.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import java.io.IOException;
import java.net.HttpCookie;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MessageSentDispatcher {

    private static final List<String> onlineUsersList = Collections.synchronizedList(new ArrayList<>());
    private static final Logger logger = Logger.getLogger(MessageSentDispatcher.class);
    private static final Map<String, Session> sessions = new ConcurrentHashMap<>();
    PostgresqlDBService postgresqlDBService = new PostgresqlDBService();
    Gson gson = new Gson();
    private Session session;
    private String login;

    public void prepareSessionForUser(Session session){
        this.session = session;
        List<HttpCookie> cookies = session.getUpgradeRequest().getCookies();
        try {
            login = cookies
                    .stream()
                    .filter(httpCookie -> httpCookie.getName().equals("login"))
                    .map(HttpCookie::getValue)
                    .findFirst()
                    .get();
        } catch (NoSuchElementException e) {
            logger.error(e.getMessage());
        }
        onlineUsersList.add(login);
        sessions.put(login, session);
        sentAllRegisteredUsersInfo(session);
        sentOnlineUsersInfo(onlineUsersList);
        sentHistoryGeneralChat(session);
    }

    public void distributedMessages(Session session,String message){
        Envelop envelop = gson.fromJson(message, Envelop.class);
        switch (envelop.getTopic()) {
            case MESSAGE:
                sentMessageManager(message, envelop.getPayload());
                saveMessageToDb(envelop.getPayload());
                break;
            case HISTORY_PRIVATE:
                sentHistoryPrivateChat(session, envelop.getPayload());
                break;
            case HISTORY_GENERAL:
                sentHistoryGeneralChat(session);
                break;
        }
    }

    public void closeSession(int statusCode, String reason){
        onlineUsersList.remove(login);
        sessions.remove(login);
        sentOnlineUsersInfo(onlineUsersList);

    }

    private void saveMessageToDb(String payload) {
        Message message = gson.fromJson(payload, Message.class);
        postgresqlDBService.saveMessage(message);
    }

    private void sentOnlineUsersInfo(List<String> onlineUsersList) {
        Envelop envelop = new Envelop(Topic.ONLINE_STATUS, gson.toJson(onlineUsersList));
        sendToGeneralChat(gson.toJson(envelop));
    }

    private void sentAllRegisteredUsersInfo(Session session) {
        Envelop envelop = new Envelop(Topic.LIST_ALL_USERS, gson.toJson(postgresqlDBService.getListAllUsersLoginFromDb()));
        sendDataForThisSession(session, gson.toJson(envelop));
    }

    private void sentHistoryPrivateChat(Session session, String payload) {
        PrivateChat privateChat = gson.fromJson(payload, PrivateChat.class);
        ArrayList<Message> allMessages = postgresqlDBService.getPrivateHistoryFromDb(privateChat.getSentTo(), privateChat.getSentFrom());
        Envelop envelop = new Envelop(Topic.HISTORY_PRIVATE, gson.toJson(allMessages));
        sendDataForThisSession(session, gson.toJson(envelop));

    }

    private void sentHistoryGeneralChat(Session session) {
        ArrayList<Message> allMessages = postgresqlDBService.getGeneralHistoryFromDb();
        Envelop envelop = new Envelop(Topic.HISTORY_GENERAL, gson.toJson(allMessages));
        sendDataForThisSession(session, gson.toJson(envelop));
    }

    private void sendDataForThisSession(Session session, String envelop) {
        try {
            session.getRemote().sendString(envelop);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void sentMessageManager(String message, String payload) {
        Message messageDirection = gson.fromJson(payload, Message.class);
        if (messageDirection.isGeneralChat()) {
            sendToGeneralChat(message);
        } else {
            sendToPrivateChat(messageDirection.getSentTo(), message);
        }
    }

    private static void sendToPrivateChat(String sendTo, String message) {
        if (sessions.containsKey(sendTo)) {
            try {
                sessions.get(sendTo).getRemote().sendString(message);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public static void sendToGeneralChat(String message) {
        synchronized (sessions) {
            for (Session s : sessions.values()) {
                try {
                    s.getRemote().sendString(message);
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }
}
