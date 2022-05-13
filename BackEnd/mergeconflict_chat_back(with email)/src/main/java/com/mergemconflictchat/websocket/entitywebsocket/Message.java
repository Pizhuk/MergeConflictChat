package com.mergemconflictchat.websocket.entitywebsocket;


public class Message {

    private String sentTo;
    private String sentFrom;
    private String date;
    private String text;
    private boolean generalChat;

    public Message() {

    }

    public Message(String sentTo, String sentFrom, String date, String text, boolean generalChat) {
        this.sentTo = sentTo;
        this.sentFrom = sentFrom;
        this.date = date;
        this.text = text;
        this.generalChat = generalChat;

    }

    public String getSentTo() {
        return sentTo;
    }

    public String getSentFrom() {
        return sentFrom;
    }

    public String getText() {
        return text;
    }

    public boolean isGeneralChat() {
        return generalChat;
    }

    public String getDate() {
        return date;
    }



}
