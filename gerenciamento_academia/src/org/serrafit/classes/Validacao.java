package org.serrafit.classes;

import java.util.List;
import java.util.Scanner;

import org.serrafir.DAO.ValidacaoDAO;

public class Validacao {
	public static void login() {
		boolean loginConcluido = false;
		int usuarioLogando = 0;
		ValidacaoDAO validacaoBD = new ValidacaoDAO();
		String tipoUsuario = null;
		
		Scanner sc = new Scanner(System.in);
		
		//Looping de inserção de CPF
		do {
			System.out.println("Insira seu CPF: ");
			String validaCPF = sc.next();
			System.out.println("Insira sua Senha: ");
			String validaSenha = sc.next();
			
			//Verifica CPF inserido
			tipoUsuario = validacaoBD.conectarUsuario(validaCPF, validaSenha);
			if(tipoUsuario != null) {
				loginConcluido = true;
			}
			sc.nextLine();
		}while(loginConcluido == false);
		
		sc.close();

		//Exibe um menu de acordo com a Classe de quem logou
		exibirMenu(tipoUsuario);
	}
}
