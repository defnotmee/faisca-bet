package br.com.Faisca.Contas;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.Faisca.Jogo.Gerador;

public abstract class Conta {
	
	final private String nome;
	private byte[] hashSenha;
	final private Long id;
	
	protected Conta(String nome, String senha) {
		this.nome = nome;
		// Vamos rezar para que não haja colisão. O valor esperado é que precise de 10⁹ contas pra haver colisão
		// pelo paradoxo do aniversário. Acho que ta suave.
		this.id = Gerador.getInstance().genId(); 
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			hashSenha = digest.digest(senha.getBytes(StandardCharsets.UTF_8));
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Não achou SHA-256");
			e.printStackTrace();
		}
	}
	
	public abstract BigDecimal getBalance();
	
	public boolean canBet(BigDecimal ammount) {
		return ammount.compareTo(this.getBalance()) <= 0;
	}
	
	public boolean verify(String senha) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhash = digest.digest(
		  senha.getBytes(StandardCharsets.UTF_8));
		
		return encodedhash.equals(hashSenha);
	}
	
	public abstract void applyDelta(BigDecimal delta);
	
	public abstract void deposit(BigDecimal ammount);
	
	public Long getId() {
		return this.id;
	}
	
}
