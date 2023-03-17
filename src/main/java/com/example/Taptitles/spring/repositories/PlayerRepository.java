package com.example.Taptitles.spring.repositories;

import com.example.Taptitles.spring.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {

    Player findByName(String name);
}
