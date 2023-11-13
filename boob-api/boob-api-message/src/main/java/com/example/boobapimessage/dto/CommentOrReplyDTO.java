package com.example.boobapimessage.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class CommentOrReplyDTO implements Serializable {
    private String userName1; // 谁评论的
    private String userName2; // 评论了谁

    private int objId; // 评论了你的什么
    private int type;

    private String content; // 评论了什么
    private Date time; // 什么时候

    @Override
    public String toString() {
        return "CommentOrReplyDTO{" +
                "userName1='" + userName1 + '\'' +
                ", userName2='" + userName2 + '\'' +
                ", objId=" + objId +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }
}
