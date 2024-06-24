package br.com.Faisca.Contas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ContasDAO {
	
	static ContasDAO instance;
	String path;
	List<Conta> snapshot;
	
	private ContasDAO(String path) {
		System.err.println("Meu path é " + path);
		this.path = path;
	}
	
	public static ContasDAO getInstance(String path) {
		if(instance == null)
			instance = new ContasDAO(path);
		return instance;
	}
	
	
	public List<Conta> getSnapshot() throws FileNotFoundException, IOException {
		if(snapshot == null)
			snapshot = LerContas.lerDeArquivo(new FileInputStream(path));
		
		System.out.println("Snapshot atual: " + snapshot.toString());
		return snapshot;
	}

	public Conta accessId(Long Id) throws FileNotFoundException, IOException{
		List<Conta> cur = getSnapshot();
		
		for(Conta i : cur) {
			if(i.getId().equals(Id)){
				return i;
			}
		}
		
		System.err.println("Usuário procurou por Id inexistente.");
		
		return null;
	}
	
	public Conta accessEmail(String email) throws FileNotFoundException, IOException {
		List<Conta> cur = getSnapshot();
		
		for(Conta i : cur) {
			if(i.getEmail().equals(email)){
				return i;
			}
		}
		return null;
	}
	
	public void clearSnapshot() {
		this.snapshot = null;
	}
	
	public void persist() throws FileNotFoundException, IOException {
		EscreverContas.escreverEmArquivo(new FileOutputStream(path),getSnapshot());
	}
	
	public void insert(Conta conta) throws FileNotFoundException, IOException {
		getSnapshot().add(conta);
	}
}