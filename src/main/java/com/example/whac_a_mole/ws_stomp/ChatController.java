package com.example.whac_a_mole.ws_stomp;

import com.example.whac_a_mole.api.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.util.HtmlUtils;

import javax.net.ssl.HandshakeCompletedEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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