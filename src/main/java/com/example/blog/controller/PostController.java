package com.example.blog.controller;

import com.example.blog.dto.PostDTO;
import com.example.blog.model.Post;
import com.example.blog.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public String notFound(EntityNotFoundException e) {
        return e.getMessage();
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody @Valid PostDTO postDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postService.createPost(postDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") UUID id, @RequestBody @Valid PostDTO postDTO) {
        return ResponseEntity.ok(postService.updatePost(id, postDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable("id") UUID id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(@RequestParam(value = "term", required = false) String term) {
        return ResponseEntity.ok(postService.getAllPosts(term));
    }
}