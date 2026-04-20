package com.example.weibo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentCreateDTO {
    private Long postId;
    private Long userId;

    @NotBlank(message = "评论内容不能为空")
    private String content;
}