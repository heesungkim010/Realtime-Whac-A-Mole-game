package com.game.whac_a_mole.ws_stomp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatSendingMsg {

    private String userId;
    private String content;

    public ChatSendingMsg() {
    }

    public ChatSendingMsg(String userId, String content) {
        this.userId = userId;
        this.content = content;
    }
}