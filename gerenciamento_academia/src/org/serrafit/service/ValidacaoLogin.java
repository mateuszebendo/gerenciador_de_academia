package org.serrafit.service;


import java.util.List;
import java.util.Scanner;

import org.serrafit.DAO.ValidacaoDAO;
import org.serrafit.classes.Aluno;
import org.serrafit.classes.Funcionario;
import org.serrafit.classes.PersonalTrainer;
import org.serrafit.classes.Pessoa;
import org.serrafit.menu.Menu;
import org.serrafit.menu.MenuAluno;
import org.serrafit.menu.MenuFuncionario;
import org.serrafit.menu.MenuPersonalTrainer;

public class ValidacaoLogin {
	public static Menu login(Scanner sc, List<Pessoa> cadastros) {
		boolean loginConcluido = false;
		//int usuarioLogando = 0;
		String tipoUsuario = null;		
		
		//Looping de inserção de CPF
		String cpfCorreto = null;
		String senhaCorreta = null;
		int usuarioConectado = 0;
		do {
			System.out.println("Insira seu CPF: ");
			String validaCPF = sc.next();
			System.out.println("Insira sua Senha: ");
			String validaSenha = sc.next();
			
			//Verifica CPF inserido
			//tipoUsuario = validacaoBD.conectarUsuario(validaCPF, validaSenha);
//			if(tipoUsuario != null) {
//				loginConcluido = true;
//			}
			for (int i = 0; i < cadastros.size(); i++ ) {
				cpfCorreto = cadastros.get(i).getCpf();
				senhaCorreta = cadastros.get(i).getSenha();
				
				if (cpfCorreto.equals(validaCPF) && senhaCorreta.equals(validaSenha)) {
					System.out.println("Logou");
					loginConcluido = true;
					usuarioConectado = i;
				} else if (loginConcluido == false && i == cadastros.size() -1) {		
				
					System.err.println("Usuário não encontrado!");
				}
			}
			
			sc.nextLine();
		}while(loginConcluido == false);
		
		
		
		if (cadastros.get(usuarioConectado) instanceof Aluno) {			
			return new MenuAluno((Aluno) cadastros.get(usuarioConectado));
		} else if (cadastros.get(usuarioConectado) instanceof PersonalTrainer) {
			return new MenuPersonalTrainer((PersonalTrainer) cadastros.get(usuarioConectado));			
		}  else if (cadastros.get(usuarioConectado) instanceof Funcionario) {
			return new MenuFuncionario((Funcionario) cadastros.get(usuarioConectado));
		}else {
			System.err.println("Erro ao conectar");
			return null;
		}
		
		
		
	}
}
