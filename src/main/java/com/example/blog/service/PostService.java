package com.example.blog.service;

import com.example.blog.dto.PostDTO;
import com.example.blog.model.Post;
import com.example.blog.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post createPost(PostDTO postDTO) {
        var post = new Post();
        BeanUtils.copyProperties(postDTO, post);
        post.setCreatedAt(Instant.now());
        post.setUpdatedAt(Instant.now());
        return postRepository.save(post);
    }

    public Post updatePost(UUID postId, PostDTO postDTO) {
        var post = getPostById(postId);
        BeanUtils.copyProperties(postDTO, post);
        post.setUpdatedAt(Instant.now());
        return postRepository.save(post);
    }

    public void deletePost(UUID postId) {
        getPostById(postId);
        postRepository.deleteById(postId);
    }

    public Post getPostById(UUID postId) {
        return postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post not found"));
    }

    public List<Post> getAllPosts(String term) {
        return term == null ? postRepository.findAll() : postRepository.search(term);
    }
}