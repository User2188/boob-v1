package com.example.boobposting.service;

import com.example.boobposting.dao.ReplyDAO;
import com.example.boobposting.dto.ReplyGetDTO;
import com.example.boobposting.dto.ReplyGetPageDTO;
import com.example.boobposting.dto.ReplyPostDTO;
import com.example.boobposting.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    @Autowired
    private ReplyDAO replyDAO;
    public String reply(ReplyPostDTO postingPostDTO){
        try{
            Reply reply = new Reply();

            reply.setObjId(postingPostDTO.getObjId());
            reply.setUserName(postingPostDTO.getUserName());
            reply.setContent(postingPostDTO.getContent());
            reply.setReplyTime(postingPostDTO.getReplyTime());
            reply.setReplyLevel(postingPostDTO.getReplyLevel());
            replyDAO.save(reply);
        }catch(Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
        return "reply success";
    }

    /*
     * 获取指定id的帖子/回复（replyLevel:0-帖子; 1-回复）的所有回复
     */
    public List<Reply> getReplyOrderByReplyTimeDesc(ReplyGetDTO  replyGetDTO) {
        List<Reply> replyList = null;
        try{
            replyList = replyDAO.findByObjIdAndReplyLevel(
                    replyGetDTO.getObjId(), replyGetDTO.getReplyLevel()); // 无内容，返回空而不是null

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

        return replyList;
    }

    /*
     * （分页方式）获取指定id的帖子/回复（replyLevel:0-帖子; 1-回复）的回复
     */
    public List<Reply> getReplyPageOrderByReplyTimeDesc(ReplyGetPageDTO replyGetPageDTO) {
        int page = replyGetPageDTO.getPage();
        int size = replyGetPageDTO.getSize();
        Pageable pageable = PageRequest.of(page, size, Sort.by("replyTime").descending());
        Page<Reply> replyPage = null;
        try{
            replyPage = replyDAO.findByObjIdAndReplyLevel(
                    replyGetPageDTO.getObjId(), replyGetPageDTO.getReplyLevel(),pageable); // 无内容，返回空而不是null

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

        return replyPage.getContent();
    }
}
