package com.fazol;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private Button saqueButton;

    @FXML
    private Button depositButton;

    @FXML
    private Button roletaButton;

    @FXML
    private Text username;

    @FXML
    void trySaque(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERRO");
        alert.setHeaderText("Problema ao conectar com o servidor");
        alert.setContentText("Por favor, tente novamente mais tarde");
        alert.showAndWait();
    }

    @FXML
    void tryDeposito(ActionEvent event) throws IOException {
        
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Deposito");
            alert.setContentText("Deposito ainda não disponivel");
            alert.showAndWait();
            return;
        
        /*
        App.scene = new Scene(loadFXML("deposit"), 700,441);
        App.stages.get(0).close();
        App.stages.remove(0);
        Stage stage = new Stage();

        stage.setScene(App.scene);
        App.stages.add(stage);
        stage.show();
*/
    }

    @FXML
    void logOut(ActionEvent event) throws Exception{
        App.scene = new Scene(loadFXML("login"), 700,441);
        App.stages.get(0).close();
        App.stages.remove(0);
        Stage stage = new Stage();

        stage.setScene(App.scene);
        App.stages.add(stage);
        stage.show();
    }

    @FXML
    void openConfig(ActionEvent event) throws IOException {
        if (App.stages.size() > 2){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Não se pode abrir mais de 2 janelas ao mesmo tempo");
            alert.setContentText("Por favor, feche uma das janelas abertas para poder abrir uma nova!");
            alert.showAndWait();
            return;
        }

        App.scene = new Scene(loadFXML("config"), 700,441);
        App.stages.get(0).close();
        App.stages.remove(0);
        Stage stage = new Stage();

        stage.setScene(App.scene);
        App.stages.add(stage);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @FXML
    void startBomba(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERRO");
        alert.setHeaderText("Jogo ainda não disponivel");
        alert.setContentText("Por favor, tente novamente daqui uns anos");
        alert.showAndWait();
    }

    @FXML
    void startRoleta(ActionEvent event) throws IOException {
        if (App.stages.size() == 2){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Não se pode abrir mais de 2 janelas ao mesmo tempo");
            alert.setContentText("Por favor, feche uma das janelas abertas para poder abrir uma nova!");
            alert.showAndWait();
            return;
        }

        App.scene = new Scene(loadFXML("roletinha"), 569,400);
        Stage stage = new Stage();
        stage.setScene(App.scene);
        App.stages.add(stage);
        stage.show();

    }

    @FXML
    public void initialize(){
        username.setText(App.conta.getNome());
        BigDecimal bd = App.conta.getBalance().setScale(2, BigDecimal.ROUND_DOWN);

        DecimalFormat df = new DecimalFormat();
                    
        df.setMaximumFractionDigits(2);
                    
        df.setMinimumFractionDigits(2);
                    
        df.setGroupingUsed(false);

        String result = df.format(bd);
        Saldo.setText("Saldo: R$" + result);
    }
}
