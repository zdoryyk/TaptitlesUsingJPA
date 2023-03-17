package com.example.Taptitles.spring.controllers;

import com.example.Taptitles.spring.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.Taptitles.spring.services.CommentService;

import java.util.List;

@Controller
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    public List<Comment> commentList(){
        return commentService.getAll();
    }

    public Comment findUserByName(String name)
    {
        return commentService.getCommentByPlayerName(name);
    }

    public void addComment(Comment comment){
        commentService.addComment(comment);
    }


}
