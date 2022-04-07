package com.example.whac_a_mole.ws_stomp;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {

        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/bye")
    @SendTo("/topic/greetings")
    public Greeting greetingBye(HelloMessage message) throws Exception {

        return new Greeting("Bye, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}