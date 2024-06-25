package br.com.Faisca.Contas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.Faisca.Jogo.Gerador;

public abstract class Conta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	final private String nome;
	private byte[] hashSenha;
	final private Long id;
	
	protected Conta(String nome, String senha) {
		this.nome = nome;
		// Vamos rezar para que não haja colisão. O valor esperado é que precise de 10⁹ contas pra haver colisão
		// pelo paradoxo do aniversário. Acho que ta suave.
		this.id = Gerador.getInstance().genId(); 
		System.out.println("Senha: " + senha);
		this.hashSenha = Hasher.hash(senha);
	}
	
	public abstract BigDecimal getBalance();
	
	public boolean canBet(BigDecimal ammount) {
		return ammount.compareTo(this.getBalance()) <= 0;
	}
	
	public boolean verify(String senha) throws Exception {

		return Hasher.hash(senha).equals(hashSenha);
	}
	
	public abstract String getCpf();
	
	public abstract void applyDelta(BigDecimal delta);

	// Retorna true se você ganhou o bonus de primeiro deposito
	public abstract boolean deposit(BigDecimal ammount);
	
	public Long getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public byte[] getHashSenha() {
		return this.hashSenha;
	}

	protected abstract Object getEmail();

}
