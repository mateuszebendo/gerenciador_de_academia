package org.serrafit.menu;

import java.util.Scanner;

import org.serrafit.classes.Funcionario;

public class MenuFuncionario implements Menu {
	Funcionario funcionario = null;
	
	
	
	public MenuFuncionario(Funcionario funcionario) {
		super();
		this.funcionario = funcionario;
	}

	@Override
	public void exibirMenu(Scanner sc) {
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

}
