package com.example.weibo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String bio;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}