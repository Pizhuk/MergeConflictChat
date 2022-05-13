package com.mergemconflictchat.websocket;

import org.apache.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import javax.websocket.EncodeException;
import java.io.IOException;

@WebSocket(maxIdleTime = 1000000)
public class WebSocketController extends WebSocketHandler {

    private static final Logger logger = Logger.getLogger(WebSocketController.class);
    MessageSentDispatcher messageSentDispatcher = new MessageSentDispatcher();

    @OnWebSocketConnect
    public void onConnect(Session session) {
        session.setIdleTimeout(Long.MAX_VALUE);
     messageSentDispatcher.prepareSessionForUser(session);
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String message) throws IOException, EncodeException {
        messageSentDispatcher.distributedMessages(session, message);
    }

    @Override
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        webSocketServletFactory.register(WebSocketController.class);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {

    }

    @OnWebSocketError
    public void onError(Throwable t) {
        logger.error(t.getMessage());
    }
}
