package com.example.weibo.service;

import com.example.weibo.dto.CommentCreateDTO;
import com.example.weibo.dto.PostCreateDTO;
import com.example.weibo.entity.Comment;
import com.example.weibo.entity.Post;

import java.util.List;

public interface PostService {
    List<Post> list(Long userId);

    void create(PostCreateDTO dto);

    void delete(Long id);

    List<Comment> getComments(Long postId);

    void addComment(CommentCreateDTO dto);

    void favorite(Long postId, Long userId);

    void unfavorite(Long postId, Long userId);

    void like(Long postId, Long userId);

    void unlike(Long postId, Long userId);
}