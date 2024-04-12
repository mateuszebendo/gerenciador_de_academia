package org.serrafit.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AvaliacaoDAO {
	PreparedStatement script = null;
		
		
		public void cadastro (int idAluno, int idPersonal, String descricao, LocalDate dataAvaliacao) {
			String queryAvalicao = "INSERT INTO Avaliacao (idaluno, idpersonal, descricao, dataavaliacao) "
								+ " values (?, ?, ?, ?)";
			try {
				script = ConexaoDB.criarConexao().prepareStatement(queryAvalicao);
				
				script.setInt(1, idAluno);
				script.setInt(2, idPersonal);
				script.setString(3, descricao);
				script.setDate(4, Date.valueOf(dataAvaliacao));
				
				script.executeUpdate();
				
			} catch (SQLException e){
				System.out.println("Erro: " + e);
			}
		}
	
		public ResultSet consulta(LocalDate dataAvaliacao) {
			String query = "Select * from Avaliacao where dataavaliacao = ?";
			ResultSet resultadoConsulta = null;
			try {
				script = ConexaoDB.criarConexao().prepareStatement(query);
				
				script.setDate(1, Date.valueOf(dataAvaliacao));
				
				resultadoConsulta = script.executeQuery();
				
				return resultadoConsulta;
			} catch (SQLException e) {
				System.out.println("Erro: " + e);
				return null;
			}
		}
}
