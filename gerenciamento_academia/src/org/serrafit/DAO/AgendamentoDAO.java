package org.serrafit.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class AgendamentoDAO {
	PreparedStatement script = null;
	
	
	public void cadastro (LocalTime horario, int idAluno, int idPlano, LocalDate data) {
		String queryAvalicao = "INSERT INTO Agendamento (horario, idaluno, idplano, data) "
							+ " values (?, ?, ?, ?)";
		try {
			script = ConexaoDB.criarConexao().prepareStatement(queryAvalicao);
			
			script.setTime(1, Time.valueOf(horario));
			script.setInt(2, idAluno);
			script.setInt(3, idPlano);
			script.setDate(4, Date.valueOf(data));
			
			script.executeUpdate();
			
		} catch (SQLException e){
			System.out.println("Erro: " + e);
		}
	}

	public ResultSet consulta(LocalTime horario) {
		String query = "Select * from Agendamento";
		ResultSet resultadoConsulta = null;
		try {
			script = ConexaoDB.criarConexao().prepareStatement(query);
			
			resultadoConsulta = script.executeQuery();
			
			return resultadoConsulta;
		} catch (SQLException e) {
			System.out.println("Erro: " + e);
			return null;
		}
	}
}
