package br.com.Faisca.Jogo;

import java.math.BigDecimal;

import br.com.Faisca.Contas.Conta;

public interface Jogo {
	public String play(BigDecimal bet, int option, Conta jogador);
}
