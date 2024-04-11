package org.serrafit.classes;

import java.time.LocalDate;

public class Plano {
	private String nome;
	private double valor;
	private String descricao;
	private LocalDate duracao;

	// Construtor
	public Plano(String nome) {
		super();
		this.nome = nome;
	}

	// Get & Set
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDuracao() {
		return duracao;
	}

	public void setDuracao(LocalDate duracao) {
		this.duracao = duracao;
	}

	// Método
	public String toString() {
		return String.format("""
				Tipo de Plano: %s
				Valor: %.2f
				Descrição: %s
				Duração do Plano: %s
				""", nome, valor, descricao, duracao); 
	}
}
