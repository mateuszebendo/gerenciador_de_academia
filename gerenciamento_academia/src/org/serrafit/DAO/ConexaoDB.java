package org.serrafit.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexaoDB {
	static String url = "jdbc:postgresql://aws-0-sa-east-1.pooler.supabase.com:5432/postgres?user=postgres.ximiuubjkqrxjuxmjnrh&password=c4iX4zanot1!";
	static Connection conexao = null;
	
	public static Connection criarConexao () {
		try {
			Class.forName("org.postgresql.Driver");
			return conexao = DriverManager.getConnection(url);
		} catch (Exception e) {
			System.out.println("Erro: " + e);
			return null;
		}
	}
	
	public static void fecharConexao () {
		try {
			if(conexao != null) conexao.close();
		} catch (SQLException e) {
			System.out.println("Erro: " + e);
		}
	}
}
