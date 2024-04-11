package org.serrafit.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	
	public void fecharConexao () {
		try {
			conexao.close();
		} catch (SQLException e) {
			System.out.println("Erro: " + e);
		}
	}
}
