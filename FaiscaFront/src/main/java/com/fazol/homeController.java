package com.fazol;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class homeController {
     @FXML
    private Text Saldo;

    @FXML
    private Button bombaButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Button openConfigsButton;

    @FXML
    private Button roletaButton;

    @FXML
    private Text username;

    @FXML
    void logOut(ActionEvent event) throws Exception{
        App.setRoot("login");
    }

    @FXML
    void openConfig(ActionEvent event) {
        if (App.stages.size() > 2){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Não se pode abrir mais de 2 janelas ao mesmo tempo");
            alert.setContentText("Por favor, feche uma das janelas abertas para poder abrir uma nova!");
            alert.showAndWait();
            return;
        }
        




    }

    @FXML
    void startBomba(ActionEvent event) {

    }

    @FXML
    void startRoleta(ActionEvent event) {

    }
}
