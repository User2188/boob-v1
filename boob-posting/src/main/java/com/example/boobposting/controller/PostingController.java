package com.example.boobposting.controller;

import com.example.boobcommoncore.response.ServerResponseEntity;
import com.example.boobposting.dto.DeleteDTO;
import com.example.boobposting.dto.PageGetDTO;
import com.example.boobposting.dto.PostingPostDTO;
import com.example.boobposting.model.Posting;
import com.example.boobposting.service.PostingService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/product")
public class PostingController {

    @Autowired
    private PostingService postingService;

    @PostMapping("/posting/post/test")
    public ServerResponseEntity<String> post(PostingPostDTO postingPostDTO) {
        log.info("postingPostDTO : " + postingPostDTO);
        String message = postingService.post(postingPostDTO);
        if(message.equals("post success")){
            return ServerResponseEntity.success("Post Success");
        }
        else{
            return ServerResponseEntity.showFailMsg(message);
        }
    }

    @PostMapping("/posting/post")
    public ServerResponseEntity<String> post(@RequestBody String payload ) throws IOException {
        log.info("payload: " + payload);
        ObjectMapper objectMapper = new ObjectMapper();
        PostingPostDTO postingPostDTO = objectMapper.readValue(payload, PostingPostDTO.class);

        String message = postingService.post(postingPostDTO);
        if(message.equals("post success")){
            return ServerResponseEntity.success("Post Success");
        }
        else{
            return ServerResponseEntity.showFailMsg(message);
        }
    }

    @PostMapping("/posting/delete")
    public ServerResponseEntity<String> deletePosting(
            @RequestBody String payload,
            @RequestHeader("permissionNum") int pernum,
            @RequestHeader("username") String username
    ) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        DeleteDTO deleteDTO = objectMapper.readValue(payload, DeleteDTO.class);
        int postingId = deleteDTO.getPostingId();
        log.info("payload: " + postingId+","+pernum+","+username);

        var posting=postingService.getPostingWithCommentsAndReplies(postingId);
        int userPermission= (int) (pernum/Math.pow(2,2));
        System.out.println(userPermission);
        System.out.println(posting.getUserName());
        if (Objects.equals(username, posting.getUserName()) ||userPermission>0){
            postingService.deletePosting(postingId);
            return ServerResponseEntity.success("Delete Success");
        }
        else{
            return ServerResponseEntity.showFailMsg("No Permission");
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
    public ServerResponseEntity<?> search(@RequestBody Map<String,String> param) {
        String query = param.get("query");
        log.info("query:" + String.valueOf(query));
        List<Posting> postingList = postingService.serach(query);
        log.info("postingList size:" + postingList.size());
        if(postingList == null){
            return ServerResponseEntity.showFailMsg("database operation error");
        }
        else{
            return ServerResponseEntity.success(postingList);
        }
    }

    @PostMapping("/posting/search/test")
    public ServerResponseEntity<?> search(String query) {
        List<Posting> postingList = postingService.serach(query);
        log.info("postingList size:" + postingList.size());
        if(postingList == null){
            return ServerResponseEntity.showFailMsg("database operation error");
        }
        else{
            return ServerResponseEntity.success(postingList);
        }
    }

}
