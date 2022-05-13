package com.mergemconflictchat.websocket.entitywebsocket;

public class Envelop {
    private Topic topic;
    private String payload;


    public Envelop(Topic topic, String payload) {
        this.topic = topic;
        this.payload = payload;
    }

    public Topic getTopic() {
        return topic;
    }

    public String getPayload() {
        return payload;
    }
}
