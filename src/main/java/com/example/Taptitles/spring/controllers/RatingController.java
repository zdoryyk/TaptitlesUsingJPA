package com.example.Taptitles.spring.controllers;


import com.example.Taptitles.spring.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.Taptitles.spring.services.RatingService;

import java.util.List;

@Controller
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }


    //GET
    public List<Rating> findAll(){
        return ratingService.findAll();
    }
    //GET
    public Rating findUserByName(String name){
        return ratingService.findByName(name);
    }

    //POST
    public void addRating(Rating rating){
        ratingService.addRating(rating);
    }

}
