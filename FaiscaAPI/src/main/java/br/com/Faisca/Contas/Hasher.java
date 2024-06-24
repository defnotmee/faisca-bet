package br.com.Faisca.Contas;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Hasher {
	public static byte[] hash(String s) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			return digest.digest(s.getBytes(StandardCharsets.UTF_8));
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Fudeu, não achou SHA-256");
			e.printStackTrace();
		}
		
		return null;
	}
}
