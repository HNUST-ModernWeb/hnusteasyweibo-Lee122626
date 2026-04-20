package com.example.weibo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostCreateDTO {
    private Long userId;

    @NotBlank(message = "微博内容不能为空")
    private String content;

    private String image;
}