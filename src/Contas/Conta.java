package Contas;

import java.math.BigDecimal;

public abstract class Conta {
    String name;
    Long id;
    BigDecimal balance;
    BigDecimal qtdDeposit;
    BigDecimal qtdBonus;
    BigDecimal qtdLoss;

    BigDecimal getUserProfit(){
        return balance-qtdDeposit-qtdBonus;
    }
}
