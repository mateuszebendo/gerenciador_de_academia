package org.serrafit.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.serrafit.classes.Avaliacao;

public class AvaliacaoDAO {
	static PreparedStatement script = null;
	static ResultSet resultado = null;
		
	 public static void cadastro (Avaliacao avaliacao) {
			String queryAvalicao = "INSERT INTO Avaliacao (idaluno, idpersonal, status, dataavaliacao) "
								+ " values (?, ?, ?, ?)";
			try {
				int idAluno = 0; 
				int idPersonal = 0; 
				
				resultado = AlunoDAO.consulta(avaliacao.getAluno().getCpf());
				if(resultado.next()) idAluno = resultado.getInt("idaluno");
				
				resultado = PersonalTrainerDAO.consultaHorario(avaliacao.getPersonal().getInicioAtendimento());
				if(resultado.next()) idPersonal = resultado.getInt("idpersonal");
				
				script = ConexaoDB.criarConexao().prepareStatement(queryAvalicao);
				
				
				script.setInt(1, idAluno);
				script.setInt(2, idPersonal);
				script.setString(3, avaliacao.getStatus());
				script.setDate(4, Date.valueOf(avaliacao.getData()));
				
				script.executeUpdate();
				
			} catch (SQLException e){
				System.out.println("Erro: " + e);
			}
		}
	
	 public static ResultSet consulta(LocalDate dataAvaliacao) {
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
