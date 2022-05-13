package com.mergemconflictchat.websocket.entitywebsocket;

public class PrivateChat {
    private final String sentTo;
    private final String sentFrom;

    public String getSentTo() {
        return sentTo;
    }

    public String getSentFrom() {
        return sentFrom;
    }

    public PrivateChat(String sentTo, String sentFrom) {
        this.sentTo = sentTo;
        this.sentFrom = sentFrom;
    }


}
