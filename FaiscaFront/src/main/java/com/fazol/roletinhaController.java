package com.fazol;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;

import com.fazol.Requester.RException.InvalidDataException;
import com.fazol.Requester.RException.TooPoorException;
import com.fazol.Requester.RequesterRoleta;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class roletinhaController {
	
	@FXML
	private Text saldo;

	@FXML
	private TextField valorBet;

	@FXML
    private Button blackButton;

    @FXML
    private ImageView myImage;

    @FXML
    private Button quitButton;

    @FXML
    private Button redButton;

    @FXML
    private Button whiteButton;

	@FXML
	private ImageView spinImage;

    @FXML
    void quit(ActionEvent event) {
		App.stages.get(App.stages.size()-1).close();
		App.stages.remove(App.stages.size()-1);
	}

	void generalSpin(String cor){
		try {
			if (valorBet.getText().isEmpty() || Double.parseDouble(valorBet.getText()) <= 0) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("ERRO");
				alert.setHeaderText("Valor inválido");
				alert.setContentText("Por favor insira um valor valido para a aposta!");
				alert.showAndWait();
				
				return;
			}
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERRO");
			alert.setHeaderText("Valor inválido");
			alert.setContentText("Por favor insira um valor valido para a aposta!");
			return;
		}
		
		RequesterRoleta req = new RequesterRoleta();
		String res;
		try {
			res = req.makeRequest(Arrays.asList(App.conta.getId().toString(), valorBet.getText(), cor));
			
		} catch (InvalidDataException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERRO");
			alert.setHeaderText("Dados inválidos");
			alert.setContentText("Por favor insira um valor valido para a aposta!");
			alert.showAndWait();
			
			return;
		} catch (TooPoorException e){
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERRO");
			alert.setHeaderText("Saldo insuficiente");
			alert.setContentText("Você não tem saldo suficiente para essa aposta!");
			alert.showAndWait();

			return;
		} catch(Exception e){
			System.out.println("Não é para chegar aqui");
			return;
		}

		roda(Cor.valueOf(res));
		initialize();
	}

	@FXML
	void red(ActionEvent event) {
		generalSpin("VERMELHO");
	}

	@FXML
	void white(ActionEvent event) {
		generalSpin("BRANCO");
	}

	@FXML
	void black(ActionEvent event) {
		generalSpin("PRETO");
	}


	
	@FXML
	void roda(Cor cor){
		myImage.setRotate(0);
		switch (cor) {
			case BRANCO:
				RotateTransition rotateBranco = new RotateTransition();
				rotateBranco.setNode(myImage);
				rotateBranco.setDuration(Duration.millis(1000));
				rotateBranco.setInterpolator(Interpolator.LINEAR);
				rotateBranco.setCycleCount(1);
				rotateBranco.setByAngle(1800);
				rotateBranco.play();	
				break;
			
			case PRETO:
				RotateTransition rotateP = new RotateTransition();
				rotateP.setNode(myImage);
				rotateP.setDuration(Duration.millis(1000));
				rotateP.setInterpolator(Interpolator.LINEAR);
				rotateP.setCycleCount(1);
				rotateP.setByAngle(1800-98);
				rotateP.play();	
				break;

			case VERMELHO:
				RotateTransition rotate = new RotateTransition();
				rotate.setNode(myImage);
				rotate.setDuration(Duration.millis(1000));
				rotate.setInterpolator(Interpolator.LINEAR);
				rotate.setCycleCount(1);
				rotate.setByAngle(1800 + 98);
				rotate.play();	
				break;
				
			default:
				break;
		}
		
	}

	@FXML
    public void initialize(){
        App.conta.refresh();
        BigDecimal bd = App.conta.getBalance().setScale(2, BigDecimal.ROUND_DOWN);

        DecimalFormat df = new DecimalFormat();
                    
        df.setMaximumFractionDigits(2);
                    
        df.setMinimumFractionDigits(2);
                    
        df.setGroupingUsed(false);

        String result = df.format(bd);
        saldo.setText("Saldo: R$" + result);
    }
}
