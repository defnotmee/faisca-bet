package com.fazol;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Popup;

public class signInController {
    @FXML
    void TryNewUser(ActionEvent event) {

    }

    @FXML
    void aboutMenu(ActionEvent event) {
        Popup popup = new Popup();
        popup.getContent().add(new Label("About"));
        popup.show(popup);
    }

    @FXML
    void goBack(ActionEvent event) throws IOException{
        App.setRoot("login");
    }

}
