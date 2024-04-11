package org.serrafit.classes;

import java.time.LocalDate;
import java.time.LocalTime;

public class Agendamento {
	private LocalTime horario;
	private Aluno aluno;
	private PersonalTrainer personal;
	private LocalDate data;

	// Construtor
	public Agendamento(LocalTime horario, Aluno aluno, PersonalTrainer personal, LocalDate data) {
		super();
		this.horario = horario;
		this.aluno = aluno;
		this.personal = personal;
		this.data = data;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public PersonalTrainer getPersonal() {
		return personal;
	}

	public void setPersonal(PersonalTrainer personal) {
		this.personal = personal;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String toString() {
		return String.format("""
				Horario Agendado: %s
				Aluno: %s
				Personal trainer: %s
				Data Inicio: %s
				""", horario, aluno, personal, data);
	}

}
