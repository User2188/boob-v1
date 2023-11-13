package com.example.boobposting.service;

import com.example.boobapimessage.dto.CommentOrReplyDTO;
import com.example.boobposting.dao.CommentDAO;
import com.example.boobposting.dao.PostingDAO;
import com.example.boobposting.dto.CommentPostDTO;
import com.example.boobposting.model.Comment;
import com.example.boobposting.model.Posting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class CommentService {

    @Autowired
    private PostingDAO  postingDAO;

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    RestTemplate restTemplate;

    public String comment(CommentPostDTO commentPostDTO){
        try{
            Comment comment = new Comment();
            comment.setUserName(commentPostDTO.getUserName());
            comment.setContent(commentPostDTO.getContent());
            comment.setCommentTime(commentPostDTO.getCommentTime());

            Posting posting = postingDAO.findById(commentPostDTO.getPostingId());
            if(posting != null){
                comment.setPosting(posting);
                posting.getComments().add(comment);
                postingDAO.save(posting);

                // 通知被评论者
                String userCommented = postingDAO.findUserNameById(commentPostDTO.getPostingId());
                CommentOrReplyDTO data = new CommentOrReplyDTO();
                data.setUserName1(comment.getUserName());
                data.setUserName2(userCommented);
                data.setObjId(posting.getId());
                data.setType(1);
                data.setContent(comment.getContent());
                data.setTime(comment.getCommentTime());

                // loadBalance+restTemplate实现服务调用
                String out = restTemplate.postForObject(
                        "http://boob-message/message?commentOrReplyDTO=", data, String.class);

                if(out.equals("success")){
                    return "comment success and message sent success";
                }
                else{
                    return "comment success but message sent failed";
                }
            }
            else{
                return "posting not found";
            }

        }catch(Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /*
     * 获取指定post_id的所有评论
     */
    public List<Comment> getCommentsByPostingId(int postingId) {
        try {
            List<Comment> commentList = commentDAO
                    .findByPostingIdOrderByCommentTimeDesc(postingId); // 无内容，返回空而不是null
            return commentList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
