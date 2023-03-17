package com.example.Taptitles.spring.services;

import com.example.Taptitles.spring.models.Rating;
import com.example.Taptitles.spring.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingService {

    private RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> findAll(){
        return ratingRepository.findAll();
    }

    public Rating findByName(String name){
        return ratingRepository.findByPlayerName(name);
    }
    public void addRating(Rating rating){
        rating.setPlayerName(rating.getPlayer().getName());
        rating.setRatedOn(LocalDateTime.now());
        rating.setGame("TapTitles");
        ratingRepository.save(rating);
    }

    public void updateRating(Rating rating){
        ratingRepository.save(rating);
    }



}
