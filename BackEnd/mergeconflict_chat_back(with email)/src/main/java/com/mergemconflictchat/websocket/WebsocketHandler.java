
package com.mergemconflictchat.websocket;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class WebsocketHandler extends WebSocketServlet {
    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.register(WebSocketController.class);
    }
}
