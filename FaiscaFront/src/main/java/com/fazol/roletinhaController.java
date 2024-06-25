package com.fazol;

import java.net.URL;
import javafx.event.ActionEvent;
import java.time.Duration;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

public class roletinhaController {
	@FXML
	private ImageView myImage;
	
	@FXML
	void roda(ActionEvent event){
		RotateTransition rotate = new RotateTransition();
		rotate.setNode(myImage);
		rotate.setByAngle(360);
		rotate.setInterpolator(Interpolator.LINEAR);
		rotate.setCycleCount(10);
		//rotate.setDuration(Duration.millis(1000));
		rotate.play();
	}
}
