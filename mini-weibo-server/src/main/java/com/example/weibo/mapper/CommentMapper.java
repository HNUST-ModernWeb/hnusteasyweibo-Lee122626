package com.example.weibo.mapper;

import com.example.weibo.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<Comment> selectByPostId(@Param("postId") Long postId);

    int insert(Comment comment);
}