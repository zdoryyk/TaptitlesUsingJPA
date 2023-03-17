package com.example.Taptitles.spring.services;

import com.example.Taptitles.spring.models.Player;
import com.example.Taptitles.spring.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    //GET
    public List<Player> getAll(){
        return playerRepository.findAll();
    }
    //GET
    public Player getPlayerByName(String name){
        return playerRepository.findByName(name);
    }

    //POST
    public void addUser(Player player){
        player.setCreatedOn(LocalDateTime.now());
        playerRepository.save(player);
    }

    public void changePassword(Player player){
        player.setUpdatedOn(LocalDateTime.now());
        playerRepository.save(player);
    }
}
