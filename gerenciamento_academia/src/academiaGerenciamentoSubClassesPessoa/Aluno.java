package academiaGerenciamentoSubClassesPessoa;

import java.time.LocalDate;

import academiaGerenciamentoParalelaClasse.Plano;

public class Aluno {
	private LocalDate dataMatricula;
	private String avaliacoesFisicas;
	private Plano plano;

	// Construtor
	public Aluno(LocalDate dataMatricula, String avaliacoesFisicas, Plano plano) {
		super();
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

}
