package com.example.weibo.service.impl;

import com.example.weibo.dto.CommentCreateDTO;
import com.example.weibo.dto.PostCreateDTO;
import com.example.weibo.entity.Comment;
import com.example.weibo.entity.Post;
import com.example.weibo.entity.PostFavorite;
import com.example.weibo.entity.PostLike;
import com.example.weibo.mapper.CommentMapper;
import com.example.weibo.mapper.PostFavoriteMapper;
import com.example.weibo.mapper.PostLikeMapper;
import com.example.weibo.mapper.PostMapper;
import com.example.weibo.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final CommentMapper commentMapper;
    private final PostFavoriteMapper postFavoriteMapper;
    private final PostLikeMapper postLikeMapper;

    public PostServiceImpl(PostMapper postMapper,
                           CommentMapper commentMapper,
                           PostFavoriteMapper postFavoriteMapper,
                           PostLikeMapper postLikeMapper) {
        this.postMapper = postMapper;
        this.commentMapper = commentMapper;
        this.postFavoriteMapper = postFavoriteMapper;
        this.postLikeMapper = postLikeMapper;
    }

    @Override
    public List<Post> list(Long userId) {
        return postMapper.selectList(userId);
    }

    @Override
    public void create(PostCreateDTO dto) {
        Post post = new Post();
        post.setUserId(dto.getUserId());
        post.setContent(dto.getContent());
        post.setImage(dto.getImage());
        post.setLikeCount(0);
        postMapper.insert(post);
    }

    @Override
    public void delete(Long id) {
        postMapper.deleteById(id);
    }

    @Override
    public List<Comment> getComments(Long postId) {
        return commentMapper.selectByPostId(postId);
    }

    @Override
    public void addComment(CommentCreateDTO dto) {
        Comment comment = new Comment();
        comment.setPostId(dto.getPostId());
        comment.setUserId(dto.getUserId());
        comment.setContent(dto.getContent());
        commentMapper.insert(comment);
    }

    @Override
    public void favorite(Long postId, Long userId) {
        PostFavorite record = postFavoriteMapper.selectByPostIdAndUserId(postId, userId);
        if (record != null) {
            throw new RuntimeException("你已经收藏过了");
        }

        PostFavorite favorite = new PostFavorite();
        favorite.setPostId(postId);
        favorite.setUserId(userId);
        postFavoriteMapper.insert(favorite);
    }

    @Override
    public void unfavorite(Long postId, Long userId) {
        PostFavorite record = postFavoriteMapper.selectByPostIdAndUserId(postId, userId);
        if (record == null) {
            throw new RuntimeException("你还没有收藏");
        }

        postFavoriteMapper.deleteByPostIdAndUserId(postId, userId);
    }

    @Override
    public void like(Long postId, Long userId) {
        PostLike record = postLikeMapper.selectByPostIdAndUserId(postId, userId);
        if (record != null) {
            throw new RuntimeException("你已经点过赞了");
        }

        PostLike like = new PostLike();
        like.setPostId(postId);
        like.setUserId(userId);

        postLikeMapper.insert(like);
        postMapper.increaseLikeCount(postId);
    }

    @Override
    public void unlike(Long postId, Long userId) {
        PostLike record = postLikeMapper.selectByPostIdAndUserId(postId, userId);
        if (record == null) {
            throw new RuntimeException("你还没有点赞");
        }

        postLikeMapper.deleteByPostIdAndUserId(postId, userId);
        postMapper.decreaseLikeCount(postId);
    }
}