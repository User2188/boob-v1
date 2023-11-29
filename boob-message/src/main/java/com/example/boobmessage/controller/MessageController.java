package com.example.boobmessage.controller;

import com.example.boobapimessage.dto.CommentOrReplyDTO;
import com.example.boobmessage.Service.WebSocket;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MessageController {

    @Autowired
    WebSocket  webSocket;

    @GetMapping("/sessions")
    public String getSessions() {
        return webSocket.getSessions();
    }

    @PostMapping("/message")
    public String sendMessage(@RequestBody String payload) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        CommentOrReplyDTO commentOrReplyDTO = objectMapper.readValue(payload, CommentOrReplyDTO.class);
//        log.info(commentOrReplyDTO == null ? "null" : commentOrReplyDTO.toString());
//        log.info(commentOrReplyDTO.getContent());
        try{

            ObjectMapper mapper = new ObjectMapper();
            // java对象转换为json字符换
            String Json =  mapper.writeValueAsString(commentOrReplyDTO);

            String out = webSocket.sendOneMessage(commentOrReplyDTO.getUserName2(),Json);
            if(out.equals("not online")){ //用户不在线

                // 存数据库
                // ...

                return out;
            }
            else if(out.equals("success")){
                return out;
            }
            else{ // 尝试发送失败
                return out;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
