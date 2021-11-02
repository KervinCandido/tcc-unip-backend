package br.unip.cc.tcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import br.unip.cc.tcc.model.Message;

@Controller
public class ChatController {
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/send")
	public void sendMessage(@Payload Message message) {
		simpMessagingTemplate.convertAndSend("/user/" + message.getReceiver() + "/message/queue", message);
	}
}
