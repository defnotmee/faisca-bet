package br.com.Faisca.Jogo;
import java.util.Random;

import br.com.Faisca.Jogo.Roleta.RoletaRes;

public class Gerador {
	
	private static final Gerador instance = new Gerador();
	
	private Random rng;
	
	private Gerador(){
		rng = new Random();
	}
	
	public static Gerador getInstance() {
		return instance;
	}
	
	public RoletaRes genRoleta() {
		int res = rng.nextInt(0, 15);
		
		if(res < 7)
			return RoletaRes.PRETO;
		if(res < 14)
			return RoletaRes.VERMELHO;
		return RoletaRes.BRANCO;
	}
	
	public Long genId() {
		return rng.nextLong();
	}
}
