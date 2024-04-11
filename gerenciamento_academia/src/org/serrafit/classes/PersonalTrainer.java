package org.serrafit.classes;

import java.time.LocalDate;
import java.time.LocalTime;

public class PersonalTrainer extends Pessoa {
	private LocalTime horarioAtendimento;
	private String cref;
	private String especialidade;

	// Construtor
	public PersonalTrainer(String nome, String cpf, LocalDate dNasc, String contato, String senha,
			LocalTime horarioAtendimento, String cref, String especialidade) {
		super(nome, cpf, dNasc, contato, senha);
		this.horarioAtendimento = horarioAtendimento;
		this.cref = cref;
		this.especialidade = especialidade;
	}

	// Get & Set
	public LocalTime getHorarioAtendimento() {
		return horarioAtendimento;
	}

	public void setHorarioAtendimento(LocalTime horarioAtendimento) {
		this.horarioAtendimento = horarioAtendimento;
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

	public void exibirMenu() {
		var mensagem = String.format("""
				============================================
				         BEM VINDO - %S
				============================================

				1 - Visualizar agenda de atendimentos
				2 - Registrar avaliações físicas dos alunos
				3 - Visualizar lista de avaliações
				0 - Sair
				""", getNome());
		System.out.println(mensagem);
	}

	public void visualizarAgenda() {

	}

	public void registrarAvaliacao() {

	}

	public void visualizarListaAvaliacoes() {

	}

	public void selecionarOpcao() {

	}

	// Método
	public String toString() {
		return String.format("""
				Horario Atendimento: %s
				CREF: %s
				Especialidade: %s
				""", horarioAtendimento, cref, especialidade);
	}

}
