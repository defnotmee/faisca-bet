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
		
	}

	@FXML
	void white(ActionEvent event) {
		
	}

	@FXML
	void black(ActionEvent event) {
		
	}


	
	@FXML
	void roda(Cor cor){
		switch (cor) {
			case BRANCO:
				RotateTransition rotateBranco = new RotateTransition();
				rotateBranco.setNode(myImage);
				rotateBranco.setDuration(Duration.millis(5000));
				rotateBranco.setInterpolator(Interpolator.LINEAR);
				rotateBranco.setCycleCount(1);
				rotateBranco.setByAngle(1800);
				rotateBranco.play();	
				break;
			
			case PRETO:
				RotateTransition rotateP = new RotateTransition();
				rotateP.setNode(myImage);
				rotateP.setDuration(Duration.millis(5000));
				rotateP.setInterpolator(Interpolator.LINEAR);
				rotateP.setCycleCount(1);
				rotateP.setByAngle(1800-98);
				rotateP.play();	
				break;

			case VERMELHO:
				RotateTransition rotate = new RotateTransition();
				rotate.setNode(myImage);
				rotate.setDuration(Duration.millis(5000));
				rotate.setInterpolator(Interpolator.LINEAR);
				rotate.setCycleCount(1);
				rotate.setByAngle(1800 + 98);
				rotate.play();	
				break;
				
			default:
				break;
		}
	}
}
