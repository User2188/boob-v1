package com.example.boobposting.dao;

import com.example.boobposting.model.Posting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingDAO extends JpaRepository<Posting, Integer> {
    public List<Posting> findByUserName(String username);

    public Page<Posting> findAll(Pageable pageable);

    public Page<Posting> findByUserName(String username, Pageable pageable);

}