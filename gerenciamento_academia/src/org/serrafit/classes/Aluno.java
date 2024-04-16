package org.serrafit.classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Aluno extends Pessoa {
	private LocalDate dataMatricula;
	private List<Avaliacao> avaliacoesFisicas;
	private Plano plano;

	public Aluno(String nome, String cpf, LocalDate dataNascimento, String contato, String senha,
			LocalDate dataMatricula, Plano plano) {
		super(nome, cpf, dataNascimento, contato, senha);
		this.dataMatricula = dataMatricula;
		this.plano = plano;
	}

	public List<Avaliacao> getAvaliacoesFisicas() {
		return avaliacoesFisicas;
	}

	public void setAvaliacoesFisicas(List<Avaliacao> avaliacoesFisicas) {
		this.avaliacoesFisicas = avaliacoesFisicas;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public LocalDate getDataMatricula() {
		return dataMatricula;
	}

	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return super.toString() + String.format("""
				Data Matricula: %s
				Avaliação Física: %s
				Plano: %s
				""", dataMatricula.format(dtf), avaliacoesFisicas, plano);
	}

}
