package com.example.messagingstompwebsocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class GreetingController {


	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}

	@MessageMapping("/private-hello")
	@SendToUser("/topic/private-greetings")
	public Greeting privateGreeting(HelloMessage message, Principal principal) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new Greeting("*whispers* secret hello, to the mysterious and private" +
				" " + HtmlUtils.htmlEscape(message.getName()) + " *whispers*");
	}
}
