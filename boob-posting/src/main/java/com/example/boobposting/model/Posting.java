package com.example.boobposting.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Posting {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String userName;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Date createTime;

    @Column(columnDefinition = "int default 0")
    private int replyNum;

    @Column(columnDefinition = "int default 0")
    private int blockId;

    @Column(columnDefinition = "character varying(100) default ''")
    private String tags;

    @Override
    public String toString() {
        return "Posting{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", replyNum=" + replyNum +
                ", blockId=" + blockId +
                ", tags='" + tags + '\'' +
                '}';
    }
}
