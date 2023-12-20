package com.example.boobmessage.controller;

import com.example.boobapimessage.dto.CommentOrReplyDTO;
import com.example.boobcommoncore.response.ServerResponseEntity;
import com.example.boobmessage.Service.MessageService;
import com.example.boobmessage.Service.WebSocket;
import com.example.boobmessage.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@Slf4j
@RestController
public class MessageController {

    @Autowired
    WebSocket  webSocket;

    @Autowired
    MessageService messageService;

    @GetMapping("/sessions")
    public String getSessions() {
        return webSocket.getSessions();
    }

    @PostMapping("/message")
    public String sendMessage(@RequestBody String payload) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        CommentOrReplyDTO commentOrReplyDTO = objectMapper.readValue(payload, CommentOrReplyDTO.class);

        try{

            ObjectMapper mapper = new ObjectMapper();
            // java对象转换为json字符换
            String Json =  mapper.writeValueAsString(commentOrReplyDTO);

            String out = webSocket.sendOneMessage(commentOrReplyDTO.getUserName2(),Json);
            if(out.equals("not online")){ //用户不在线
                log.info("用户"+commentOrReplyDTO.getUserName2()+"不在线");
                Message message = new Message();
                message.setUserName1(commentOrReplyDTO.getUserName1());
                message.setUserName2(commentOrReplyDTO.getUserName2());
                message.setContent(commentOrReplyDTO.getContent());
                message.setObjId(commentOrReplyDTO.getObjId());
                message.setType(commentOrReplyDTO.getType());
                message.setTime(commentOrReplyDTO.getTime());

                // 存数据库
                messageService.save(message);
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

    @PostMapping("/message-get")
    public ServerResponseEntity<?> getMessage(@RequestParam String userName2) {

        // 获取未读消息
        // ...
        log.info("userName2:" + userName2);
        List<Message> messageList = messageService.getMessagesByUserName2(userName2);

        log.info("messageList size:" + messageList.size());
        if(messageList == null){
            return ServerResponseEntity.showFailMsg("database operation error");
        }
        else{
            // 读完删除
//            String deleteOut = messageService.deleteMessagesByUserName2(userName2);
//            log.info(deleteOut);
            return ServerResponseEntity.success(messageList);
        }
    }

}
