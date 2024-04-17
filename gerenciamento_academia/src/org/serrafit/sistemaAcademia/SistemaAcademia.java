package org.serrafit.sistemaAcademia;

import java.util.Scanner;

import org.serrafit.menu.Menu;
import org.serrafit.service.Registra;
import org.serrafit.service.ValidacaoLogin;

public class SistemaAcademia {
	public static void main(String[] args) throws InterruptedException{
		Scanner sc = new Scanner(System.in);		
		Menu menu =null;

		menu = ValidacaoLogin.login(sc, Registra.criaCadastros());
		menu.exibirMenu(sc);
		
	}
}


