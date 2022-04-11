package com.game.whac_a_mole;

import com.game.whac_a_mole.ws_stomp.ChatController;
import com.game.whac_a_mole.ws_stomp.GameController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
    private ApplicationContext ac;
    private GameController gameController;

    @Autowired
    public AppConfig(ApplicationContext applicationContext) throws InterruptedException {
        this.ac = applicationContext;
        this.gameController = ac.getBean(GameController.class);
        start();
    }

    public void start(){
        Thread threadChatController = new Thread(this.gameController, "gameController");
        threadChatController.start();
    }
}
