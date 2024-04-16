package org.serrafit.classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class PersonalTrainer extends Pessoa {
	private LocalTime inicioAtendimento;
	private LocalTime fimAtendimento;
	private String cref;
	private String especialidade;

	Scanner sc = new Scanner(System.in);

	public PersonalTrainer(String nome, String cpf, LocalDate dataNascimento, String contato, String senha,
			LocalTime inicioAtendimento, LocalTime fimAtendimento, String cref, String especialidade) {
		super(nome, cpf, dataNascimento, contato, senha);
		this.inicioAtendimento = inicioAtendimento;
		this.fimAtendimento = fimAtendimento;
		this.cref = cref;
		this.especialidade = especialidade;
	}

	public LocalTime getInicioAtendimento() {
		return inicioAtendimento;
	}

	public void setInicioAtendimento(LocalTime inicioAtendimento) {
		this.inicioAtendimento = inicioAtendimento;
	}

	public LocalTime getFimAtendimento() {
		return fimAtendimento;
	}

	public void setFimAtendimento(LocalTime fimAtendimento) {
		this.fimAtendimento = fimAtendimento;
	}

	public String getCref() {
		return cref;
	}

	public void setCref(String cref) {
		this.cref = cref;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String toString() {
		return String.format("""
				Nome do Personal: %s
				Início do Atendimento: %s
				Fim do Atendimento: %s
				CREF: %s
				Especialidade: %s
				""", this.getNome(), inicioAtendimento, fimAtendimento, cref, especialidade);
	}
}
