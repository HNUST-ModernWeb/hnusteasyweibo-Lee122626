package com.example.weibo.controller;

import com.example.weibo.common.Result;
import com.example.weibo.dto.LoginDTO;
import com.example.weibo.dto.RegisterDTO;
import com.example.weibo.dto.UserUpdateDTO;
import com.example.weibo.entity.User;
import com.example.weibo.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public Result<Void> register(@RequestBody @Valid RegisterDTO dto) {
        authService.register(dto);
        return Result.success();
    }

    @PostMapping("/login")
    public Result<User> login(@RequestBody @Valid LoginDTO dto) {
        return Result.success(authService.login(dto));
    }

    @GetMapping("/user/{id}")
    public Result<User> getUserInfo(@PathVariable Long id) {
        return Result.success(authService.getUserInfo(id));
    }

    @PutMapping("/user")
    public Result<Void> updateProfile(@RequestBody @Valid UserUpdateDTO dto) {
        authService.updateProfile(dto);
        return Result.success();
    }
}