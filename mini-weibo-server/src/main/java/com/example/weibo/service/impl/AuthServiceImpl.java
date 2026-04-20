package com.example.weibo.service.impl;

import com.example.weibo.dto.LoginDTO;
import com.example.weibo.dto.RegisterDTO;
import com.example.weibo.dto.UserUpdateDTO;
import com.example.weibo.entity.User;
import com.example.weibo.mapper.UserMapper;
import com.example.weibo.service.AuthService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;

    public AuthServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void register(RegisterDTO dto) {
        User existUser = userMapper.selectByUsername(dto.getUsername());
        if (existUser != null) {
            throw new RuntimeException("用户名已存在");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setNickname(dto.getNickname());
        user.setBio("这个人很懒，还没有写简介");

        userMapper.insert(user);
    }

    @Override
    public User login(LoginDTO dto) {
        User user = userMapper.selectByUsername(dto.getUsername());
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        if (!StringUtils.hasText(dto.getPassword()) || !dto.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("密码错误");
        }

        return user;
    }

    @Override
    public User getUserInfo(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        return user;
    }

    @Override
    public void updateProfile(UserUpdateDTO dto) {
        User user = userMapper.selectById(dto.getId());
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        user.setNickname(dto.getNickname());
        user.setBio(dto.getBio());
        userMapper.updateProfile(user);
    }
}