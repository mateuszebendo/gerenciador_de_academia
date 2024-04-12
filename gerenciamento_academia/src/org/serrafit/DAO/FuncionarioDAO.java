package org.serrafit.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class FuncionarioDAO {
	PreparedStatement script = null; 
	
	public void cadastro(String nome, String cpf, String senha, String contato, LocalDate dataNascimento, String tipo,
						String cargo ) {
		String queryPessoa = "INSERT INTO Pessoa (nome, cpf, senha, contato, dataNascimento, tipo) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		String queryFuncionario = "INSERT INTO Funcionario (idfuncionario, cargo) VALUES (?, ?)";
		
		try {
			script = ConexaoDB.criarConexao().prepareStatement(queryPessoa);
			
			script.setString(1, nome);
			script.setString(2, cpf);
			script.setString(3, senha);
			script.setString(4, contato);
			script.setDate(5, Date.valueOf(dataNascimento));
			script.setString(6, tipo);
			
			script.executeUpdate();
			
			script = ConexaoDB.criarConexao().prepareStatement("Select idPessoa from Pessoa where cpf = ?");
					
			ResultSet lugarIdFuncionario = script.executeQuery();
			
			int idFuncionario = -1;
			
			while(lugarIdFuncionario.next()) {
				idFuncionario = lugarIdFuncionario.getInt("idPessoa");
			}
			
			script = ConexaoDB.criarConexao().prepareStatement(queryFuncionario);
			
			script.setInt(1, idFuncionario);
			script.setString(2, cargo);
			
			script.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro: " + e);
		}
	}
}
