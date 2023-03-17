package com.example.Taptitles.spring.repositories;

import com.example.Taptitles.spring.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {

    Rating findByPlayerName(String name);
}
