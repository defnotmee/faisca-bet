package com.fazol;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class loginController {

    @FXML
    private ImageView LOGO;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane loginPane;

    @FXML
    private Button signInButton;

    @FXML
    private void signIn(ActionEvent event) throws IOException{
        App.setRoot("signIn");
    }

    @FXML
    void tentarLogin(ActionEvent event) {

    }

}
