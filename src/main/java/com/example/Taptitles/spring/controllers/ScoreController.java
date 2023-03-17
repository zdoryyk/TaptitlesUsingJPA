package com.example.Taptitles.spring.controllers;

import com.example.Taptitles.spring.models.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.Taptitles.spring.services.ScoreService;

import java.util.List;

@Controller
public class ScoreController {

    private final ScoreService scoreService;


    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    public List<Score> findAll(){
        return scoreService.showAll();
    }

    public List<Score> findUserByName(String name){
        return scoreService.findByName(name);
    }

    public void addScore(Score score){
        scoreService.addScore(score);
    }

}
