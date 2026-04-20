package com.example.weibo.mapper;

import com.example.weibo.entity.PostLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostLikeMapper {
    PostLike selectByPostIdAndUserId(@Param("postId") Long postId, @Param("userId") Long userId);

    int insert(PostLike postLike);

    int deleteByPostIdAndUserId(@Param("postId") Long postId, @Param("userId") Long userId);
}