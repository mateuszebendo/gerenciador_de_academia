package org.serrafit.classes;

public class Plano {
	private String nome;
	private double valor;
	private String descricao;
	private int duracao;

	// Construtor
	public Plano(String nome, double valor, String descricao, int duracao) {
		this.nome = nome;
		this.valor = valor; 
		this.descricao = descricao; 
		this.duracao = duracao;
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

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	// Método
	public String toString() {
		return String.format("""
				%s
				Valor: R$%.2f
				Descrição: %s
				Duração do Plano: %d meses
				""", nome, valor, descricao, duracao); 
	}
}
