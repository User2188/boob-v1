package com.example.boobposting.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentPostDTO {

    private int postingId;

    private String userName;
    private Date commentTime;
    private String content;
}
