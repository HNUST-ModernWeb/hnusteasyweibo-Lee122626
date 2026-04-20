package com.example.weibo.mapper;

import com.example.weibo.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    List<Post> selectList(@Param("userId") Long userId);

    int insert(Post post);

    int deleteById(@Param("id") Long id);

    int increaseLikeCount(@Param("id") Long id);

    int decreaseLikeCount(@Param("id") Long id);
}