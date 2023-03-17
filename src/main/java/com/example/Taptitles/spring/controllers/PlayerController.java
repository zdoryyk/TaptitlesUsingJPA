package com.example.Taptitles.spring.controllers;

import com.example.Taptitles.spring.models.Player;
import com.example.Taptitles.spring.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

@Controller
public class PlayerController {


    private final PlayerService playerService;


    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    public List<Player> getPlayers(){
        return playerService.getAll();
    }

    public Player getPlayerByName(String name)
    {
        return playerService.getPlayerByName(name);
    }

    public void addPlayer(Player player){
        playerService.addUser(player);
    }

    public void updatePassword(Player player) {

        System.out.println("Enter your old password");
        String password = new Scanner(System.in).next();
        if(!player.getPassword().equals(password)){
            System.out.println("Incorrect password");
            updatePassword(player);
        }
        System.out.println("Enter your new password");
        player.setPassword(new Scanner(System.in).next());
        playerService.changePassword(player);
    }
    
}
