package com.example.boobposting.controller;

import com.example.boobcommoncore.response.ServerResponseEntity;
import com.example.boobposting.dto.ReplyPostDTO;
import com.example.boobposting.model.Reply;
import com.example.boobposting.service.ReplyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/product")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping("/reply/post/test")
    public ServerResponseEntity<String> post(ReplyPostDTO replyPostDTO) {
        String message = replyService.reply(replyPostDTO);
        if(message.equals("reply success")){
            return ServerResponseEntity.success("reply Success");
        }
        else{
            return ServerResponseEntity.showFailMsg(message);
        }
    }

    @PostMapping("/reply/post")
    public ServerResponseEntity<String> post(@RequestBody String payload ) throws IOException {
        log.info("payload: " + payload);
        ObjectMapper objectMapper = new ObjectMapper();
        ReplyPostDTO replyPostDTO = objectMapper.readValue(payload, ReplyPostDTO.class);
        String message = replyService.reply(replyPostDTO);
        if(message.equals("reply success")){
            return ServerResponseEntity.success("reply Success");
        }
        else{
            return ServerResponseEntity.showFailMsg(message);
        }
    }

    /*
     * 获取指定评论的的所有回复
     * 参数:
     *     commentId
     */
    @GetMapping("/reply/get")
    public ServerResponseEntity<?> get(int commentId) {
        List<Reply> replyList = replyService.getRepliesByCommentId(commentId);
        if(replyList == null) {
            return ServerResponseEntity.showFailMsg("database operation error");
        }
        else{
            return ServerResponseEntity.success(replyList);
        }
    }

}
