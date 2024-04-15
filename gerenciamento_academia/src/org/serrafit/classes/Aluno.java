package org.serrafit.classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Aluno extends Pessoa {
	private LocalDate dataMatricula;
	private String avaliacoesFisicas;
	private Plano plano;

	Scanner sc = new Scanner(System.in);

	// Construtor
	public Aluno(String nome, String cpf, LocalDate dNasc, String contato, String senha, LocalDate dataMatricula,
			String avaliacoesFisicas, Plano plano) {
		super(nome, cpf, dNasc, contato, senha);
		this.dataMatricula = dataMatricula;
		this.avaliacoesFisicas = avaliacoesFisicas;
		this.plano = plano;
	}

	// Get & Set
	public LocalDate getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(LocalDate dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public String getAvaliacoesFisicas() {
		return avaliacoesFisicas;
	}

	public void setAvaliacoesFisicas(String avaliacoesFisicas) {
		this.avaliacoesFisicas = avaliacoesFisicas;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	@Override
	public void exibirMenu() {
		int opcao = -1;
		do {
			var mensagem = String.format("""
					============================================
					         BEM VINDO - %S
					============================================

					1 - Visualizar dados pessoais e plano contratado
					2 - Solicitar agendamento de horário com personal trainer;
					3 - Visualizar hitórico de agendamentos
					4 - Cancelar agendamento
					5 - Visualizar avaliações físicas
					0 - Sair
					""", getNome());
			System.out.println(mensagem);

			opcao = sc.nextInt();
			sc.nextLine();
			switch (opcao) {
			case 1:
				exibeDadosPessoais();
				exibePlanoContratado();
				voltarMenu();
				break;
			case 2:
				solicitaAgendamento();
				voltarMenu();
				break;
			case 3:
				visualizarAgendamentos();
				voltarMenu();
				break;
			case 4:
				cancelarAgendamento();
				voltarMenu();
				break;
			case 5:
				visualizarAvaliacoes();
				voltarMenu();
				break;
			case 0:
				sairMenu();
				break;
			default:
				System.out.println("Opção inválida. Por gentileza, selecione uma opção válida");
			}

		} while (opcao != 0);
	}

	public void exibeDadosPessoais() {
		var mensagem = String.format("""
				=============================
					DADOS PESSOAIS
				=============================
				Nome: %s
				CPF: %s
				Data de Nascimento: %s
				Contato: %s
				Data de matrícula: %s
				""", getNome(), getCpf(), getdNasc(), getContato(), dataMatricula);
		System.out.println(mensagem);

	}

	public void exibePlanoContratado() {
		var mensagem = String.format("""
				--------------------------
				PLANO CONTRATADO
				--------------------------
				Plano: %s
				""", plano);
		System.out.println(mensagem);		
	}

	public void solicitaAgendamento() {
		// Criando um novo agendamento
		var mensagem = String.format("""
				==========================
				 SOLICITANDO AGENDAMENTO
				==========================
				""");
		System.out.println(mensagem);	;
		System.out.println("Infome o horário desejado (formato HH:MM): ");
		String horarioInformado = sc.nextLine();
		LocalTime horario = LocalTime.parse(horarioInformado);

		// Mostrar a lista de personais disponíveis.
		System.out.println("Insira o nome do personal desejado: ");
		String nomePersonal = sc.nextLine();
		encontrarPersonalPeloNome(nomePersonal, null);
		// PersonalTrainer personal = encontrarPersonalPeloNome(nomePersonal);
		// Verifica se o personal esta de fato disponível, se sim, cria agendamento
		System.out.println("Insira a data (Formato AAAA-MM-DD): ");
		String dataInformada = sc.nextLine();
		LocalDate data = LocalDate.parse(dataInformada);		

	}

	public void visualizarAgendamentos(List<Agendamento> agenda) {
		var mensagem = String.format("""
				==========================
				      AGENDAMENTOS
				==========================
				""");
		System.out.println(mensagem);		
		for (Agendamento agendamento : agenda) {
			if (agendamento != null) {
				System.out.println(agendamento);
			} else {
				System.out.println("Sem agendamentos registrados!");
			}
		}

	}

	public void cancelarAgendamento() {
		var mensagem = String.format("""
				==========================
				   CANCELAR AGENDAMENTO
				==========================
				""");
		System.out.println(mensagem);	

	}

	public void visualizarAvaliacoes(List<Avaliacao> avaliacoes) {
		var mensagem = String.format("""
				==========================
				      AVALIAÇÕES
				==========================
				""");
		System.out.println(mensagem);	
		for (Avaliacao avaliacao : avaliacoes) {
			if (avaliacao != null) {
				System.out.println(avaliacao);
			} else {
				System.out.println("Sem histórico de avaliações!");
			}
		}
	}

	public void sairMenu() {
		System.out.println("Saindo do Sistema");
		System.exit(0);
	}

	public void voltarMenu() {
		System.out.println("Deseja votar ao Menu: S - sim | N - não");
		String resposta = sc.nextLine();
		if (!resposta.equalsIgnoreCase("s")) {
			sairMenu();
		}
	}
	

	private PersonalTrainer encontrarPersonalPeloNome(String nome, List<Pessoa> cadastros) {
		for (Pessoa personal : cadastros) {
			if (personal instanceof PersonalTrainer && personal.getNome().equals(nome)) {
				return (PersonalTrainer) personal;
			}
		}
		return null;
	}

	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		return super.toString() + String.format("""
				Data Matricula: %s
				Avaliação Física: %s
				Plano: %s
				""", dataMatricula.format(dtf), avaliacoesFisicas, plano);
	}

}
