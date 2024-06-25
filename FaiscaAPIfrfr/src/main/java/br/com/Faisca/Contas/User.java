package br.com.Faisca.Contas;

import java.io.Serializable;
import java.math.BigDecimal;

public class User extends Conta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private boolean alreadyDeposit;
	private BigDecimal qtdBonus, qtdDeposit;
	private String cpf;
	private String email;
	
	
	public User(String nome, String email, String senha, String cpf) {
		super(nome, senha);
		alreadyDeposit = false;
		this.email = email;
		qtdBonus = new BigDecimal(0);
		qtdDeposit = new BigDecimal(0);
		this.cpf = cpf;
	}

	public boolean isBonusAvailable(){
		return !isAlreadyDeposit();
	}

	@Override
	public BigDecimal getBalance() {
		return qtdBonus.add(qtdDeposit);
	}

	@Override
	public void applyDelta(BigDecimal delta) {
		if(delta.compareTo(new BigDecimal(0)) >= 0) {
			qtdDeposit = qtdDeposit.add(delta);
		} else {
			delta = delta.multiply(new BigDecimal(-1));
			BigDecimal minusDeposit = delta.min(qtdDeposit);
			setQtdDeposit(qtdDeposit.subtract(minusDeposit));
			delta = delta.subtract(minusDeposit);
			setQtdBonus(qtdBonus.subtract(delta));
		}

	}

	@Override
	public boolean deposit(BigDecimal ammount){
		boolean ret = isBonusAvailable();
		if(!alreadyDeposit && ammount.compareTo(new BigDecimal(100)) >= 0){
			qtdBonus = qtdBonus.add(new BigDecimal(100));
			setAlreadyDeposit(true);
		}
		qtdDeposit = qtdDeposit.add(ammount);
		return ret;
	}

	@Override
	public String toString() {
		return "{" +
			"nome='" + super.getNome() + "'" +
			", email='" + getEmail() + "'" +
			", hashSenha='" + super.getHashSenha() + "'" +
			", accountId='" + super.getId() + "'" +
			", alreadyDeposit='" + isAlreadyDeposit() + "'" +
			", qtdBonus='" + getQtdBonus() + "'" +
			", qtdDeposit='" + getQtdDeposit() + "'" +
			", cpf='" + User.imprimeCpf(getCpf()) + "'" +
			"}";
	}

	private boolean isAlreadyDeposit() {
		return this.alreadyDeposit;
	}

	public void setAlreadyDeposit(boolean alreadyDeposit) {
		this.alreadyDeposit = alreadyDeposit;
	}

	private BigDecimal getQtdBonus() {
		return this.qtdBonus;
	}

	private void setQtdBonus(BigDecimal qtdBonus) {
		this.qtdBonus = qtdBonus;
	}

	private BigDecimal getQtdDeposit() {
		return this.qtdDeposit;
	}

	private void setQtdDeposit(BigDecimal qtdDeposit) {
		this.qtdDeposit = qtdDeposit;
	}
	
	public static String imprimeCpf(String CPF) {
		if(CPF == "null")
			return CPF;
        return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
        CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }
	
	@Override
	public String getCpf() {
		if(this.cpf == null)
			return "null";
		return this.cpf;
	}
	
	

	private void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	private void setEmail(String email) {
		this.email = email;
	}

}
