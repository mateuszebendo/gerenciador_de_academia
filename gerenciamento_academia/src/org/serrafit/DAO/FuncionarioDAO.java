package org.serrafit.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class FuncionarioDAO {
	PreparedStatement script = null; 
	ResultSet rs = null;
	int idpessoa = 0;
	
	public void cadastro(String nome, String cpf, String senha, String contato, LocalDate dataNascimento, String cargo) {
		String queryPessoa = "INSERT INTO Pessoa (nome, cpf, senha, contato, dataNascimento, tipo) "
				+ "VALUES (?, ?, ?, ?, ?, 'Funcionario') returning idpessoa";
		
		try {
			script = ConexaoDB.criarConexao().prepareStatement(queryPessoa);
			
			script.setString(1, nome);
			script.setString(2, cpf);
			script.setString(3, senha);
			script.setString(4, contato);
			script.setDate(5, Date.valueOf(dataNascimento));
			
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
			script.setString(2, cargo);
			
			script.executeUpdate();
			script.close();
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
	
	public static void main(String[] args) {
		//FuncionarioDAO funcionario1 = new FuncionarioDAO();
		
		//funcionario1.cadastro("Arnaldo","111.222.333-44","123456","22999991111",LocalDate.of(2003, 4, 2),"Gerente");
	}
}
