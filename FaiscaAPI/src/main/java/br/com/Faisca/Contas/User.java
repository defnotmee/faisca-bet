package br.com.Faisca.Contas;

import java.io.Serializable;
import java.math.BigDecimal;

public class User extends Conta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private boolean firstDeposit;
	private BigDecimal qtdBonus, qtdDeposit;
	private String cpf;
	private String email;
	
	
	public User(String nome, String email, String senha, String cpf) {
		super(nome, senha);
		firstDeposit = true;
		this.email = email;
		qtdBonus = new BigDecimal(0);
		qtdDeposit = new BigDecimal(0);
		this.cpf = cpf;
	}

	public boolean isBonusAvailable(){
		return isFirstDeposit();
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
			BigDecimal minusDeposit = delta.min(qtdDeposit);
			setQtdDeposit(qtdDeposit.subtract(minusDeposit));
			delta = delta.subtract(minusDeposit);
			setQtdBonus(qtdBonus.subtract(delta));
		}

	}

	@Override
	public void deposit(BigDecimal ammount){
		if(firstDeposit && ammount.compareTo(new BigDecimal(100)) >= 0){
			qtdBonus = qtdBonus.add(new BigDecimal(100));
			firstDeposit = true;
		}
		qtdDeposit = qtdDeposit.add(ammount);
	}

	@Override
	public String toString() {
		return "{" +
			"nome='" + super.getNome() + "'" +
			", email='" + getEmail() + "'" +
			", hashSenha='" + super.getHashSenha() + "'" +
			", accountId='" + super.getId() + "'" +
			", firstDeposit='" + isFirstDeposit() + "'" +
			", qtdBonus='" + getQtdBonus() + "'" +
			", qtdDeposit='" + getQtdDeposit() + "'" +
			", cpf='" + getCpf() + "'" +
			"}";
	}

	private boolean isFirstDeposit() {
		return this.firstDeposit;
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
	
	public static String imprimeCPF(String CPF) {
        return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
        CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }
	
	private String getCpf() {
		if(this.cpf == null)
			return "null";
		return User.imprimeCPF(this.cpf);
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
