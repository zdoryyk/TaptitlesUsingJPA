package com.example.Taptitles.spring.services;


import com.example.Taptitles.spring.models.Comment;
import com.example.Taptitles.spring.repositories.CommentRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAll(){
        return commentRepository.findAll();
    }

    public Comment getCommentByPlayerName(String name)
    {
        return commentRepository.getAllByPlayerName(name).stream().findAny().orElse(null);
    }

    public void addComment(Comment comment){
        comment.setGame("TapTitles");
        comment.setCommentedOn(LocalDateTime.now());
        comment.setPlayerName(comment.getPlayer().getName());
        commentRepository.save(comment);
    }

}
