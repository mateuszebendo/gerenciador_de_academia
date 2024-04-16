package org.serrafit.service;

import java.util.List;
import java.util.Scanner;
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
		    if(planoEscolhido == 0) {
		    	return -1;
		    }
		    return planoEscolhido;
	}
	
	public static int validaPreco (Scanner sc) {
		boolean numeroCorreto = false; 
		int valor = 0;
		while(!numeroCorreto) {
			System.out.println("Valor do plano:");
			System.out.println("$");
			valor = sc.nextInt();
			if(valor >= 0) {
				System.out.println("Digite um valor maior que zero!");
			} else {
				numeroCorreto = true;
			}
		}
		return valor;
	}
	
	public static String validaDescricao(Scanner sc) {
		String regex = "^.{0,200}$";
		String descricao = null;
		do {
			System.out.println("Digite uma descrição para o plano: \n(máx: 200 caracteres)");
			descricao = sc.nextLine();
		}while(Pattern.matches(regex, descricao));
		
		return descricao;
	}
}
