package com.fazol;

import java.net.URL;
import javafx.event.ActionEvent;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.util.Duration;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class roletinhaController {
	
	@FXML
    private Button blackButton;

    @FXML
    private ImageView myImage;

    @FXML
    private Button quitButton;

    @FXML
    private Button redButton;

    @FXML
    private Button whiteButton;

	@FXML
	private ImageView spinImage;

    @FXML
    void quit(ActionEvent event) {
		App.stages.get(App.stages.size()-1).close();
		App.stages.remove(App.stages.size()-1);
	}

	@FXML
	void red(ActionEvent event) {
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(myImage);
		translate.setByX(100);
		translate.setByY(100);
		translate.setDuration(Duration.millis(1000));
		translate.setInterpolator(Interpolator.LINEAR);
		translate.setCycleCount(1);
		translate.play();
	}

	@FXML
	void white(ActionEvent event) {
		RotateTransition rotate = new RotateTransition();
		rotate.setNode(myImage);
		rotate.setDuration(Duration.millis(1000));
		rotate.setInterpolator(Interpolator.LINEAR);
		rotate.setCycleCount(1);
		rotate.play();	

	}


	
	@FXML
	void roda(Cor cor){
		switch (cor) {
			case BRANCO:
			RotateTransition rotate = new RotateTransition();
				rotate.setNode(myImage);
				rotate.setDuration(Duration.millis(1000));
				rotate.setInterpolator(Interpolator.LINEAR);
				rotate.setCycleCount(1);
				rotate.play();	
				break;
			
			case PRETO:
				
				break;

			case VERMELHO:

				break;
				
			default:
				break;
		}
		RotateTransition rotate = new RotateTransition();
		rotate.setNode(myImage);
		rotate.setByAngle(360);
		rotate.setInterpolator(Interpolator.LINEAR);
		rotate.setCycleCount(10);
		//rotate.setDuration(Duration.millis(1000));
		rotate.play();
	}
}
