package org.serrafit.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

import org.serrafit.classes.PersonalTrainer;
import org.serrafit.conexao.ConexaoDB;

public class PersonalTrainerDAO {
	static PreparedStatement script = null; 
	static ResultSet resultado = null;
		
		public static void cadastro(PersonalTrainer personal) {
			String queryPessoa = "INSERT INTO Pessoa (nome, cpf, senha, contato, dataNascimento, tipo) "
					+ "VALUES (?, ?, ?, ?, ?, 'Personal') returning idPessoa";
			
			try {
				script = ConexaoDB.criarConexao().prepareStatement(queryPessoa);
				
				script.setString(1, personal.getNome());
				script.setString(2, personal.getCpf());
				script.setString(3, personal.getSenha());
				script.setString(4, personal.getContato());
				script.setDate(5, Date.valueOf(personal.getdNasc()));
				
				resultado = script.executeQuery();
				
			} catch (SQLException e) {
				System.out.println("Erro: " + e);
			}
			
			String queryPersonal = "INSERT INTO PersonalTrainer "
					+ "(idpersonal, cref, especialidade, inicio, fim) "
					+ "VALUES (?, ?, ?, ?, ?)";
			
			try {
				int idPersonal = 0;
				if(resultado.next()) {
					idPersonal = resultado.getInt("idpessoa");
				}
				script = ConexaoDB.criarConexao().prepareStatement(queryPersonal);
				
				script.setInt(1, idPersonal);
				script.setString(2, personal.getCref());
				script.setString(3, personal.getEspecialidade());
				script.setTime(4, Time.valueOf(personal.getInicioAtendimento()));
				script.setTime(5, Time.valueOf(personal.getFimAtendimento()));
				
				script.executeUpdate();
				script.close();
			} catch (SQLException e) {
				System.out.println("Erro: " + e);
			}
		}
		
		public static ResultSet consultaHorario(LocalTime horarioAtendimento) {
			
			String query = "Select * from Pessoa JOIN PersonalTrainer on"
					+ " Pessoa.idpessoa = PersonalTrainer.idpersonal "
					+ "where PersonalTrainer.inicio <= ? and PersonalTrainer.fim >= ?";
			ResultSet resultadoConsulta = null;
			try {
				script = ConexaoDB.criarConexao().prepareStatement(query);
				
				script.setTime(1, Time.valueOf(horarioAtendimento));
				script.setTime(2, Time.valueOf(horarioAtendimento));
				
				resultadoConsulta = script.executeQuery();
				
				return resultadoConsulta;
			} catch (SQLException e) {
				System.out.println("Erro: " + e);
				return null;
			}
		}
		
		public static ResultSet consultaCref(String cref) {
			String query = "Select * from PersonalTrainer where cref = ?"; 
			try {
				script = ConexaoDB.criarConexao().prepareStatement(query);
				
				script.setString(1, cref);
				
				resultado = script.executeQuery();
				
				return resultado;
				} catch (SQLException e) {
				System.out.println("Erro: " + e);
				return null;
			}
		}
}
