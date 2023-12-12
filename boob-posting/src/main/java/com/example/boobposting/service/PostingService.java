package com.example.boobposting.service;

import com.example.boobposting.dao.PostingDAO;
import com.example.boobposting.dto.PageGetDTO;
import com.example.boobposting.dto.PostingPostDTO;
import com.example.boobposting.model.Posting;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

//@Slf4j
@Service
public class PostingService {

    @Autowired
    private PostingDAO postingDAO;

    public String post(PostingPostDTO postingPostDTO){
        try{
            Posting posting = new Posting();
            posting.setUserName(postingPostDTO.getUserName());
            posting.setTitle(postingPostDTO.getTitle());
            posting.setContent(postingPostDTO.getContent());
            posting.setCreateTime(postingPostDTO.getTime());
            postingDAO.save(posting);
        }catch(Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
        return "post success";
    }

    /*
     * 分页方式获取帖子
     */
    public List<Posting> getPostPageOrderByCreateTimeDesc(PageGetDTO postingGetDTO) {
        int page = postingGetDTO.getPage();
        int size = postingGetDTO.getSize();
//        log.info("page: " + page + ", size: " + size);

        try{
            Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
            Page<Posting> postingPage = postingDAO.findAll(pageable); // 无内容，返回空而不是null
//            log.info("come in");

            return postingPage.getContent();

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /*
     * 获取指定帖子的所有内容
     */
    @JsonProperty("comments")
    public Posting getPostingWithCommentsAndReplies(int postingId) {
        try{
            Posting posting = postingDAO.findById(postingId); // 无内容，返回空而不是null
            return posting;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /*
     * 获取指定帖子的所有内容
     */
    public List<Posting> serach(String content) {
        try{
            List<Posting> postingList = postingDAO
                    .findByTitleContainingOrContentContaining(content, content); // 无内容，返回空而不是null
            return postingList;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Boolean deletePosting(int postingID){
        postingDAO.deleteById(postingID);
        return true;
    }
}
