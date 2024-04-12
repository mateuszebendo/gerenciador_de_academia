package org.serrafit.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PersonalTrainerDAO {
	PreparedStatement script = null; 
		
		public void cadastro(String nome, String cpf, String senha, String contato, LocalDate dataNascimento, String tipo,
							String cref, String especialidade ) {
			String queryPessoa = "INSERT INTO Pessoa (nome, cpf, senha, contato, dataNascimento, tipo) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			String queryPersonal = "INSERT INTO Personal (idpersonal, cref, especialidade) VALUES (currval(Pessoa_idpessoa_seq), ?, ?)";
			
			try {
				script = ConexaoDB.criarConexao().prepareStatement(queryPessoa);
				
				script.setString(1, nome);
				script.setString(2, cpf);
				script.setString(3, senha);
				script.setString(4, contato);
				script.setDate(5, Date.valueOf(dataNascimento));
				script.setString(6, tipo);
				
				script.executeUpdate();
				
				script = ConexaoDB.criarConexao().prepareStatement(queryPersonal);
				
				script.setString(1, cref);
				script.setString(2, especialidade);
				
				script.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("Erro: " + e);
			}
		}
		
		public ResultSet consulta(String cpf) {
			
			String query = "Select * from Pessoa join PersonalTrainer on  Pessoa.cpf = ?";
			ResultSet resultadoConsulta = null;
			try {
				script = ConexaoDB.criarConexao().prepareStatement(query);
				
				script.setString(1, cpf);
				
				resultadoConsulta = script.executeQuery();
				
				return resultadoConsulta;
			} catch (SQLException e) {
				System.out.println("Erro: " + e);
				return null;
			}
		}
}
