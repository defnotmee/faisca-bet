package br.com.Faisca.Contas;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public final class EscreverContas {
	public static void escreverEmArquivo(OutputStream out, List<Conta> lista) throws IOException{
		ObjectOutputStream objout = new ObjectOutputStream(out);
		
		for(Conta i : lista) {
			objout.writeObject(i);
		}
		
		objout.flush();
		objout.close();
	}
}
