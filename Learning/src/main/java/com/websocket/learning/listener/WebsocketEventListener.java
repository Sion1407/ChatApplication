package com.Websocket.Learning.listener;

import com.Websocket.Learning.dto.Message;
import com.Websocket.Learning.enums.MsgType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebsocketEventListener {
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent sessionDisconnectEvent){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionDisconnectEvent.getMessage());
        String username =(String) headerAccessor.getSessionAttributes().get("username");

        if (Objects.nonNull(username)){
            log.info("user disconnected: {}",username);
//            building that user has left the chat
            simpMessageSendingOperations.convertAndSend("/topic/chat", Message.builder().type(MsgType.LEAVE).sender(username).build());
        }
    }
}
