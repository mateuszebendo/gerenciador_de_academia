package org.serrafit.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlunoDAO{
	PreparedStatement script = null;
	
	
	public void cadastro (String nome, String cpf, String senha, String contato, Date dataNascimento) {
		String query = "INSERT INTO Pessoa VALUES ( ?, ?, ?, ?, ?)";
		try {
			script = ConexaoDB.criarConexao().prepareStatement(query);
			
			script.setString(1, nome);
			script.setString(2, cpf);
			script.setString(3, senha);
			script.setString(4, contato);
			script.setDate(5, dataNascimento);
			
			script.executeUpdate();
		} catch (SQLException e){
			System.out.println("Erro: " + e);
		}
	}

	public void consulta() {
		
	}

}