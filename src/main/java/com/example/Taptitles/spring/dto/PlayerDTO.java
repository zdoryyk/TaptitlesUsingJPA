package com.example.Taptitles.spring.dto;

import com.example.Taptitles.spring.models.Comment;
import com.example.Taptitles.spring.models.Player;
import com.example.Taptitles.spring.models.Rating;
import com.example.Taptitles.spring.models.Score;

import java.util.List;

public class PlayerDTO {

    private int id;

    private String name;

    private List<Score> scoreList;

    private List<Comment> commentList;

    private Rating rating;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Score> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Score> scoreList) {
        this.scoreList = scoreList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }



}
