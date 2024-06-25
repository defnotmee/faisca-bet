package com.fazol;

import java.util.ArrayList;
import java.util.List;

import com.fazol.Requester.RequesterDeposit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class depositController {
    
    @FXML
    private TextField Ammount;

    @FXML
    private Button depositButton;

    @FXML
    private Button quitButton;

    @FXML
    void quit(ActionEvent event) {
        App.stages.get(App.stages.size()-1).close();
		App.stages.remove(App.stages.size()-1);
    }

    @FXML
    void tryDeposito(ActionEvent event) {
        try {
            if (Ammount.getText().isEmpty() || Double.parseDouble(Ammount.getText()) <= 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setHeaderText("Valor inválido");
                alert.setContentText("Por favor insira um valor valido para o depósito!");
                alert.showAndWait();
                return;
            }
            
            RequesterDeposit requesterDeposit = new RequesterDeposit();
            List <String> args = new ArrayList<String>();
            args.add(String.valueOf(App.conta.getId()));
            args.add(Ammount.getText());


            boolean bonus = requesterDeposit.makeRequest(args);

            if (bonus){
                Alert bonusAlert = new Alert(AlertType.INFORMATION);
                bonusAlert.setTitle("PARABENSS!");
                bonusAlert.setHeaderText("Você ganhou um bônus!");
                bonusAlert.setContentText("Ao depositar mais de 100 reais você recebeu um bônus único de mais 100 reais. Aproveite!");
                bonusAlert.showAndWait();
            }

        } catch (Exception e) {
            System.out.println("Dados mal formatados");
            return;
        }
    }

}