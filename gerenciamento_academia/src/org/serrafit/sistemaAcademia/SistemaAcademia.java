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
import org.serrafit.service.ValidacaoLogin;

public class SistemaAcademia {
	public static void main(String[] args) throws InterruptedException{
		Scanner sc = new Scanner(System.in);		
		Menu menu =null;
		List <Pessoa> cadastros = new ArrayList<>();
		List <Agendamento> agendamentos = new ArrayList<>();
		Plano planoGold = new Plano("Gold", 98, "Muito bom", 1);
		
		PersonalTrainer personal1 = new PersonalTrainer("Gabriel Santos","1",LocalDate.of(1990, 5, 3),"22999991111","1",LocalTime.of(10, 0),LocalTime.of(18, 0),"12345","Funcionario");
		Aluno aluno1 = new Aluno("Lucas Bonafé","111.222.333-55",LocalDate.of(2005, 5, 1),"22999991111","12345",LocalDate.of(1990, 5, 3), planoGold);
		Funcionario funcionario1 = new Funcionario("Matheus Zezé","2",LocalDate.of(2005, 7, 2),"22999991111","2","Arquiteto de banco de dados Sênior");
		Agendamento agendamento1 = new Agendamento(LocalTime.of(14, 0), aluno1, personal1, LocalDate.of(2005, 5, 7));
//		Agendamento agendamento2 = new Agendamento(LocalTime.of(14, 0), aluno1, personal1, LocalDate.of(2005, 5, 8));
//		Agendamento agendamen1to3 = new Agendamento(LocalTime.of(14, 0), aluno1, personal1, LocalDate.of(2005, 5, 9));
//		
		cadastros.add(personal1);
		cadastros.add(aluno1);
		cadastros.add(funcionario1);
//		
		agendamentos.add(agendamento1);		
//		agendamentos.add(agendamento2);		
//		agendamentos.add(agendamento3);
//		
		menu = ValidacaoLogin.login(sc, cadastros);
		menu.exibirMenu(sc);
		
	}
}


