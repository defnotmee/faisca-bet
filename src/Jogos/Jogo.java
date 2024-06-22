package Jogos;

import java.math.BigDecimal;
import Contas;

/*
 * Interface para jogos em geral
 * 
 */
public interface Jogo {
    
    public Jogo getInstance();

    public void makeBet(BigDecimal bet);

    public void play();



}   
