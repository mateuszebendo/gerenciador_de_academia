package org.serrafir.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ConexaoDB {
	String url = "jdbc:postgresql://aws-0-sa-east-1.pooler.supabase.com:5432/postgres?user=postgres.ximiuubjkqrxjuxmjnrh&password=c4iX4zanot1!";
	Connection conexao = null;
	
	public void criarConexao () {
		try {
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection(url);
			if(conexao != null) {
				System.out.println("FOI KHEZAC FOI!");
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}
	}
	
	public void inserirDados () {
		String sql = "insert into pessoa (nome, cpf, senha) values (?, ?, ?)";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setString(1, "Khezac");
			ps.setString(2, "Heitor");
			ps.setString(3, "Bianco");
			
			ps.executeUpdate();
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
		ConexaoDB conexao = new ConexaoDB();
		
		conexao.criarConexao();
		conexao.inserirDados();
	}
}
