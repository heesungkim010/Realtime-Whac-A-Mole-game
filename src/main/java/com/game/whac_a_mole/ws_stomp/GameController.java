package com.game.whac_a_mole.ws_stomp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
@Slf4j
@RequiredArgsConstructor
public class GameController implements Runnable{

    private final SimpMessagingTemplate template;
    //TODO: user concurrent hashmap

    @MessageMapping("/game")
    //@SendTo("/topic/game")
    public void receiveGameMsg(GameReceivedMsg message) throws Exception {
        //1. receive msg
        //2. if it is the very first msg : endRound()
        //   else: ignore
    }

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