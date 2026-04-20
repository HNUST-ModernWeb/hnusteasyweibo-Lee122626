package com.example.weibo.service;

import com.example.weibo.dto.LoginDTO;
import com.example.weibo.dto.RegisterDTO;
import com.example.weibo.dto.UserUpdateDTO;
import com.example.weibo.entity.User;

public interface AuthService {
    void register(RegisterDTO dto);

    User login(LoginDTO dto);

    User getUserInfo(Long id);

    void updateProfile(UserUpdateDTO dto);
}