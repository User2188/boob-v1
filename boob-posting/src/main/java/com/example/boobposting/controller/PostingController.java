package com.example.boobposting.controller;

import com.example.boobcommoncore.response.ServerResponseEntity;
import com.example.boobposting.dto.PageGetDTO;
import com.example.boobposting.dto.PostingPostDTO;
import com.example.boobposting.model.Posting;
import com.example.boobposting.service.PostingService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Slf4j
@RestController
@RequestMapping("/product")
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

    /*
     * 分页获取帖子
     * 参数:
     *     page: 第几页
     *     size: 每页大小
     */
    @GetMapping("/posting/page")
    public ServerResponseEntity<?> page(PageGetDTO pageGetDTO) {
        List<Posting> postingList = postingService.getPostPageOrderByCreateTimeDesc(pageGetDTO);
        if(postingList == null){
            return ServerResponseEntity.showFailMsg("database operation error");
        }
        else{
            return ServerResponseEntity.success(postingList);
        }
    }

    /*
     * 获取指定帖子的所有内容
     * 参数:
     *     postingId
     */
    @JsonProperty("comments")
    @GetMapping("/posting/all")
    public ServerResponseEntity<?> pageAll(int postingId) {
//        log.info(String.valueOf(postingId));
        Posting postingAll = postingService.getPostingWithCommentsAndReplies(postingId);
        if(postingAll == null){
            return ServerResponseEntity.showFailMsg("database operation error");
        }
        else{
            return ServerResponseEntity.success(postingAll);
        }
    }

    /*
     * 搜索
     * 参数:
     *     postingId
     */
    @PostMapping("/posting/search")
    public ServerResponseEntity<?> search(@RequestParam("query") String query) {
//        log.info("query:" + String.valueOf(query));
        List<Posting> postingList = postingService.serach(query);
//        log.info("postingList size:" + postingList.size());
        if(postingList == null){
            return ServerResponseEntity.showFailMsg("database operation error");
        }
        else{
            return ServerResponseEntity.success(postingList);
        }
    }

}
