package com.example.boobposting.dao;

import com.example.boobposting.model.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyDAO extends JpaRepository<Reply, Integer> {

    public List<Reply> findByCommentIdOrderByReplyTimeDesc(int CommentId);

}
