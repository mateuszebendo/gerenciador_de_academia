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
	List<Agendamento> agendamentos = new ArrayList<Agendamento>();
		
	public MenuAluno(Aluno aluno) {
		super();
		this.aluno = aluno;
		this.personais = Registra.criaPersonais();
		this.agendamentos = Registra.criaAgendamento();
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
				voltarMenu(sc);
				break;
			case 2:
				solicitaAgendamento(sc, personais, agendamentos);
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

	public void solicitaAgendamento(Scanner sc, List<PersonalTrainer> listaPersonal, List<Agendamento> listaAgendamento) {
		LocalTime horario = null;
		LocalDate data = null;
		
		var mensagem = String.format("""
				==========================
				 SOLICITANDO AGENDAMENTO
				==========================
				""");
		System.out.println(mensagem);	
		
		// o solicitaAgendamento tem que captar horario e data e ver quais personais atendem naquele horario escolhido e checar se o personal ja tem um agendamento para aquela hora e data
			System.out.println("Informe o horário desejado (formato HH:mm): ");
			String horarioInformado = sc.nextLine();
			horario = LocalTime.parse(horarioInformado);
			
			System.out.println("Insira a data (Formato AAAA-MM-DD): ");
			String dataInformada = sc.next();
			data = LocalDate.parse(dataInformada);
			
			for(int i = 0; i < listaPersonal.size(); i++) {
				if(listaPersonal.get(i).getInicioAtendimento().isBefore(horario) && listaPersonal.get(i).getFimAtendimento().isAfter(horario)) {
					System.out.println(listaPersonal.get(i));
				}
			}
			
			System.out.println("Informe o Personal desejado pelo CREF: ");
			String crefSelecionado = sc.next();
			
			PersonalTrainer personalSelecionado = encontrarPersonalPeloCref(crefSelecionado, listaPersonal);
			
			Agendamento agendamento = new Agendamento(horario, this.aluno, personalSelecionado, data);
			Registra.adicionaAgendamentoUnico(agendamento);
			System.out.println("Agendamento concluido!");
			
			
			
	    }
	
	private PersonalTrainer encontrarPersonalPeloCref(String cref, List<PersonalTrainer> personais) {
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
		boolean voltarAoMenu = false; 
		
		do {
			System.out.print("Deseja voltar ao Menu: S - sim | N - não");
			voltar = sc.nextLine().trim(); // remove espaços em branco antes e depois da entrada

		switch (voltar.toUpperCase()) {
		case "S":
			this.exibirMenu(sc);
			voltarAoMenu = true;
			break;
		case "N":
			sairMenu();
			break;
		default:
			System.out.println("Opção inválida. Por favor, escolha 'S' para sim ou 'N' para não.");
		}
		
		} while (!voltar.equalsIgnoreCase("S") && !voltar.equalsIgnoreCase("N")); // enqnt voltar for diferente de s ou n ele repete a funçao
	}
}
