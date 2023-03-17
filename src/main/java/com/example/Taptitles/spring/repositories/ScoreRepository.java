package com.example.Taptitles.spring.repositories;

import com.example.Taptitles.spring.models.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score,Integer> {

    List<Score> findByPlayerName(String player);
}
