package com.example.Taptitles.spring.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int id;

    @Column(name = "player_name")
    @NotNull
    private String playerName;

    @Column(name = "game")
    @NotNull
    private String game;

    @Column(name = "comment")
    @NotNull
    private String comment;

    @Column(name = "commented_on")
    @NotNull
    private LocalDateTime commentedOn;

    @ManyToOne()
    @JoinColumn(name = "player_id")
    private Player player;

    public Comment(String playerName, String game, String comment, LocalDateTime commentedOn) {
        this.playerName = playerName;
        this.game = game;
        this.comment = comment;
        this.commentedOn = commentedOn;
    }

    public Comment() {
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCommentedOn() {
        return commentedOn;
    }

    public void setCommentedOn(LocalDateTime commentedon) {
        this.commentedOn = commentedon;
    }

    @Override
    public String toString() {
        return "Comment: " + comment + "  \n" + "commented: " + commentedOn.getDayOfMonth() + " " + commentedOn.getMonth();
    }
}
