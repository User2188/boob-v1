package com.example.boobposting.controller;

import com.example.boobcommoncore.response.ServerResponseEntity;
import com.example.boobposting.dao.ReplyDAO;
import com.example.boobposting.dto.PostingPostDTO;
import com.example.boobposting.dto.ReplyGetDTO;
import com.example.boobposting.dto.ReplyGetPageDTO;
import com.example.boobposting.dto.ReplyPostDTO;
import com.example.boobposting.model.Posting;
import com.example.boobposting.model.Reply;
import com.example.boobposting.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping("/reply/post")
    public ServerResponseEntity<String> post(ReplyPostDTO replyPostDTO) {
        String message = replyService.reply(replyPostDTO);
        if(message.equals("reply success")){
            return ServerResponseEntity.success("reply Success");
        }
        else{
            return ServerResponseEntity.showFailMsg(message);
        }
    }

    @PostMapping("/reply/get")
    public ServerResponseEntity<?> get(ReplyGetDTO replyGetDTO) {
        List<Reply> replyList = replyService.getReplyOrderByReplyTimeDesc(replyGetDTO);
        if(replyList == null) {
            return ServerResponseEntity.showFailMsg("database operation error");
        }
        else{
            return ServerResponseEntity.success(replyList);
        }
    }

    @PostMapping("/reply/page")
    public ServerResponseEntity<?> page(ReplyGetPageDTO replyGetPageDTO) {
        List<Reply> replyList = replyService.getReplyPageOrderByReplyTimeDesc(replyGetPageDTO);
        if(replyList == null){
            return ServerResponseEntity.showFailMsg("database operation error");
        }
        else{
            return ServerResponseEntity.success(replyList);
        }
    }

}
