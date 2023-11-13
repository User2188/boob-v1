package com.example.boobposting.dao;

import com.example.boobposting.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDAO extends JpaRepository<Comment, Integer> {
    public List<Comment> findByPostingIdOrderByCommentTimeDesc(int postingId);
}
