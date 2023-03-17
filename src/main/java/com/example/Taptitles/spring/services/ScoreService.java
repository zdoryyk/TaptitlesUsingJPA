package com.example.Taptitles.spring.services;

import com.example.Taptitles.spring.models.Score;
import com.example.Taptitles.spring.repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;

    @Autowired
    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public List<Score> showAll(){
        return scoreRepository.findAll();
    }

    public List<Score> findByName(String playerName){
        return scoreRepository.findByPlayerName(playerName);
    }

    public void addScore(Score score){
        score.setPlayedOn(LocalDateTime.now());
        score.setPlayerName(score.getPlayer().getName());
        score.setGame("TapTitles");
        scoreRepository.save(score);
    }

}
