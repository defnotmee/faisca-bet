package com.fazol;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Espaços vazios");
            alert.setContentText("Por favor preencha todas as informações");
            alert.showAndWait();
        } else {
            // Send Request to server
            // If server returns true
            // App.setRoot("home");

        }
    }

}
