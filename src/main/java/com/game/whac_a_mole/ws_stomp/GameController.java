package com.game.whac_a_mole.ws_stomp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@Slf4j
public class GameController implements Runnable{

    private final SimpMessagingTemplate template;
    private Map<String, Boolean> firstArrivalMap;
    private Random random;
    private int locNum;
    private int gameMapBoundNum;

    public GameController(SimpMessagingTemplate template) {
        this.template = template;
        this.firstArrivalMap = new HashMap<String, Boolean>();
        this.random = new Random();
        this.gameMapBoundNum = 25;
    }
    //TODO: user concurrent hashmap

    @MessageMapping("/game")
    //@SendTo("/topic/game")
    public void receiveGameMsg(GameReceivedMsg message) throws Exception {
        /*
        1. receive msg
        2. if it is the very first msg of the round : save in hashmap, endRound()
           else: ignore
        */

        synchronized (this){
            if (this.firstArrivalMap.size() == 0) { // the very first msg.
                log.info("start here");
                this.firstArrivalMap.put(message.getUserId(), true);
                notifyFirst(message.getUserId());
            }
            // not the very first msg of the round -> ignore.
        }
    }

    public void startRound() throws Exception {
        //broadcast to client at any point.
        this.template.convertAndSend("/topic/game", new GameSendingMsg("start", this.locNum, "abc"));
    }

    public void endRound() throws Exception {
        //1. re-init hash map
        //2. broadcast to client at any point.
        firstArrivalMap.clear();
        this.template.convertAndSend("/topic/game", new GameSendingMsg("end", this.locNum, "abc"));
    }

    public void notifyFirst(String userId) throws Exception {
        //broadcast to client at any point.
        this.template.convertAndSend("/topic/game", new GameSendingMsg("notifyFirstUser", this.locNum, userId));
    }

    @Override
    public void run() {
        while(true){
            try {
                locNum = random.nextInt(gameMapBoundNum);
                startRound();
                Thread.sleep(2000);
                endRound();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}