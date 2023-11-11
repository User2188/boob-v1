package com.example.boobposting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Posting implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "posting", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("commentTime DESC")
    private List<Comment> comments;

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
                ",comments=" + comments.toString() +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", replyNum=" + replyNum +
                ", blockId=" + blockId +
                ", tags='" + tags + '\'' +
                '}';
    }
}
