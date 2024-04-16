package org.serrafit.menu;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.serrafit.classes.Agendamento;
import org.serrafit.classes.Avaliacao;
import org.serrafit.classes.PersonalTrainer;

public class MenuPersonalTrainer implements Menu{
	PersonalTrainer personalTrainer =  null;
	
	
	
	public MenuPersonalTrainer(PersonalTrainer personalTrainer) {
		super();
		this.personalTrainer = personalTrainer;
	}

	@Override
	public void exibirMenu(Scanner sc) {
		int opcao = -1;
		do {
			var mensagem = String.format("""
					============================================
					         BEM VINDO - %S
					============================================

					1 - Registrar avaliações físicas dos alunos
					2 - Visualizar agenda de atendimentos
					3 - Visualizar lista de avaliações
					0 - Sair
					""", getNome());
			System.out.println(mensagem);
			opcao = sc.nextInt();
			sc.nextLine();

			switch (opcao) {
			case 1:
				registrarAvaliacao();
				voltarMenu();
				break;
			case 2:
				visualizarAgenda();
				voltarMenu();
				break;
			case 3:
				visualizarListaAvaliacoes();
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

	public void registrarAvaliacao() {
		var mensagem = String.format("""
				==========================
				 REGISTRAR NOVA AVALIAÇÃO
				==========================
				""");
		System.out.println(mensagem);	
		System.out.println("Informe o CPF do aluno: ");
		String cpf = sc.next();
		//metodo encontrar aluno
		System.out.println("Data do Agendamento(YYYY-MM-DD): ");
		String dataInformada = sc.next();
		LocalDate data = LocalDate.parse(dataInformada); //testar como salva a informação
		System.out.println("Digite a descrição dessa Avaliação: ");
		String descricao = sc.next();
		
		Avaliacao avaliacao = new Avaliacao(Aluno aluno, Personal personal, descricao, data);
	}

	public void visualizarAgenda(List<Agendamento> agenda) {
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

	public void visualizarListaAvaliacoes(List<Avaliacao> avaliacoes) {
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

	public void voltarMenu() {
		String voltar;
		do {
			System.out.print("Deseja votar ao Menu: S - sim | N - não");
			voltar = sc.nextLine().trim(); // remove espaços em branco antes e depois da entrada

			if (voltar.isEmpty()) { // verifica se a entrada está em branco
				voltar = sc.nextLine().trim();
			}
		} while (voltar.isEmpty()); // continua solicitando entrada até que não esteja em branco

		switch (voltar.toUpperCase()) {
		case "S":
			exibirMenu();
		case "N":
			break;
		default:
			System.out.println("Opção inválida. Por favor, escolha 'S' para sim ou 'N' para não.");
			this.voltarMenu();

		}
	}

	public void sairMenu() {
		System.out.println("Saindo do Sistema");
		System.exit(0);
	}
}
}
