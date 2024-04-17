package org.serrafit.registro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.serrafit.classes.Funcionario;

public class RegistroFuncionario {
    public static List<Funcionario> listaFuncionarios = new ArrayList<>();

    public static List<Funcionario> criarListaFuncionarios() {
        File arquivo = new File("C:\\Users\\Public\\Documents\\funcionarios.txt");
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] cortado = linha.split(";");
                if (cortado.length >= 6) {
                    String nome = cortado[0];
                    String cpf = cortado[1];
                    String senha = cortado[2];
                    String telefone = cortado[3];
                    LocalDate dataNascimento = LocalDate.parse(cortado[4]);
                    String cargo = cortado[5];

                    Funcionario funcionario = new Funcionario(nome, cpf, dataNascimento, telefone, senha, cargo);
                    listaFuncionarios.add(funcionario);
                } else {
                    System.out.println("Linha inválida no arquivo: " + linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e);
        }
        return listaFuncionarios;
    }
    
    public static Funcionario criarObjetoFuncionario(String nomeProcurado, List<Funcionario> listaFuncionario) throws IOException {
        File arquivo = new File("C:\\Users\\Public\\Documents\\funcionarios.txt");
        @SuppressWarnings("resource")
		BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
        String linha = leitor.readLine();
        try {
	        	for(Funcionario funcionario: listaFuncionario) {
		        	if(funcionario.getNome().equalsIgnoreCase(nomeProcurado))	{
		        	String[] cortado = linha.split(";");
	                if (cortado.length >= 6) {
	                	String nome = cortado[0];
	                    String cpf = cortado[1];
	                    String senha = cortado[2];
	                    String telefone = cortado[3];
	                    LocalDate dataNascimento = LocalDate.parse(cortado[4]);
	                    String cargo = cortado[5];

	                    Funcionario funcionarioEncontrado = new Funcionario(nome, cpf, dataNascimento, telefone, senha, cargo);
	                    return funcionarioEncontrado;
	                }
                  }
                }
        	}catch (Exception e) {
	            System.out.println("Erro ao ler o arquivo: " + e);
	        }
            return null;
    }
    
//    public static void main(String[] args) throws IOException {
//        listaFuncionarios = criarListaFuncionarios();
//        Funcionario funcionario = criarObjetoFuncionario("Carlos Andrade", listaFuncionarios);
//        if (funcionario != null) {
//            System.out.println(funcionario);
//        } else {
//            System.out.println("Funcionário não encontrado.");
//        }
//    }
}
