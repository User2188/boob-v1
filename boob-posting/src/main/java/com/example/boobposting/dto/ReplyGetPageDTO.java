package com.example.boobposting.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyGetPageDTO {
    private int objId;
    private int replyLevel;

    private int page;
    private int size;
}
