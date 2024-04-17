package org.serrafit.menu;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.serrafit.classes.Agendamento;
import org.serrafit.classes.Aluno;
import org.serrafit.classes.Avaliacao;
import org.serrafit.classes.PersonalTrainer;
import org.serrafit.classes.Pessoa;
import org.serrafit.service.Registra;
import org.serrafit.service.ValidacaoAluno;

public class MenuAluno implements Menu {
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_RESET = "\u001B[0m";
	Aluno aluno = null;

	List<PersonalTrainer> personais = new ArrayList<PersonalTrainer>();
	List<Agendamento> agendamentos = new ArrayList<Agendamento>();
	List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();

	public MenuAluno(Aluno aluno) {
		super();
		this.aluno = aluno;
		this.personais = Registra.criaPersonais();
		this.agendamentos = Registra.criaAgendamento();
		this.avaliacoes = Registra.criaAvaliacao();
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
				break;
			case 2:
				solicitaAgendamento(sc, personais, agendamentos);
				break;
			case 3:
				visualizarAgendamentos(agendamentos);
				break;
			case 4:
				cancelarAgendamento(sc, agendamentos);
				break;
			case 5:
				visualizarAvaliacoes(avaliacoes);
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
				""", aluno.getNome(), aluno.getCpf(), aluno.getDataNascimento(), aluno.getContato(),
				aluno.getDataMatricula());
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

	public void solicitaAgendamento(Scanner sc, List<PersonalTrainer> listaPersonal,
			List<Agendamento> listaAgendamento) {
		LocalTime horario = null;
		LocalDate data = null;
		PersonalTrainer personalSelecionado = null;
		boolean achouPersonal = false;

		var mensagem = String.format("""
				==========================
				 SOLICITANDO AGENDAMENTO
				==========================
				""");
		System.out.println(mensagem);

		do {
			horario = ValidacaoAluno.validaHorario(sc);

			data = ValidacaoAluno.validaData(sc);

			System.out.println("\nPersonais que trabalham no horário: \n");
			achouPersonal = ValidacaoAluno.verificarPersonalDisponivel(listaPersonal, horario);
			if (achouPersonal == false) {
				voltarMenu(sc);				
			}
		} while (achouPersonal == false);

		personalSelecionado = ValidacaoAluno.selecionaPersonal(listaPersonal, sc);

		Registra.adicionaAgendamentoUnico(new Agendamento(horario, this.aluno, personalSelecionado, data));
		System.out.println(ANSI_GREEN + "Agendamento concluido!" + ANSI_RESET);
	}

	public void visualizarAgendamentos(List<Agendamento> agenda) {
		boolean continuar = false;
		var mensagem = String.format("""
				==========================
				      AGENDAMENTOS
				==========================
				""");
		System.out.println(mensagem);

		for (int i = 0; i < agenda.size(); i++) {
			if (agenda.get(i).getAluno().getCpf() == aluno.getCpf()) {
				System.out.println(agenda.get(i));
				continuar = true;
			} else if (continuar == false && i == agenda.size() - 1) {
				System.out.println("Sem agendamentos registrados!\n");
			}
		}
	}

	public void cancelarAgendamento(Scanner sc, List<Agendamento> agenda) {
		var mensagem = String.format("""
				==========================
				   CANCELAR AGENDAMENTO
				==========================
				""");
		System.out.println(mensagem);

		visualizarAgendamentos(agendamentos);

		ValidacaoAluno.removeAgendamento(sc, agenda);

	}

	public void visualizarAvaliacoes(List<Avaliacao> avaliacoes) {
		boolean continuar = false;
		var mensagem = String.format("""
				==========================
				      AVALIAÇÕES
				==========================
				""");

		System.out.println(mensagem);
		for (int i = 0; i < avaliacoes.size(); i++) {
			if (avaliacoes.get(i).getAluno().getCpf() == aluno.getCpf()) {
				System.out.println("Aluno: " + aluno.getNome() + "\nPersonal: "
						+ avaliacoes.get(i).getPersonal().getNome() + "\nDescrição: " + avaliacoes.get(i).getDescricao()
						+ "\nData da avaliação: " + avaliacoes.get(i).getData() + "\n");
				continuar = true;
			} else if (continuar == false && i == avaliacoes.size() - 1) {
				System.out.println("Sem Avaliações registradas!\n");
			}
		}
	}

	public void sairMenu() {
		System.out.println("Saindo do Sistema");
		System.exit(0);
	}
	
	public void voltarMenu(Scanner sc) {
        String voltar;
        boolean opcaoValida = false;

        do {
            System.out.println("Voltar ao Menu? (S/N)");
            voltar = sc.next().trim().toUpperCase();

            if (voltar.equals("S")) {
                exibirMenu(sc);
                opcaoValida = true;
            } else if (voltar.equals("N")) {
                opcaoValida = true;
            } else {
                System.err.println("Opção inválida.\nPor favor, escolha 'S' para sim ou 'N' para não.");
            }
        } while (!opcaoValida);
    }
}
