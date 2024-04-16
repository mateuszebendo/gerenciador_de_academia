package org.serrafit.classes;

import java.time.LocalDate;

public class Avaliacao {
	private Aluno aluno;
	private PersonalTrainer personal;
	private String descricao;
	private LocalDate data;

	// Construtor
	public Avaliacao(Aluno aluno, PersonalTrainer personal, String descricao, LocalDate data) {		
		this.aluno = aluno;
		this.personal = personal;
		this.descricao = descricao;
		this.data = data;
	}
	
	//Get & Set
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	//Método
	public String toString() {
		return String.format("""
				Aluno: %s
				Personal Trainer: %s
				Descrição: %s
				Data da Avaliação: %s
				""", aluno, personal, descricao, data);
	}

}
