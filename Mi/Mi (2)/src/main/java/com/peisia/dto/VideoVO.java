package com.peisia.dto;

import lombok.Data;

@Data
public class VideoVO {
    private Long vno;
    private String title;
    private String content;
    private String writer;
    private String regDate;
    private String updateDate;
}
