package com.example.boobposting.service;

import com.example.boobposting.dao.PostingDAO;
import com.example.boobposting.dto.PostingGetDTO;
import com.example.boobposting.dto.PostingPostDTO;
import com.example.boobposting.model.Posting;
import com.example.boobposting.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostingService {

    @Autowired
    private PostingDAO postingDAO;

    public String post(PostingPostDTO postingPostDTO){
        try{
            Posting posting = new Posting();
            posting.setUserName(postingPostDTO.getUsername());
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
     * （分页方式）获取所有帖子
     */
    public List<Posting> getPostPageOrderByCreateTimeDesc(PostingGetDTO postingGetDTO) {
        int page = postingGetDTO.getPage();
        int size = postingGetDTO.getSize();

        Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
        Page<Posting> postingPage = null;
        try{
            postingPage = postingDAO.findAll(pageable); // 无内容，返回空而不是null

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

        return postingPage.getContent();
    }
}
