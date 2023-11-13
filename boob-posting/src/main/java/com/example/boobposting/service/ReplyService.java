package com.example.boobposting.service;

import com.example.boobapimessage.dto.CommentOrReplyDTO;
import com.example.boobposting.dao.CommentDAO;
import com.example.boobposting.dao.PostingDAO;
import com.example.boobposting.dao.ReplyDAO;
import com.example.boobposting.dto.ReplyPostDTO;
import com.example.boobposting.model.Comment;
import com.example.boobposting.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ReplyService {

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private ReplyDAO replyDAO;

    @Autowired
    private RestTemplate restTemplate;

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

                // 通知被评论者
                Comment commented = commentDAO.findById(replyPostDTO.getCommentId()).orElse(null);
                String userReplied = commented.getUserName();
                String userCommented = commented.getPosting().getUserName();

                CommentOrReplyDTO replyDTO = new CommentOrReplyDTO();
                replyDTO.setUserName1(comment.getUserName());
                replyDTO.setUserName2(userReplied);
                replyDTO.setObjId(commented.getId());
                replyDTO.setType(2); // user2回复了user1的comment
                replyDTO.setContent(comment.getContent());
                replyDTO.setTime(comment.getCommentTime());

                CommentOrReplyDTO commentDTO = new CommentOrReplyDTO();
                commentDTO.setUserName1(comment.getUserName());
                commentDTO.setUserName2(userCommented);
                commentDTO.setObjId(commented.getId());
                commentDTO.setType(3); // user2回复了user1的posting中的comment
                commentDTO.setContent(comment.getContent());
                commentDTO.setTime(comment.getCommentTime());

                // loadBalance+restTemplate实现服务调用，通知userReplied和userCommented
                String out1 = restTemplate.postForObject(
                        "http://boob-message/message?commentOrReplyDTO=", replyDTO, String.class);
                String out2 = restTemplate.postForObject(
                        "http://boob-message/message?commentOrReplyDTO=", commentDTO, String.class);

                // 回复成功，返回给发表reply的用户结果
                if(out1.equals("success")){
                    if(out2.equals("success")){
                        return "reply success, message sent to userReplied and userCommented success";
                    }
                    else{
                        return "reply success, but only message sent to userReplied success";
                    }
                }
                else{
                    if(out2.equals("success")){
                        return "reply success, but only message sent to userCommented success";
                    }
                    else{
                        return "reply success, but message sent to userCommented and userReplied both failed";
                    }
                }
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
