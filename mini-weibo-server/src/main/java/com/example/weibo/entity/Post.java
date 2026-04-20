package com.example.weibo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Post {
    private Long id;
    private Long userId;
    private String content;
    private String image;
    private Integer likeCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean liked;
    private Boolean favorited;
}