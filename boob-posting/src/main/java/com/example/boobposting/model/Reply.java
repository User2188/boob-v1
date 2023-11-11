package com.example.boobposting.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Reply {
    @Id
    @GeneratedValue()
    private int id;

    // 帖子 or 回复
    @Column(columnDefinition = "int default 0")
    private int objId;
    @Column
    private String userName;
    @Column
    private Date replyTime;
    @Column
    private String content;

    // 共三级，一级为帖子，二级为回复，三级为回复的回复。
    // 0->回复posting; 1->回复reply;
    @Column
    private int replyLevel;

    // 发帖人(提问者)选择有帮助的回复标记
    @Column(columnDefinition = "boolean default false")
    private boolean isMarked;

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", objId=" + objId +
                ", userName='" + userName + '\'' +
                ", replyTime=" + replyTime +
                ", content='" + content + '\'' +
                ", replyLevel=" + replyLevel +
                ", isMarked=" + isMarked +
                '}';
    }
}
