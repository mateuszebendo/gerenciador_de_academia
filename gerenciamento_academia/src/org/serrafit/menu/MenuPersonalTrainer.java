package org.serrafit.menu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.serrafit.classes.Agendamento;
import org.serrafit.classes.Aluno;
import org.serrafit.classes.Avaliacao;
import org.serrafit.classes.PersonalTrainer;
import org.serrafit.service.Registra;
import org.serrafit.service.ValidacaoAvaliacao;
import org.serrafit.service.ValidacaoPersonal;
import org.serrafit.service.ValidacaoPessoa;
import org.serrafit.service.ValidacaoPlano;

public class MenuPersonalTrainer implements Menu{
	PersonalTrainer personalTrainer =  null;
	 List<Avaliacao> listaAvaliacoes = new ArrayList<>();
	 List<Aluno> listaAlunos = new ArrayList<>();
	 List<Agendamento> listaAgendamentos = new ArrayList<>();
	
	public MenuPersonalTrainer(PersonalTrainer personalTrainer) {
		super();
		this.personalTrainer = personalTrainer;
		this.listaAvaliacoes = Registra.criaAvaliacao();
		this.listaAlunos = Registra.criaAlunos();
		this.listaAgendamentos = Registra.criaAgendamento();
	}

	@Override
	public void exibirMenu(Scanner sc) throws InterruptedException {
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
					""", personalTrainer.getNome());
			System.out.println(mensagem);
			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				registrarAvaliacao(sc, listaAlunos);
				break;
			case 2:
				visualizarAgenda(sc, listaAgendamentos);
				break;
			case 3:
				visualizarListaAvaliacoes(sc, listaAvaliacoes);
				break;
			case 0:
				sairMenu();
				break;
			default:
				System.out.println("Opção inválida. Por gentileza, selecione uma opção válida");
			}

		} while (opcao != 0);
	}

	public void registrarAvaliacao(Scanner sc, List <Aluno> listaAlunos) throws InterruptedException {
		var mensagem = String.format("""
				==========================
				 REGISTRAR NOVA AVALIAÇÃO
				==========================
				""");
		System.out.println(mensagem);
		
		Aluno alunoAvaliacao = ValidacaoPersonal.validaAlunoExistente(sc, listaAlunos);
		
		LocalDate dataInformada = ValidacaoAvaliacao.validaData(sc);
		
		String descricao = ValidacaoPlano.validaDescricao(sc);
		
		Registra.adicionaAvaliacaoUnica(new Avaliacao(alunoAvaliacao, personalTrainer, descricao, dataInformada));
		
		System.out.println("Avaliação cadastrado com sucesso!");
	}

	public void visualizarAgenda(Scanner sc, List<Agendamento> agenda) throws InterruptedException {
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
		
		exibirMenu(sc);
	}

	public void visualizarListaAvaliacoes(Scanner sc, List<Avaliacao> avaliacoes) throws InterruptedException  {
		boolean continuar = false;
		var mensagem = String.format("""
				==========================
				      AVALIAÇÕES
				==========================
				""");
		System.out.println(mensagem);	
		for (int i = 0; i < avaliacoes.size(); i++) {
			if (avaliacoes.get(i).getPersonal().getCref() == personalTrainer.getCref()) {
				System.out.println("Aluno: " + avaliacoes.get(i).getAluno().getNome() + "\nPersonal: " + avaliacoes.get(i).getPersonal().getNome() + "\nDescrição: " + avaliacoes.get(i).getDescricao() + "\nData da avaliação: " + avaliacoes.get(i).getData() + "\n");
				continuar = true;
			} else if(continuar == false && i == avaliacoes.size() - 1){
				System.out.println("Sem Avaliações registradas!\n");
			}
		}
		
		exibirMenu(sc);
	}

	public void voltarMenu(Scanner sc) throws InterruptedException {
		String voltar = null;
		boolean continua = true;
		while (continua); {
			System.out.print("Deseja votar ao Menu: S - sim | N - não");
			voltar = sc.nextLine().trim(); // remove espaços em branco antes e depois da entrada

			 if (voltar.isEmpty() || (!(voltar.equalsIgnoreCase("S") || voltar.equalsIgnoreCase("N")))) { // verifica se a entrada está em branco ou não é S nem N
			        System.out.println("Escreva um valor válido: S - sim | N - não");
			    } else if (voltar.equalsIgnoreCase("S") || voltar.equalsIgnoreCase("N")) {
			        continua = false;
			    }
		}

		switch (voltar.toUpperCase()) {
		case "S":
			exibirMenu(sc);
		case "N":
			break;
		default:
			System.out.println("Opção inválida. Por favor, escolha 'S' para sim ou 'N' para não.");
			this.voltarMenu(sc);

		}
	}

	public void sairMenu() {
		System.out.println("Saindo do Sistema");
		System.exit(0);
	}
}
