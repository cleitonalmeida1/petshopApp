package br.ufms.cpcx.engweb.petshop.mb.utils;


import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class SubstituirCaracteres {

	private static Pattern[] patterns;

	private static final String[] replacements = { "" };

	public String removerCaracteresDeString(String string, String[] caracteres) {
		patterns = new Pattern[caracteres.length];
		for (int i = 0; i < patterns.length; i++) {
			patterns[i] = Pattern.compile(caracteres[i]);
			
		}
		for (int i = 0; i < patterns.length; i++) {
			string = patterns[i].matcher(string).replaceAll(replacements[0]);
		}
		return string;
	}

	/**
	public static void main(String[] args) {
		String nome = new SubstituirCaracteres().removerCaracteresDeString("TESTE TESTE2", new String[] { "TESTE" });
		System.out.println(nome);
	}
	*/
}
