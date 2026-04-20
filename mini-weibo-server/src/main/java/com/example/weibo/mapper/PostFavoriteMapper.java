package com.example.weibo.mapper;

import com.example.weibo.entity.PostFavorite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostFavoriteMapper {
    PostFavorite selectByPostIdAndUserId(@Param("postId") Long postId, @Param("userId") Long userId);

    int insert(PostFavorite postFavorite);

    int deleteByPostIdAndUserId(@Param("postId") Long postId, @Param("userId") Long userId);
}