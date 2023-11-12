package com.example.boobposting.dao;

import com.example.boobposting.model.Posting;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostingDAO extends JpaRepository<Posting, Integer> {
    public List<Posting> findByUserName(String username);

    public Page<Posting> findAll(Pageable pageable);

    public Page<Posting> findByUserName(String username, Pageable pageable);

    @JsonProperty("comments")
    @EntityGraph(attributePaths = {"comments", "comments.replies"})
    public Posting findById(int id);


    public List<Posting> findByTitleContainingOrContentContaining(String query1, String query2);

    @Query("SELECT p.userName FROM Posting p WHERE p.id = ?1")
    public String findUserNameById(int id);

}