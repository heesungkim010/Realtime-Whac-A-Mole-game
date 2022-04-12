package com.game.whac_a_mole.ws_stomp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameSendingMsg {
    private String msgType; // start, end, notifyFirstUser
    private int gameMapLoc; // 0~24
    private String userId; // userId of first person who clicked

    public GameSendingMsg(String msgType, int gameMapLoc, String userId) {
        this.msgType = msgType;
        this.gameMapLoc = gameMapLoc;
        this.userId = userId;
    }
}
