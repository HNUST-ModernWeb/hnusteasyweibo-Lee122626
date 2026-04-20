package com.example.weibo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateDTO {
    private Long id;

    @NotBlank(message = "昵称不能为空")
    private String nickname;

    private String bio;
}