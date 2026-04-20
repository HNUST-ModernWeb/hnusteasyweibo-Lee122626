package com.example.weibo.controller;

import com.example.weibo.common.Result;
import com.example.weibo.dto.CommentCreateDTO;
import com.example.weibo.dto.PostCreateDTO;
import com.example.weibo.entity.Comment;
import com.example.weibo.entity.Post;
import com.example.weibo.service.PostService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@CrossOrigin
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/list")
    public Result<List<Post>> list(@RequestParam(required = false) Long userId) {
        return Result.success(postService.list(userId));
    }

    @PostMapping
    public Result<Void> create(@RequestBody @Valid PostCreateDTO dto) {
        postService.create(dto);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        postService.delete(id);
        return Result.success();
    }

    @GetMapping("/{id}/comments")
    public Result<List<Comment>> getComments(@PathVariable Long id) {
        return Result.success(postService.getComments(id));
    }

    @PostMapping("/{id}/comments")
    public Result<Void> addComment(@PathVariable Long id, @RequestBody @Valid CommentCreateDTO dto) {
        dto.setPostId(id);
        postService.addComment(dto);
        return Result.success();
    }

    @PostMapping("/{id}/favorite")
    public Result<Void> favorite(@PathVariable Long id, @RequestParam Long userId) {
        postService.favorite(id, userId);
        return Result.success();
    }

    @DeleteMapping("/{id}/favorite")
    public Result<Void> unfavorite(@PathVariable Long id, @RequestParam Long userId) {
        postService.unfavorite(id, userId);
        return Result.success();
    }

    @PostMapping("/{id}/like")
    public Result<Void> like(@PathVariable Long id, @RequestParam Long userId) {
        postService.like(id, userId);
        return Result.success();
    }

    @DeleteMapping("/{id}/like")
    public Result<Void> unlike(@PathVariable Long id, @RequestParam Long userId) {
        postService.unlike(id, userId);
        return Result.success();
    }
}