package org.serrafit.sistemaAcademia;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.serrafit.classes.Agendamento;
import org.serrafit.classes.Aluno;
import org.serrafit.classes.Funcionario;
import org.serrafit.classes.PersonalTrainer;
import org.serrafit.classes.Pessoa;
import org.serrafit.classes.Plano;
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


