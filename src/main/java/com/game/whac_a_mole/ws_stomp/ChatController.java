package com.game.whac_a_mole.ws_stomp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final SimpMessagingTemplate template;
    @MessageMapping("/chat")
    @SendTo("/topic/greetings")
    public ChatSendingMsg greeting(ChatReceivedMsg message) throws Exception {

        return new ChatSendingMsg(HtmlUtils.htmlEscape(message.getUserId()),
               HtmlUtils.htmlEscape(message.getContent()));

        //template.convertAndSend("/topic/greetings", new ChatSendingMsg(HtmlUtils.htmlEscape(message.getUserId()),
        //        HtmlUtils.htmlEscape(message.getContent())));
    }
    /*
    @MessageMapping("/bye")
    @SendTo("/topic/greetings")
    public ChatSendingMsg greetingBye(ChatReceivedMsg message) throws Exception {

        return new ChatSendingMsg("Bye, " + HtmlUtils.htmlEscape(message.getContent()) + "!");
    }
    */

}