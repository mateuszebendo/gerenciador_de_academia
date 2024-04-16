package org.serrafit.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.serrafit.classes.Funcionario;
import org.serrafit.conexao.ConexaoDB;

public class FuncionarioDAO {
	PreparedStatement script = null; 
	ResultSet rs = null;
	int idpessoa = 0;
	
	public void cadastro(Funcionario funcionario) {
		String queryPessoa = "INSERT INTO Pessoa (nome, cpf, senha, contato, dataNascimento, tipo) "
				+ "VALUES (?, ?, ?, ?, ?, 'Funcionario') returning idpessoa";
		
		try {
			script = ConexaoDB.criarConexao().prepareStatement(queryPessoa);
			
			script.setString(1, funcionario.getNome());
			script.setString(2, funcionario.getCpf());
			script.setString(3, funcionario.getSenha());
			script.setString(4, funcionario.getContato());
			script.setDate(5, Date.valueOf(funcionario.getdNasc()));
			
			rs = script.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e);
		}
		
		String queryFuncionario = "INSERT INTO Funcionario (idfuncionario, cargo) VALUES (?, ?)";
		
		try {
			while(rs.next()) {
				idpessoa = rs.getInt("idpessoa");
			}
			script = ConexaoDB.criarConexao().prepareStatement(queryFuncionario);
			
			script.setInt(1, idpessoa);
			script.setString(2, funcionario.getCargo());
			
			script.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e);
		}
	}
	
	public ResultSet consulta(String cpf) {
		String query = "Select * from Pessoa join Funcionario on Pessoa.cpf = ?";
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