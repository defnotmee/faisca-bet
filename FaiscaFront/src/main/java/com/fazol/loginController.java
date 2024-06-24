package com.fazol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fazol.Requester.RequesterLogin;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    void tentarLogin(ActionEvent event) throws IOException{
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Espaços vazios");
            alert.setContentText("Por favor preencha todas as informações");
            alert.showAndWait();
        } else {
            
            
                // Send Request to server
                List<String> arguments = new ArrayList<String>();
                List<String> response = new ArrayList<String>();

                RequesterLogin requesterLogin = new RequesterLogin();
                response = (List<String>) requesterLogin.makeRequest(arguments);

                if (response == null){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText("Erro ao conectar com o servidor");
                    alert.setContentText("Por favor tente novamente mais tarde");
                    alert.showAndWait();
                } else if (response.get(1).equals("400")){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText("Email inválido");
                    alert.setContentText("Por favor, verifique se o email está correto");
                    alert.showAndWait();
                } else if (response.get(1).equals("401")){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText("Senha incorreta");
                    alert.setContentText("Por favor, verifique se a senha está correta");
                    alert.showAndWait();
                } else if (response.get(1).equals("404")){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText("Usuário não encontrado");
                    alert.setContentText("Por favor, verifique se o email está correto");
                    alert.showAndWait();
                } else if (response.get(1).equals("200")){
                    App.id = Long.parseLong(response.get(0));
                }

                // If server returns true
                // App.setRoot("home");


                
                App.scene = new Scene(loadFXML("home"), 1960,1080);
                App.stages.get(0).close();
                Stage stage = new Stage();

                stage.setScene(App.scene);
                App.stages.add(stage);
                stage.setFullScreen(true);
                stage.show();
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

}
