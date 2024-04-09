package academiaGerenciamentoClassePrincipal;

import java.time.LocalDate;

public abstract class Pessoa implements InterfaceMenu{
	private String nome;
	private String cpf;
	private LocalDate dNasc;
	private String contato;
	private String senha;

	// Construtor
	public Pessoa(String nome, String cpf, LocalDate dNasc, String contato, String senha) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.dNasc = dNasc;
		this.contato = contato;
		this.senha = senha;
	}

	// Get & Set
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getdNasc() {
		return dNasc;
	}

	public void setdNasc(LocalDate dNasc) {
		this.dNasc = dNasc;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	// Métodos
	public String toString() {
		return String.format("""
				Nome: %s
				CPF: %s
				D.Nascimento: %s
				Contato: %s
				Senha: %s
				""", nome, cpf, dNasc, contato, senha);
	}

}
