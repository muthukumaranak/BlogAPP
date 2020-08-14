package com.example.blogapp.repository;

import com.example.blogapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
