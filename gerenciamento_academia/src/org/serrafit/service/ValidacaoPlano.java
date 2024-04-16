package org.serrafit.service;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.serrafit.classes.Plano;

public class ValidacaoPlano {
	public static String validaPlano (Scanner sc, List <Plano> listaPlanos) throws InterruptedException {
		boolean verificacao = false;
		String nomePlano = null;
		
		while(!verificacao) {
		System.out.print("Planos Disponiveis: ");
	    System.out.println(listaPlanos);
	    System.out.println("Digite o nome do plano desejado - ");
	    nomePlano = sc.nextLine();
	    
		    for(Plano plano: listaPlanos) {
		    	if(nomePlano.equalsIgnoreCase(plano.getNome())) verificacao = true;
		    } if(!verificacao) {
		    	System.out.println("Digite um nome válido!");
		    	Thread.sleep(1000);
		    }
	    }
		
		return nomePlano;
	}
	
	public static int posicaoPlano (List<Plano> listaPlanos, String nomePlano) {
		 int planoEscolhido = 0;
		    for(int i = 0; i < listaPlanos.size(); i++) {
		    	if(nomePlano.equalsIgnoreCase(listaPlanos.get(i).getNome())){
		    		planoEscolhido = i; 
		    	}
		    }
		    return planoEscolhido;
	}
	
	public static double validaPreco (Scanner sc) {
		String valorString = "";
		double valorNum = 0;
		Pattern padrao = Pattern.compile("^[0-9.]+$");
		Matcher comparador = null;
		while(true) {
			System.out.println("Insira o valor do plano: ");
			System.out.print("$");
			valorString = sc.nextLine();
			
			comparador = padrao.matcher(valorString);
			
			if(comparador.matches()) {
				valorNum = Double.parseDouble(valorString);
				return valorNum;
			}
			System.out.println("Valor de plano inválido!");
		}
	}
	
	public static String validaDescricao(Scanner sc) {
		String descricao = null;
		do {
			System.out.println("Digite uma descrição: \n(máx: 200 caracteres)");
			descricao = sc.nextLine();
			if(descricao.length() <= 200) return descricao;
			else System.out.println("Descrição muito longa - Máx: 200 caracteres");
		}while(true);
	}
}
