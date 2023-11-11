package com.example.boobposting.dto;

import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class PostingPostDTO {
    private int userId;
    private String userName;
    private String title;
    private String content;
    private Date time;
}
