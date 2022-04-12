package com.game.whac_a_mole.ws_stomp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameReceivedMsg {
    private String userId;

    public GameReceivedMsg(String userId) {
        this.userId = userId;
    }
}
