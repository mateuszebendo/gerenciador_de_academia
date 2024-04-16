package org.serrafit.menu;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import org.serrafit.classes.Agendamento;
import org.serrafit.classes.Aluno;
import org.serrafit.classes.Avaliacao;
import org.serrafit.classes.PersonalTrainer;
import org.serrafit.classes.Pessoa;

public class MenuAluno implements Menu {
	Aluno aluno = null;	
		
	public MenuAluno(Aluno aluno) {
		super();
		this.aluno = aluno;
	}

	@Override
	public void exibirMenu(Scanner sc) {
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
					""", aluno.getNome());
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
				""", aluno.getNome(), aluno.getCpf(), aluno.getDataNascimento(), aluno.getContato(), aluno.getDataMatricula());
		System.out.println(mensagem);

	}

	public void exibePlanoContratado() {
		var mensagem = String.format("""
				--------------------------
				PLANO CONTRATADO
				--------------------------
				Plano: %s
				""", aluno.getPlano());
		System.out.println(mensagem);		
	}

	public void solicitaAgendamento(Scanner sc) {
		
		var mensagem = String.format("""
				==========================
				 SOLICITANDO AGENDAMENTO
				==========================
				""");
		System.out.println(mensagem);	
		//DUVIDA: Como acessar qualquer tipo de armazenamento por esse método 
		System.out.println("Infome o horário desejado (formato HH:mm): ");
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
}
