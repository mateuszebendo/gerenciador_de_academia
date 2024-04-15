package org.serrafit.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;

import org.serrafit.classes.Agendamento;

public class AgendamentoDAO {
	static PreparedStatement script = null;
	static ResultSet resultado = null;
	
	public static void cadastro (Agendamento agendamento) {
		String queryAvalicao = "INSERT INTO Agendamento (horario, idaluno, idpersonal, data) "
							+ " values (?, ?, ?, ?)";
		try {
			int idAluno = 0;
			int idPersonal = 0;
			resultado = AlunoDAO.consulta(agendamento.getAluno().getCpf());
			if(resultado.next())idAluno = resultado.getInt("idAluno");
			resultado = PersonalTrainerDAO.consultaHorario(agendamento.getPersonal().getInicioAtendimento());
			if(resultado.next()) idPersonal = resultado.getInt("idpersonal");
			
			script = ConexaoDB.criarConexao().prepareStatement(queryAvalicao);
			
			script.setTime(1, Time.valueOf(agendamento.getHorario()));
			script.setInt(2, idAluno);
			script.setInt(3, idPersonal);
			script.setDate(4, Date.valueOf(agendamento.getData()));
			
			script.executeUpdate();
			
		} catch (SQLException e){
			System.out.println("Erro: " + e);
		}
	}

	public static ResultSet consulta(LocalDate data) {
		String query = "Select * from Agendamento";

		try {
			script = ConexaoDB.criarConexao().prepareStatement(query);
			
			script.setDate(1, Date.valueOf(data));
			
			resultado = script.executeQuery();
			
			return resultado;
		} catch (SQLException e) {
			System.out.println("Erro: " + e);
			return null;
		}
	}
}
