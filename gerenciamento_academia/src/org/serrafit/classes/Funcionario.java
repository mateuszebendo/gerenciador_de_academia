package org.serrafit.classes;

import java.time.LocalDate;

public class Funcionario extends Pessoa {
	private String cargo;

	public Funcionario(String nome, String cpf, LocalDate dNasc, String contato, String senha, String cargo) {
		super(nome, cpf, dNasc, contato, senha);
		this.cargo = cargo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	// Método
	public String toString() {
		return String.format("""
				Cargo: %s
				""", cargo);
	}

	@Override
	public void exibirMenu() {
		var mensagem = String.format("""
				============================================
				         BEM VINDO - %S
				============================================

				1 - Cadastrar novo plano
				2 - Cadastrar novo aluno
				3 - Cadastrar novo Personal
				4 - Emitir relatório de planos
				5 - Emitir relatório de alunos
				6 - Emitir relatório de equipe (Funcionarios e Personal Trainers)
				7 - Emitir relação de avaliaçãoes por período
				0 - Sair
				""", getNome());
		System.out.println(mensagem);
	}

	public void cadastrarPlano() {

	}

	public void cadastrarAluno() {

	}

	public void cadastrarPersonalTrainer() {

	}

	public void emitirRelatorioPlanos() {

	}

	public void emitirRelatorioAlunos() {

	}

	public void emitirRelatorioEquipe() {

	}

	public void emitirRelacaoAvaliacaoPorPeriodo() {

	}

	@Override
	public void selecionarOpcao() {
		// TODO Auto-generated method stub

	}

}
