package org.serrafit.sistemaAcademia;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.serrafit.classes.Pessoa;
import org.serrafit.menu.Menu;
import org.serrafit.registro.RegistroCadastro;
import org.serrafit.service.ValidacaoLogin;

public class SistemaAcademia {
	public static void main(String[] args) throws InterruptedException, IOException{
		Scanner sc = new Scanner(System.in);		
		Menu menu =null;
		
		List <Pessoa> cadastros = RegistroCadastro.criaCadastro();

		menu = ValidacaoLogin.login(sc, cadastros);
		menu.exibirMenu(sc);
		
	}
}


