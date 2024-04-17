package org.serrafit.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import org.serrafit.classes.Agendamento;
import org.serrafit.classes.PersonalTrainer;

public class ValidacaoAluno {

	public static LocalTime validaHorario(Scanner sc) {
		LocalTime horario = null;
		LocalTime inicioExpediente = LocalTime.of(5, 0);
		LocalTime fimExpediente = LocalTime.of(23, 0);
		String desejaSair = "";
		String horarioInformado = null;

		do {
			System.out.println("Informe o horário desejado das 5h às 22h(formato HH:mm): ");
			horarioInformado = sc.next();
			horario = LocalTime.parse(horarioInformado);

			if (horario.isBefore(fimExpediente) && horario.isAfter(inicioExpediente)) {
				return horario;
			} else {
				System.out.println("Horário inválido, fora de expediente!");
				System.out.println("Deseja voltar ao menu?(S ou N):");
				desejaSair = sc.next();
				if (desejaSair.equalsIgnoreCase("s")) {
					return null;
				}
			}
		} while (true);
	}

	public static LocalDate validaData(Scanner sc) {
		LocalDate data = null;
		String voltar = "";
		boolean opcaoValida = false;

		do {
			System.out.println("Insira a data (Formato AAAA-MM-DD): ");
			String dataInformada = sc.next();
			data = LocalDate.parse(dataInformada);
			if (data.isBefore(LocalDate.now())) {
				System.out.println("Data inválida.");
			} else {
			return data;
			}
		} while (true);
	}

	public static boolean verificarPersonalDisponivel(List<PersonalTrainer> listaPersonal, LocalTime horario) {
		PersonalTrainer personal = null;
		boolean achouPersonal = false;
		for (int i = 0; i < listaPersonal.size(); i++) {
			if (listaPersonal.get(i).getInicioAtendimento().isBefore(horario)
					&& listaPersonal.get(i).getFimAtendimento().isAfter(horario)) {
				System.out.println(listaPersonal.get(i));
				achouPersonal = true;
			} else if (i == listaPersonal.size() - 1 && achouPersonal == false) {
				System.out.println("Não há Personais disponíveis nesse horario");

			}
		}
		return achouPersonal;
	}

	public static PersonalTrainer selecionaPersonal(List<PersonalTrainer> listaPersonal, Scanner sc) {
		PersonalTrainer personalSelecionado = null;
		String desejaSair = "";

		do {
			System.out.println("Informe o Personal desejado pelo CREF: ");
			String crefSelecionado = sc.next();

			for (int i = 0; i < listaPersonal.size(); i++) {
				if (listaPersonal.get(i) instanceof PersonalTrainer
						&& listaPersonal.get(i).getCref().equals(crefSelecionado)) {
					personalSelecionado = listaPersonal.get(i);
					return personalSelecionado;
				} else if (personalSelecionado == null && i == listaPersonal.size() - 1) {
					System.out.println("Cref inválido!");
					System.out.println("Deseja voltar ao menu?(S ou N):");
					desejaSair = sc.next();
					if (desejaSair.equalsIgnoreCase("s")) {
						return null;
					}
				}
			}
		} while (true);
	}

	public static void removeAgendamento(Scanner sc, List<Agendamento> agenda) {
		String horarioInformado = null;
		LocalTime horarioDeletado = null;
		boolean continuar = true;
		String desejaSair = null;
		LocalDate dataDeletada = null;

		do {
			System.out.println("Insira a data (Formato AAAA-MM-DD): ");
			String dataInformada = sc.next();
			dataDeletada = LocalDate.parse(dataInformada);			

			System.out.println("Insira o horario do agendamento que deseja cancelar?");
			horarioInformado = sc.next();
			horarioDeletado = LocalTime.parse(horarioInformado);			

			for (int i = 0; i < agenda.size(); i++) {
				if (agenda.get(i).getHorario().equals(horarioDeletado)
						&& agenda.get(i).getData().equals(dataDeletada)) {
					Registra.deletaAgendamento(i);
					continuar = false;
					System.out.println("Agendamento cancelado!");
					return;
				} else if (continuar == true && i == agenda.size() - 1) {
					System.out.println("Horário não encontrado!");
					System.out.println("Deseja voltar ao menu?(S ou N):");
					desejaSair = sc.next();
					if (desejaSair.equalsIgnoreCase("s")) {
						return;
					}
				}
			}
		} while (true);
	}
}
