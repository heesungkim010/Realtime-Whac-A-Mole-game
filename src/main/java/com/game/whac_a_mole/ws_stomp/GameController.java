package com.game.whac_a_mole.ws_stomp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor
public class GameController implements Runnable{

    private final SimpMessagingTemplate template;

    public void startRound() throws Exception {
        //broadcast to client at any point.
        this.template.convertAndSend("/topic/game", new GameSendingMsg("start", 3, "abc"));
    }

    public void endRound() throws Exception {
        //broadcast to client at any point.
        this.template.convertAndSend("/topic/game", new GameSendingMsg("end", 3, "abc"));
    }

    public void notifyFirst() throws Exception {
        //broadcast to client at any point.
        this.template.convertAndSend("/topic/game", new GameSendingMsg("notifyFirstUser", 3, "IAMTHEFIRST"));
    }

    @Override
    public void run() {
        while(true){
            try {
                System.out.println("hi");
                Thread.sleep(3000);
                startRound();
                Thread.sleep(3000);
                notifyFirst();
                Thread.sleep(3000);
                endRound();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}