package com.example.boobposting.controller;

import com.example.boobcommoncore.response.ServerResponseEntity;
import com.example.boobposting.dto.PostingGetDTO;
import com.example.boobposting.dto.PostingPostDTO;
import com.example.boobposting.model.Posting;
import com.example.boobposting.service.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostingController {

    @Autowired
    private PostingService postingService;

    @PostMapping("/posting/post")
    public ServerResponseEntity<String> post(PostingPostDTO postingPostDTO) {
        String message = postingService.post(postingPostDTO);
        if(message.equals("post success")){
            return ServerResponseEntity.success("Post Success");
        }
        else{
            return ServerResponseEntity.showFailMsg(message);
        }
    }

    @PostMapping("/posting/page")
    public ServerResponseEntity<?> page(PostingGetDTO postingGetDTO) {
        List<Posting> postingList = postingService.getPostPageOrderByCreateTimeDesc(postingGetDTO);
        if(postingList == null){
            return ServerResponseEntity.showFailMsg("database operation error");
        }
        else{
            return ServerResponseEntity.success(postingList);
        }
    }


}
