package com.example.whac_a_mole.ws_stomp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatReceivedMsg {
    private String content;

    public ChatReceivedMsg() {
    }
    public ChatReceivedMsg(String content) {
        this.content = content;
    }

}