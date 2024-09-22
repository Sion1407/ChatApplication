package com.Websocket.Learning.dto;

import com.Websocket.Learning.enums.MsgType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message  {
    private MsgType type;
    private String content;
    private String sender;


}
