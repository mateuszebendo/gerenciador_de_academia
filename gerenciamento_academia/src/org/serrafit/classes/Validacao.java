package org.serrafit.classes;

import java.util.Scanner;

import org.serrafit.DAO.ValidacaoDAO;

public class Validacao {
	public static Menu login() {
		boolean loginConcluido = false;
		ValidacaoDAO validacaoBD = new ValidacaoDAO();
		String tipoUsuario = null;
		Menu menu = null;
		
		Scanner sc = new Scanner(System.in);
		
		//Looping de inserção de CPF
		do {
			System.out.println("Insira seu CPF: ");
			String validaCPF = sc.next();
			System.out.println("Insira sua Senha: ");
			String validaSenha = sc.next();
			
			//Verifica CPF e Senha inseridos
			tipoUsuario = validacaoBD.conectarUsuario(validaCPF, validaSenha);
			if(tipoUsuario != null) {
				loginConcluido = true;
				
			}
			sc.nextLine();
		}while(loginConcluido == false);
		
		sc.close();
		
		if(tipoUsuario.equalsIgnoreCase("Funcionario")) {
			menu = new MenuFuncionario();
		}
		return menu;
	}
}
