package org.serrafit.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.serrafit.classes.Agendamento;
import org.serrafit.classes.Aluno;
<<<<<<< HEAD
import org.serrafit.classes.Avaliacao;
import org.serrafit.classes.Funcionario;
import org.serrafit.classes.PersonalTrainer;
import org.serrafit.classes.Plano;

public class Registra {
    static List<Aluno> alunos = new ArrayList<>();
    static List<Agendamento> agendamentos = new ArrayList<>();
    static List<PersonalTrainer> personais = new ArrayList<>();
    static List<Plano> planos = new ArrayList<>();
    static List<Funcionario> funcionarios = new ArrayList<>();
    static List<Avaliacao> avaliacoes = new ArrayList<>();

    static Plano planoGold = new Plano("Gold", 98, "Muito bom", 1);
    static Plano planoPlatina = new Plano("Platina", 50, "Excelente", 6);
    static PersonalTrainer personal1 = new PersonalTrainer("Gabriel Santos", "111.222.333-44", LocalDate.of(1990, 5, 3), "22999991111", "123456", LocalTime.of(10, 0), LocalTime.of(18, 0), "12345", "Musculação");
    static PersonalTrainer personal2 = new PersonalTrainer("João Silva", "222.333.444-55", LocalDate.of(1985, 8, 15), "21999992222", "654321", LocalTime.of(8, 0), LocalTime.of(16, 0), "54321", "Crossfit");
    static PersonalTrainer personal3 = new PersonalTrainer("Maria Oliveira", "333.444.555-66", LocalDate.of(1995, 3, 20), "21998887777", "987654", LocalTime.of(12, 0), LocalTime.of(20, 0), "67890", "Yoga");

    static Aluno aluno1 = new Aluno("Lucas Bonafé", "111.222.333-55", LocalDate.of(2005, 5, 1), "22999991111", "12345", LocalDate.of(1990, 5, 3), planoGold);
    static Aluno aluno2 = new Aluno("Mariana Silva", "222.333.444-66", LocalDate.of(2000, 10, 10), "21998887777", "54321", LocalDate.of(1995, 3, 20), planoPlatina);

    static Funcionario funcionario1 = new Funcionario("Matheus Zezé", "2", LocalDate.of(2005, 7, 2), "22999991111", "2", "Arquiteto de banco de dados Sênior");
    static Funcionario funcionario2 = new Funcionario("Ana Sousa", "333.444.555-77", LocalDate.of(1998, 12, 25), "21997776666", "5678", "Analista de sistemas");

    static Avaliacao avaliacao1 = new Avaliacao(aluno1, personal1, "Boa evolução", LocalDate.now());
    static Avaliacao avaliacao2 = new Avaliacao(aluno2, personal3, "Excelente desempenho", LocalDate.of(2024, 3, 15));

    public static List<Avaliacao> criaAvaliacao() {
        avaliacoes.add(avaliacao1);
        avaliacoes.add(avaliacao2);
        return avaliacoes;
    }

    public static List<Aluno> criaAlunos() {
        alunos.add(aluno1);
        alunos.add(aluno2);
        return alunos;
    }

    public static List<Funcionario> criaFuncionario() {
        funcionarios.add(funcionario1);
        funcionarios.add(funcionario2);
        return funcionarios;
    }

    public static void adicionaAgendamentoUnico(Agendamento agendamento) {
        agendamentos.add(agendamento);
    }

    public static List<Agendamento> criaAgendamento() {
        Agendamento agendamento1 = new Agendamento(LocalTime.of(14, 0), aluno1, personal1, LocalDate.of(2005, 5, 7));
        Agendamento agendamento2 = new Agendamento(LocalTime.of(16, 0), aluno2, personal2, LocalDate.of(2000, 10, 12));
        Agendamento agendamento3 = new Agendamento(LocalTime.of(12, 0), aluno1, personal3, LocalDate.of(2005, 5, 9));

        agendamentos.add(agendamento1);
        agendamentos.add(agendamento2);
        agendamentos.add(agendamento3);

        return agendamentos;
    }

    public static List<PersonalTrainer> criaPersonais() {
        personais.add(personal1);
        personais.add(personal2);
        personais.add(personal3);
        return personais;
    }

    public static List<Plano> criarPlano() {
        planos.add(planoGold);
        planos.add(planoPlatina);
        return planos;
=======
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
>>>>>>> 001c5ccf85d3d62e7ddf820942e2e0023f3c2654
    }
}
