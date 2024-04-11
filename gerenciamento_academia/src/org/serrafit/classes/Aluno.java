package org.serrafit.classes;

import java.time.LocalDate;

public class Aluno extends Pessoa{
	private LocalDate dataMatricula;
	private String avaliacoesFisicas;
	private Plano plano;

	// Construtor
	public Aluno(String nome, String cpf, LocalDate dNasc, String contato, String senha, LocalDate dataMatricula,
			String avaliacoesFisicas, Plano plano) {
		super(nome, cpf, dNasc, contato, senha);
		this.dataMatricula = dataMatricula;
		this.avaliacoesFisicas = avaliacoesFisicas;
		this.plano = plano;
	}

	// Get & Set
	public LocalDate getDataMatricula() {
		return dataMatricula;
	}



	public void setDataMatricula(LocalDate dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public String getAvaliacoesFisicas() {
		return avaliacoesFisicas;
	}

	public void setAvaliacoesFisicas(String avaliacoesFisicas) {
		this.avaliacoesFisicas = avaliacoesFisicas;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public String toString() {
		return String.format("""
				Data Matricula: %s
				Avaliação Física: %s
				Plano: %s
				""", dataMatricula, avaliacoesFisicas, plano);
	}

	@Override
	public void exibirMenu() {
		System.out.println("Menu Aluno");
	}

	@Override
	public void selecionarOpcao() {
		// TODO Auto-generated method stub
		
	}

}
