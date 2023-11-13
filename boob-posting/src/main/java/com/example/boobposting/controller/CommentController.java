package com.example.boobposting.controller;

import com.example.boobcommoncore.response.ServerResponseEntity;
import com.example.boobposting.dto.CommentPostDTO;
import com.example.boobposting.model.Comment;
import com.example.boobposting.service.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/product")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/comment/post/test")
    public ServerResponseEntity<String> post(CommentPostDTO commentPostDTO) {
        String message = commentService.comment(commentPostDTO);
        if(message.equals("comment success")){
            return ServerResponseEntity.success("comment Success");
        }
        else{
            return ServerResponseEntity.showFailMsg(message);
        }
    }

    @PostMapping("/comment/post")
    public ServerResponseEntity<String> post(@RequestBody String payload ) throws IOException {
        log.info("payload: " + payload);
        ObjectMapper objectMapper = new ObjectMapper();
        CommentPostDTO commentPostDTO = objectMapper.readValue(payload, CommentPostDTO.class);

        String message = commentService.comment(commentPostDTO);
        if(message.equals("comment success")){
            return ServerResponseEntity.success("comment Success");
        }
        else{
            return ServerResponseEntity.showFailMsg(message);
        }
    }


    /*
     * 获取指定帖子的所有评论
     * 参数:
     *     postingId
     */
    @GetMapping("/comment/get")
    public ServerResponseEntity<?> get(int postingId) {
        List<Comment> commentList = commentService.getCommentsByPostingId(postingId);
        if(commentList == null) {
            return ServerResponseEntity.showFailMsg("database operation error");
        }
        else{
            return ServerResponseEntity.success(commentList);
        }
    }
}
