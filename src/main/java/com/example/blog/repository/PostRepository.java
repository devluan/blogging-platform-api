package com.example.blog.repository;

import com.example.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    @Query("""
        SELECT p FROM Post p
        WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :term, '%'))
           OR LOWER(p.content) LIKE LOWER(CONCAT('%', :term, '%'))
           OR LOWER(p.category) LIKE LOWER(CONCAT('%', :term, '%'))
    """)
    public List<Post> search(String term);
}