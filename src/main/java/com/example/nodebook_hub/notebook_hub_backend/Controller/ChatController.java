//package com.example.nodebook_hub.notebook_hub_backend.Controller;
//
//import com.example.nodebook_hub.notebook_hub_backend.Entity.ChatMessage;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.LocalDateTime;
//
//@RestController
//public class ChatController {
//    @MessageMapping("/send") // /app/send
//    @SendTo("/topic/messages")
//    public ChatMessage send(ChatMessage message) throws Exception {
//        System.out.println("HELLO");
//        System.out.print("MESSAGE"+message);
//        message.setTimestamp(LocalDateTime.now().toString());
//        return message;
//    }
//}
