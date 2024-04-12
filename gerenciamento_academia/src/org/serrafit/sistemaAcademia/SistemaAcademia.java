package org.serrafit.sistemaAcademia;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.serrafit.classes.Aluno;
import org.serrafit.classes.Funcionario;
import org.serrafit.classes.Validacao;
import org.serrafit.classes.PersonalTrainer;
import org.serrafit.classes.Pessoa;
import org.serrafit.classes.Plano;

public class SistemaAcademia {
	public static void main(String[] args) {
		
		List <Pessoa> cadastros = new ArrayList<>();
		
		Pessoa personal1 = new PersonalTrainer("Gabriel Santos","111.222.333-44",LocalDate.of(1990, 5, 3),"22999991111","123456",LocalTime.of(10, 0),"12345","Musculação");
		Pessoa aluno1 = new Aluno("Lucas Bonafé","111.222.333-55",LocalDate.of(2005, 5, 1),"22999991111","12345",LocalDate.of(1990, 5, 3),"Avaliacao1",new Plano("gold"));
		Pessoa funcionario1 = new Funcionario("Matheus Zezé","111.222.333-66",LocalDate.of(2005, 7, 2),"22999991111","1234","Arquiteto de banco de dados Sênior");
		
		cadastros.add(personal1);
		cadastros.add(aluno1);
		cadastros.add(funcionario1);
		
		//Validacao.login();
		aluno1.exibirMenu();
	}
}


