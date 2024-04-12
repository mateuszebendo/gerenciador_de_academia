package org.serrafit.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class AlunoDAO{
	PreparedStatement script = null;
	
	
	public void cadastro (String nome, String cpf, String senha, String contato, Date dataNascimento, String tipo, 
							Time dataMatricula, String avalicoesFisicas, int planoContratado) {
		String queryPessoa = "INSERT INTO Pessoa (nome, cpf, senha, contato, dataNascimento, tipo) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";		
		String queryAluno = "INSERT INTO Aluno (idaluno, datamatricula, avalicoesfisicas, planocontratado) "
							+ " values (?, ?, ?, ?)";
		try {
			script = ConexaoDB.criarConexao().prepareStatement(queryPessoa);
			
			script.setString(1, nome);
			script.setString(2, cpf);
			script.setString(3, senha);
			script.setString(4, contato);
			script.setDate(5, dataNascimento);
			script.setString(6, tipo);
			
			script.executeUpdate();
			
			script = ConexaoDB.criarConexao().prepareStatement("SELECT idPessoa FROM Pessoa where cpf = ?");
			
			script.setString(1, cpf);
			
			ResultSet lugarIdAluno = script.executeQuery();
			
			int idAluno = -1;
			
			while(lugarIdAluno.next()) {
				idAluno = lugarIdAluno.getInt("idAluno");
			}
			
			script = ConexaoDB.criarConexao().prepareStatement(queryAluno);
			
			script.setInt(1, idAluno);
			script.setTime(2, dataMatricula);
			script.setString(3, avalicoesFisicas);
			script.setInt(4, planoContratado);
			
		} catch (SQLException e){
			System.out.println("Erro: " + e);
		}
	}

	public ResultSet consulta(String cpf) {
		String query = "Select * from Pessoa join Aluno on  Pessoa.cpf = ?";
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
	
	/*public static void main(String[] args) {
		AlunoDAO aluno = new AlunoDAO();
		
		ResultSet teste = aluno.consulta("Heitor");
		try {
		while(teste.next()) {
			System.out.println(teste.getString("nome"));
			System.out.println(teste.getString("idaluno"));
		}
		} catch (SQLException e) {
			System.out.println("Erro: " + e);
		}
	}**/

}