package com.example.boobposting.service;

import com.example.boobposting.dao.CommentDAO;
import com.example.boobposting.dao.ReplyDAO;
import com.example.boobposting.dto.ReplyPostDTO;
import com.example.boobposting.model.Comment;
import com.example.boobposting.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private ReplyDAO replyDAO;

    public String reply(ReplyPostDTO replyPostDTO) {
        try {
            Reply reply = new Reply();
            reply.setUserName(replyPostDTO.getUserName());
            reply.setContent(replyPostDTO.getContent());
            reply.setReplyTime(replyPostDTO.getReplyTime());

            Comment comment = commentDAO.findById(replyPostDTO.getCommentId()).orElse(null);
            if (comment != null) {
                reply.setComment(comment);
                comment.getReplies().add(reply);
                commentDAO.save(comment);
                return "reply success";
            }
            else{
                return "comment not found";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /*
     * 获取指定comment_id的所有回复
     */
    public List<Reply> getRepliesByCommentId(int commentId) {
        try {
            List<Reply> replyList =  replyDAO
                    .findByCommentIdOrderByReplyTimeDesc(commentId); // 无内容，返回空而不是null
            return replyList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
