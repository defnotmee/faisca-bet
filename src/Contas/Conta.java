package Contas;

import java.math.BigDecimal;

public abstract class Conta {
    private String name;
    private Long id;
    private BigDecimal balance;
    private BigDecimal qtdDeposit;
    private BigDecimal qtdBonus;
    private BigDecimal qtdLoss;

    public BigDecimal getUserProfit(){
        return balance.subtract(qtdDeposit.add(qtdBonus));
    }
}
