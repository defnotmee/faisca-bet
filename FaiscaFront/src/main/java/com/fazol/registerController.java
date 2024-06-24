package com.fazol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fazol.Requester.RException.InvalidCpfException;
import com.fazol.Requester.RException.InvalidEmailException;
import com.fazol.Requester.RException.PermissionException;
import com.fazol.Requester.RException.UserNotFoundException;
import com.fazol.Requester.RequesterRegister;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class registerController {
    
    @FXML
    private TextField CPF;

    @FXML
    private TextField Username;

    @FXML
    private TextField conf_email;

    @FXML
    private PasswordField conf_password;

    @FXML
    private TextField email;

    @FXML
    private Button newUserButton;

    @FXML
    private PasswordField password;
    

    @FXML
    void TryNewUser(ActionEvent event) {
        String cpf = CPF.getText();
        String username = Username.getText();
        String email = this.email.getText();
        String password = this.password.getText();
        String conf_email = this.conf_email.getText();
        String conf_password = this.conf_password.getText();
        
        if (cpf.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || conf_email.isEmpty() || conf_password.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Espaços vazios");
            alert.setContentText("Por favor preencha todas as informações");
            alert.showAndWait();
        } else if (!email.equals(conf_email)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Emails não correspondem");
            alert.setContentText("Por favor, verifique se os emails correspondem");
            alert.showAndWait();
        } else if (!password.equals(conf_password)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Senhas não correspondem");
            alert.setContentText("Por favor, verifique se as senhas correspondem");
            alert.showAndWait();
        } else {
            List<String> arguments = new ArrayList<>();
            arguments.add(username);
            arguments.add(email);
            arguments.add(password);
            arguments.add(cpf);
    
            RequesterRegister requesterRegister = new RequesterRegister();

            Long response = null;

            try{
                response = requesterRegister.makeRequest(arguments);
            } catch (UserNotFoundException e){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Usuário não encontrado");
                alert.setContentText("Por favor, verifique se o email está correto");
                alert.showAndWait();
            } catch (InvalidEmailException e){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Email inválido");
                alert.setContentText("Por favor, verifique se o email está correto");
                alert.showAndWait();
            } catch (InvalidCpfException e){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("CPF inválido");
                alert.setContentText("Por favor, verifique se o CPF está correto");
                alert.showAndWait();
            } catch (PermissionException e){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Senha incorreta");
                alert.setContentText("Por favor, verifique se a senha está correta");
                alert.showAndWait();
            }

            App.userId = response;
        }
    }

    @FXML
    void aboutMenu(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Faisca");
        alert.setContentText("Faisca is a betting platform designed and created by a group of students from the University of Campinas. The main goal of the platform is to provide a fun and engaging experience while also giving us a good grade! The platform is still in development, so we appreciate your feedback and suggestions. Thank you for using Faisca!");
        alert.showAndWait();
    }

    @FXML
    void goBack(ActionEvent event) throws IOException{
        App.setRoot("login");
    }

}
