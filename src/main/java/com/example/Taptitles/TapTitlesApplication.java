package com.example.Taptitles;

import com.example.Taptitles.spring.controllers.CommentController;
import com.example.Taptitles.spring.controllers.PlayerController;
import com.example.Taptitles.spring.controllers.RatingController;
import com.example.Taptitles.spring.controllers.ScoreController;
import com.example.Taptitles.spring.models.logicModel.ConsoleUI;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TapTitlesApplication {

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(TapTitlesApplication.class, args);
		RatingController ratingController = applicationContext.getBean(RatingController.class);
		PlayerController playerController = applicationContext.getBean(PlayerController.class);
		ScoreController scoreController = applicationContext.getBean(ScoreController.class);
		CommentController commentController = applicationContext.getBean(CommentController.class);


		ConsoleUI consoleUI = new ConsoleUI(2,2,applicationContext);
		consoleUI.play();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
