package com.example.Taptitles.spring.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "Rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private int id;

    @Column(name = "player_name")
    @NotNull
    private String playerName;

    @Column(name = "game")
    @NotNull
    private String game;

    @Column(name = "rating")
    @NotNull
    private int rating;

    @Column(name = "rated_on")
    @NotNull
    private LocalDateTime ratedOn;

    @OneToOne()
    @JoinColumn(name = "player_id")
    private Player player;

    public Rating(String playerName, String game, int rating, LocalDateTime ratedOn) {
        this.playerName = playerName;
        this.game = game;
        this.rating = rating;
        this.ratedOn = ratedOn;
    }

    public Rating() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDateTime getRatedOn() {
        return ratedOn;
    }

    public void setRatedOn(LocalDateTime ratedOn) {
        this.ratedOn = ratedOn;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "You have " + rating + " points of rating";
    }
}
