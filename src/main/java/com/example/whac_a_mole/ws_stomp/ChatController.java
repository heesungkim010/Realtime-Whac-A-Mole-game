package com.example.whac_a_mole.ws_stomp;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ChatController {

    @MessageMapping("/chat")
    @SendTo("/topic/greetings")
    public ChatSendingMsg greeting(ChatReceivedMsg message) throws Exception {

        return new ChatSendingMsg(HtmlUtils.htmlEscape(message.getContent()));
    }

    @MessageMapping("/bye")
    @SendTo("/topic/greetings")
    public ChatSendingMsg greetingBye(ChatReceivedMsg message) throws Exception {

        return new ChatSendingMsg("Bye, " + HtmlUtils.htmlEscape(message.getContent()) + "!");
    }

}