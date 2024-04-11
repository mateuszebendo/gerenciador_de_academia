package org.serrafit.classes;

import java.util.List;
import java.util.Scanner;

public class Validacao {
	public static void login(List<Pessoa> lista) {
		boolean cpfEstaCorreto = false;
		boolean senhaEstaCorreta = false;
		int usuarioLogando = 0;
		
		Scanner sc = new Scanner(System.in);
		
		//Looping de inserção de CPF
		do {
			System.out.println("Insira seu CPF: ");
			String validaCPF = sc.next();
			
			//Verifica CPF inserido
			for (int i = 0; i < lista.size(); i++) {
				if(validaCPF.equals(lista.get(i).getCpf())){
					cpfEstaCorreto = true;
					usuarioLogando = i;
				} else if(i == lista.size() - 1 && cpfEstaCorreto == false) {
					System.out.println("CPF Inválido ou não cadastrado, tente novamente!");
				}
			}
			sc.nextLine();
		}while(cpfEstaCorreto == false);
		
		//Looping de inserção de Senha
		do {
			System.out.println("Insira sua Senha: ");
			String validaSenha = sc.next();
			
			//Verifica Senha inserida
			if(validaSenha.equals(lista.get(usuarioLogando).getSenha())){
				senhaEstaCorreta = true;
			} else {
				System.out.println("Senha inválida, tente novamente!");
			}
		}while(senhaEstaCorreta == false);
		sc.close();

		
		//Exibe um menu de acordo com a Classe de quem logou
		lista.get(usuarioLogando).exibirMenu();
	}
}
