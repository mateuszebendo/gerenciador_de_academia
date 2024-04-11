package org.serrafit.classes;

import java.time.LocalDate;

public class Funcionario extends Pessoa{
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
		System.out.println("Menu Funcionario");
	}

	@Override
	public void selecionarOpcao() {
		// TODO Auto-generated method stub
		
	}
	
}
