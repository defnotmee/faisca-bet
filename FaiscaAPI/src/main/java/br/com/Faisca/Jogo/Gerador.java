package br.com.Faisca.Jogo;
import br.com.Faisca.Jogo.Roleta.RoletaRes;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;

public class Gerador {
	
	private static final Gerador instance = new Gerador();
	
	private UniformRandomProvider rng;

	private Gerador(){
		rng = RandomSource.XO_RO_SHI_RO_128_PP.create();
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
	
	public double genFoguete(){
		if(rng.nextInt(100) < 5)
			return 1;

		double y = rng.nextDouble(0,1);
		return 1/(1-y);
	}

	public Long genId() {
		return rng.nextLong();
	}
}
