package com.example.boobposting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Comment implements Serializable {
    @Id
    @GeneratedValue()
    private int id;

    @ManyToOne
    @JoinColumn(name = "postingId")
    @JsonIgnore
    private Posting posting;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("replyTime DESC")
    private List<Reply> replies;

    @Column
    private String userName;
    @Column
    private Date commentTime;
    @Column
    private String content;

    @Column(columnDefinition = "boolean default false")
    private boolean isMarked;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", replies=" + replies.toString() +
                ", userName='" + userName + '\'' +
                ", commentTime=" + commentTime +
                ", content='" + content + '\'' +
                ", isMarked=" + isMarked +
                '}';
    }
}
