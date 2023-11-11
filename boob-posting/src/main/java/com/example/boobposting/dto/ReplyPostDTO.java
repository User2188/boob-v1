package com.example.boobposting.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReplyPostDTO {

    private int commentId;

    private String userName;
    private Date replyTime;
    private String content;
}
