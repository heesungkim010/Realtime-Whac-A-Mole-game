package com.game.whac_a_mole.ws_stomp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatReceivedMsg {
    private String userId;
    private String content;

    public ChatReceivedMsg() {
    }
    public ChatReceivedMsg(String userId, String content) {
        this.userId = userId;
        this.content = content;
    }

}