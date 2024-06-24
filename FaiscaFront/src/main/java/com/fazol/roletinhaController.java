package com.fazol;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

public class roletinhaController implements Initializable
{
	@FXML
	private ImageView myImage;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(myImage);
		translate.setByX(250);
		translate.play();
	}
}
