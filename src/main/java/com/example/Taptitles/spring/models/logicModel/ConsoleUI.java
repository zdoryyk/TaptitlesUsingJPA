package com.example.Taptitles.spring.models.logicModel;

import com.example.Taptitles.spring.controllers.CommentController;
import com.example.Taptitles.spring.controllers.PlayerController;
import com.example.Taptitles.spring.controllers.RatingController;
import com.example.Taptitles.spring.controllers.ScoreController;
import com.example.Taptitles.spring.models.Comment;
import com.example.Taptitles.spring.models.Player;
import com.example.Taptitles.spring.models.Rating;
import com.example.Taptitles.spring.models.Score;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;

import java.util.*;

public class ConsoleUI {

    private Field field;
    private int rows;
    private int cols;

    private Player player;

    private CommentController commentController;

    private PlayerController playerController;

    private RatingController ratingController;

    private ScoreController scoreController;

    private ModelMapper modelMapper = new ModelMapper();


    public ConsoleUI(int rows, int cols, ApplicationContext applicationContext) {
        field = new Field(rows,cols,applicationContext);
        this.cols = cols;
        this.rows = rows;
        ratingController = applicationContext.getBean(RatingController.class);
        playerController = applicationContext.getBean(PlayerController.class);
        scoreController = applicationContext.getBean(ScoreController.class);
        commentController = applicationContext.getBean(CommentController.class);

    }

    public void play(){

        int tempScore = 0;
        int tempRating = 0;
        player = menu(player);
        do{
            if(field.handleInput()){
                tempRating += 1;
                tempScore += 1;
            }else{
                tempRating -= 1;
                tempScore -=1 ;
            }
        }while (field.getState() == State.PLAYING);

        Rating rating = player.getRating();
        rating.setRating(tempRating);
        ratingController.addRating(rating);
        player.setRating(rating);

        Score score = new Score();
        score.setPlayer(player);
        score.setPoints(tempScore);
        scoreController.addScore(score);
        player.setScoreList(scoreController.findUserByName(player.getName()));


        System.out.println("Would you like to comment your game[y/n]");
        if ("y".equals(new Scanner(System.in).next())) {
            commentByUser(player);
        }
        play();
    }



    private Player menu(Player player){
        if(player == null)System.out.println("WELCOME TO THE TAP TITLES");
        System.out.println("-----MENU-----");
        if(player == null) {
            System.out.println("1: login");
            System.out.println("2: signup");

            switch (new Scanner(System.in).next()){
                case "2" -> {
                    player = registerNewUser();
                    menu(player);
                }
                case "1" -> {
                    player = login();
                    menu(player);
                }
                default -> {player = menu(null);}
            }
        }else{
            System.out.println("1: game");
            System.out.println("2: all my scores");
            System.out.println("3: my rating");
            System.out.println("4: my comments");
            System.out.println("5: change password");
            System.out.println("6: logout");

            switch (new Scanner(System.in).next()){
                case "1" ->{
                    return player;
                }
                case "2" -> {
                    if(player.getScoreList().isEmpty()) System.out.println("You didnt play any game");
                    else player.getScoreList().forEach(x -> System.out.println(x + "\n"));
                    menu(player);
                }
                case "3" ->{
                    System.out.println(player.getRating());
                    menu(player);
                }
                case "4" ->{
                    if(player.getCommentList() == null || player.getCommentList().isEmpty()) System.out.println("you didnt comment yet");
                    else player.getCommentList().forEach(x -> System.out.println(x + "\n"));
                    menu(player);
                }
                case "5" -> {
                    playerController.updatePassword(player);
                    menu(player);
                }
                case "6" -> {
                    player = null;
                    menu(null);
                }
            }
        }
        return player;
    }
    private Player registerNewUser(){

        Player playerToRegister = new Player();
        System.out.println("Enter your name");
        String name = new Scanner(System.in).next();
        if(playerController.getPlayers().stream().anyMatch(x -> x.getName().equals(name))){
            System.out.println("This name is taken");
            registerNewUser();
        }
        playerToRegister.setName(name.toLowerCase());
        System.out.println("Enter your password");
        playerToRegister.setPassword(new Scanner(System.in).next());
        playerController.addPlayer(playerToRegister);

        Rating rating = new Rating();
        rating.setPlayer(playerToRegister);
        rating.setRating(0);
        ratingController.addRating(rating);
        playerToRegister.setRating(rating);


        return playerToRegister;
    }

    private Player login(){
        System.out.println("Enter your name");
        String name = new Scanner(System.in).next();

         if(playerController.getPlayerByName(name.toLowerCase()) != null){
             player = playerController.getPlayerByName(name.toLowerCase());
         };
        if(player == null) {
            System.out.println("Player doesnt exist\n");
            login();
        }
        System.out.println("Enter your password");
        while (!player.getPassword().equals(new Scanner(System.in).next())) {
            System.out.println("Wrong password");
        }
        return player;
    }

    private void commentByUser(Player player){
        Comment comment = new Comment();
        comment.setPlayer(player);
        System.out.println("Your opinion");
        comment.setComment(new Scanner(System.in).nextLine());
        commentController.addComment(comment);
        if(player.getCommentList() != null){
            List<Comment> commentList = new ArrayList<>(player.getCommentList());
            commentList.add(comment);
            player.setCommentList(commentList);
        }
        else player.setCommentList(Collections.singletonList(comment));
    }
}
