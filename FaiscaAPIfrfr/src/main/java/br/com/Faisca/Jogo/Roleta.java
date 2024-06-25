package br.com.Faisca.Jogo;

import java.math.BigDecimal;
import java.text.NumberFormat;

import br.com.Faisca.Contas.Conta;

public class Roleta implements Jogo {	
	
	public enum RoletaRes {
		PRETO,
		VERMELHO,
		BRANCO
	}
	
	public Roleta (){
		
	}

	private static RoletaRes intToRes(int id) {
		if(id == 0)
			return RoletaRes.PRETO;
		if(id == 1)
			return RoletaRes.VERMELHO;
		return RoletaRes.BRANCO;		
	}
	
	public static int resToInt(RoletaRes res) {
		switch(res) {
		case PRETO:
			return 0;
		case VERMELHO:
			return 1;
		case BRANCO:
			return 2;
		}
		return -1;
	}
	/*
	 * bet = quantidade apostada
	 * option = {0: preto [0,6], 1: vermelho [7,13], 2: branco[14]}
	 */
	@Override
	public String play(BigDecimal bet, int option, Conta jogador) {
		BigDecimal ini = bet;
		RoletaRes choice = intToRes(option);
		
		RoletaRes res = Gerador.getInstance().genRoleta();
		
		if(res == choice) {
			bet = bet.multiply(new BigDecimal(2));
			if(res == RoletaRes.BRANCO)
				bet = bet.multiply(new BigDecimal(7));
		} else bet = new BigDecimal(0);
		
		
		
		System.out.printf("Usuário apostou %s e ficou com %s\n", NumberFormat.getCurrencyInstance().format(ini), NumberFormat.getCurrencyInstance().format(bet));
		
		jogador.applyDelta(bet.subtract(ini));
		
		return String.format(choice.name());
		
	}

}
