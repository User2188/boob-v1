package com.example.boobposting.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReplyPostDTO {
    // 帖子 or 回复
    private int objId;
    private String userName;
    private Date replyTime;
    private String content;

    // 共三级，一级为帖子，二级为回复，三级为回复的回复。
    // 0->回复posting; 1->回复reply;
    private int replyLevel;
}
