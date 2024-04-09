package academia.gerenciamento.subclassesPessoa;

import java.time.LocalDate;

public class PersonalTrainer {
	private LocalDate horarioAtendimento;
	private String cref;
	private String especialidade;

	// Construtor
	public PersonalTrainer(LocalDate horarioAtendimento, String cref, String especialidade) {
		super();
		this.horarioAtendimento = horarioAtendimento;
		this.cref = cref;
		this.especialidade = especialidade;
	}

	// Get & Set
	public LocalDate getHorarioAtendimento() {
		return horarioAtendimento;
	}

	public void setHorarioAtendimento(LocalDate horarioAtendimento) {
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

	// Método
	public String toString() {
		return String.format("""
				Horario Atendimento: %s
				CREF: %s
				Especialidade: %s
				""", horarioAtendimento, cref, especialidade);
	}

}
