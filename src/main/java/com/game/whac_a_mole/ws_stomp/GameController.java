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

    public void greeting() throws Exception {
        //broadcast to client at any point.
        this.template.convertAndSend("/topic/greetings", new ChatSendingMsg("abc", "content"));
    }

    @Override
    public void run() {
        while(true){
            try {
                System.out.println("hi");
                greeting();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}