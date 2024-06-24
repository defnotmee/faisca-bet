package br.com.Faisca.Contas;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public final class LerContas {
	public static List<Conta> lerDeArquivo(InputStream in) throws IOException{
		if(in.available() == 0) {
			return new ArrayList<>();
		}
		
		ObjectInputStream objin = new ObjectInputStream(in);
		
		System.err.println(in.available());
		List<Conta> ret = new ArrayList<>();
		
		Conta conta;
		try {
			do {
		        conta = (Conta) objin.readObject();
		        if(conta != null){
		            ret.add(conta);
		        } 
		    } while (conta != null);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(EOFException e) {
			System.out.println("Sistema completou uma leitura");
		}
		objin.close();
		
		return ret;
	}
}
