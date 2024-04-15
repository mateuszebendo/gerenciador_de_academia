package org.serrafit.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.serrafit.classes.Aluno;

public class AlunoDAO{
	static PreparedStatement script = null;
	static ResultSet resultado = null;
	
	static public void cadastro (Aluno aluno) {
		String queryPessoa = "INSERT INTO Pessoa (nome, cpf, senha, contato, dataNascimento, tipo) "
				+ "VALUES (?, ?, ?, ?, ?, 'Aluno') returning idPessoa";		
		try {
			script = ConexaoDB.criarConexao().prepareStatement(queryPessoa);
			
			script.setString(1, aluno.getNome());
			script.setString(2, aluno.getCpf());
			script.setString(3, aluno.getSenha());
			script.setString(4, aluno.getContato());
			script.setDate(5, Date.valueOf(aluno.getdNasc()));
			
			resultado = script.executeQuery();
			
		} catch (SQLException e){
			System.out.println("Erro: " + e);
		}
		
		String queryAluno = "INSERT INTO Aluno (idaluno, datamatricula, planocontratado) "
				+ " values (?, ?, ?, ?)";
		
		try {
			int idAluno = 0;
			while(resultado.next()) {
				idAluno = resultado.getInt("idaluno");
			}
			
			script = ConexaoDB.criarConexao().prepareStatement(queryAluno);
			
			resultado = PlanoDAO.consulta(aluno.getPlano().getNome());
			
			int idPlano = resultado.getInt("idplano");
			
			script.setInt(1, idAluno);
			script.setDate(2, Date.valueOf(aluno.getDataMatricula()));
			script.setInt(3, idPlano);
			
			script.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e);
		}
			
}

	static public ResultSet consulta(String cpf) {
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

}