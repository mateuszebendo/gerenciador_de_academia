package org.serrafit.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.serrafit.classes.Agendamento;
import org.serrafit.classes.Aluno;
import org.serrafit.classes.Funcionario;
import org.serrafit.classes.PersonalTrainer;
import org.serrafit.classes.Pessoa;
import org.serrafit.classes.Plano;

public class Registra {
	static List <Pessoa> cadastros = new ArrayList<>();
	static List <Agendamento> agendamentos = new ArrayList<>();
	static List <PersonalTrainer> personais = new ArrayList<>();

	static Plano planoGold = new Plano("Gold");
	static PersonalTrainer personal1 = new PersonalTrainer("Gabriel Santos","111.222.333-44",LocalDate.of(1990, 5, 3),"22999991111","123456",LocalTime.of(10, 0),LocalTime.of(18, 0),"12345","Musculação");
	static PersonalTrainer personal2= new PersonalTrainer("Gabriel Santos","111.222.333-44",LocalDate.of(1990, 5, 3),"22999991111","123456",LocalTime.of(6, 0),LocalTime.of(12, 0),"12345","Musculação");
	static PersonalTrainer personal3 = new PersonalTrainer("Gabriel Santos","111.222.333-44",LocalDate.of(1990, 5, 3),"22999991111","123456",LocalTime.of(14, 0),LocalTime.of(15, 0),"12345","Musculação");
	
	static Aluno aluno1 = new Aluno("Lucas Bonafé","111.222.333-55",LocalDate.of(2005, 5, 1),"22999991111","12345",LocalDate.of(1990, 5, 3), planoGold);
	static Pessoa funcionario1 = new Funcionario("Matheus Zezé","111.222.333-66",LocalDate.of(2005, 7, 2),"22999991111","1234","Arquiteto de banco de dados Sênior");


    public static List<Pessoa> criaListas() {
        cadastros.add(personal1);
        cadastros.add(aluno1);
        cadastros.add(funcionario1);
        
        return cadastros;
    }
    
    public static void adicionaAgendamentoUnico(Agendamento agendamento) {
    	agendamentos.add(agendamento);
    }
    
    public static List<Agendamento> criaAgendamento() {
        Agendamento agendamento1 = new Agendamento(LocalTime.of(14, 0), aluno1, personal1, LocalDate.of(2005, 5, 7));
        Agendamento agendamento2 = new Agendamento(LocalTime.of(14, 0), aluno1, personal2, LocalDate.of(2005, 5, 8));
        Agendamento agendamento3 = new Agendamento(LocalTime.of(14, 0), aluno1, personal3, LocalDate.of(2005, 5, 9));
    	
    	agendamentos.add(agendamento1);
		agendamentos.add(agendamento2);
		agendamentos.add(agendamento3);
		
		return agendamentos;
    }
    
    public static List <PersonalTrainer> criaPersonais() {
    	personais.add(personal1);
    	personais.add(personal2);
    	personais.add(personal3);
    	
    	return personais;
    }
}
