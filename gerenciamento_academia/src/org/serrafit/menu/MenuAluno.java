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

public class MenuAluno implements Menu {
	Aluno aluno = null;	
	
	List<PersonalTrainer> personais = new ArrayList<PersonalTrainer>();
		
	public MenuAluno(Aluno aluno) {
		super();
		this.aluno = aluno;
		this.personais = Registra.criaPersonais();
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
				solicitaAgendamento(sc, personais);
				break;
			case 3:
				//visualizarAgendamentos();
				break;
			case 4:
				cancelarAgendamento();
				break;
			case 5:
				//visualizarAvaliacoes();
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

	public void solicitaAgendamento(Scanner sc, List<PersonalTrainer> lista) {
		PersonalTrainer personal = null;
		boolean continuar = true;
		LocalTime horario = null;
		List<Agendamento> agendamentos = new ArrayList<Agendamento>();
		PersonalTrainer personalSelecionado = null;
		LocalDate data = null;
		boolean podeRegistrar = false;
		
		var mensagem = String.format("""
				==========================
				 SOLICITANDO AGENDAMENTO
				==========================
				""");
		System.out.println(mensagem);	
		
		do {
			System.out.println("Informe o horário desejado (formato HH:mm): ");
			String horarioInformado = sc.nextLine();
			horario = LocalTime.parse(horarioInformado);
			
			agendamentos = Registra.criaAgendamento();
			for (int i = 0; i < lista.size(); i++) {
				if(lista.get(i) instanceof PersonalTrainer) {
					if(lista.get(i).getInicioAtendimento().isBefore(horario) && lista.get(i).getFimAtendimento().isAfter(horario)) {
						System.out.println("Personal que atendem no horário: ");
						System.out.println(lista.get(i) + "\n");
						continuar = false;
					} else if(continuar == true && i == lista.size() - 1){
						System.out.println("Não há personais disponíveis no horário desejado.\n");
						return;
					}
				} 
			}
		}while(continuar == true);
		
		continuar = true;
		
		do {
			System.out.println("Selecione o personal pelo CREF:");
			String crefPersonal = sc.next();
			
			personalSelecionado = encontrarPersonalPeloNome(crefPersonal, personais);
			if(personalSelecionado != null) {
				continuar = false;
			} else {
				System.out.println("CREF inválido!");
				voltarMenu(sc);
			}
		}while(continuar == true);

		continuar = true;
		
		do {
			System.out.println("Insira a data (Formato AAAA-MM-DD): ");
			String dataInformada = sc.next();
			data = LocalDate.parse(dataInformada);
			
			agendamentos = Registra.criaAgendamento();
			for (int i = 0; i < agendamentos.size(); i++) {
				if(agendamentos.get(i).getData() == data && agendamentos.get(i).getHorario() == horario && i == agendamentos.size() - 1) {
					System.out.println("Não há horarios disponíveis.");
				} else {
					podeRegistrar = true;
					continuar = false;
				}
			}
		}while(continuar == true);
		
		if(podeRegistrar) {
			Agendamento agendamento = new Agendamento(horario, aluno, personalSelecionado, data);
			Registra.adicionaAgendamentoUnico(agendamento);
			
			System.out.println("\n" + agendamento + "\nAgendado com sucesso!");
		}
	}
	
	private PersonalTrainer encontrarPersonalPeloNome(String cref, List<PersonalTrainer> personais) {
		for (PersonalTrainer personal : personais) {
			if (personal instanceof PersonalTrainer && personal.getCref().equals(cref)) {
				return personal;
			}
		}
		return null;
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

	public void voltarMenu(Scanner sc) {
		String voltar;
		do {
			System.out.print("Deseja voltar ao Menu: S - sim | N - não");
			voltar = sc.nextLine().trim(); // remove espaços em branco antes e depois da entrada

			if (voltar.isEmpty()) { // verifica se a entrada está em branco
				voltar = sc.nextLine().trim();
			}
		} while (voltar.isEmpty()); // continua solicitando entrada até que não esteja em branco

		switch (voltar.toUpperCase()) {
		case "S":
			this.exibirMenu(sc);
			break;
		case "N":
			break;
		default:
			System.out.println("Opção inválida. Por favor, escolha 'S' para sim ou 'N' para não.");
			this.voltarMenu(sc);
		}
	}
}
