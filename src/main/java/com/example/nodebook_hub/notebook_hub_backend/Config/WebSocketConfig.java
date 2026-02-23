//package com.example.nodebook_hub.notebook_hub_backend.Config;
//
//
//import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketMessagingAutoConfiguration;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//
//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry config) {
//        config.enableSimpleBroker("/topic"); // frontend will subscribe to this
//        config.setApplicationDestinationPrefixes("/app"); // client will send to this
//    }
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/chat-websocket").setAllowedOriginPatterns("*").withSockJS();
//    }
//}
