package com.example.Taptitles.spring.repositories;

import com.example.Taptitles.spring.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> getAllByPlayerName(String name);

}
