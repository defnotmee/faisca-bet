package com.fazol;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
                System.out.println("Valor inválido");
                return;
            }
        } catch (Exception e) {
            System.out.println("Dados mal formatados");
            return;
        }
    }

}