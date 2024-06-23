package br.com.Faisca.Contas;

import java.math.BigDecimal;

public class User extends Conta {
	
	private boolean firstDeposit;
	private BigDecimal qtdBonus, qtdDeposit;
	private String cpf;
	
	
	public User(String nome, String senha, String cpf) {
		super(nome, senha);
		firstDeposit = true;
		qtdBonus = new BigDecimal(0);
		qtdDeposit = new BigDecimal(0);
		this.cpf = cpf;
	}

	public boolean isBonusAvailable(){
		return firstDeposit;
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
			qtdDeposit = qtdDeposit.subtract(minusDeposit);
			delta = delta.subtract(minusDeposit);
			
			qtdBonus = qtdBonus.subtract(delta);
			
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
			" firstDeposit='" + firstDeposit + "'" +
			", qtdBonus='" + qtdBonus + "'" +
			", qtdDeposit='" + qtdDeposit + "'" +
			", cpf='" + ValidaCPF.imprimeCPF(cpf) + "'" +
			"}";
	}
}
