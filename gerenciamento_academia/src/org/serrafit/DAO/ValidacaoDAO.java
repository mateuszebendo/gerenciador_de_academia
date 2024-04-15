package org.serrafit.DAO;

//import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidacaoDAO {
	static PreparedStatement script = null;
  
	public static String conectarUsuario(String cpf, String senha) {
        ResultSet usuarioConectado = null;
        try {
        	String query = "SELECT tipo FROM Pessoa WHERE cpf = ? and senha = ?";
       
        	script = ConexaoDB.criarConexao().prepareStatement(query);
  
        	script.setString(1, cpf);
        	script.setString(2, senha);
        	
        	usuarioConectado = script.executeQuery();
        	
            if (usuarioConectado.next()) {
                String tipoUsuario = usuarioConectado.getString("tipo");
                script.close();
                return tipoUsuario;
            } else {
            	System.out.println("CPF ou Senha não encontrado!");
            	script.close();
                return null;
            }
        } catch (SQLException e) {
        	System.out.println("Erro na Requisição:  " + e);
            return null;
        }
    }
}
