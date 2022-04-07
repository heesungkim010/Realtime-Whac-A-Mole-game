package com.example.whac_a_mole.ws_stomp;

public class ChatSendingMsg {

    private String content;

    public ChatSendingMsg() {
    }

    public ChatSendingMsg(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}