package org.serrafit.registro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.serrafit.classes.Aluno;
import org.serrafit.classes.Plano;

public class RegistroAluno {
	 public static List<Aluno> listaAlunos = new ArrayList<>();

	    public static List<Aluno> criarListaAlunos() {
	        File arquivo = new File("C:\\Users\\Public\\Documents\\alunos.txt");
	        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
	            String linha;
	            while ((linha = leitor.readLine()) != null) {
	                String[] cortado = linha.split(";");
	                if (cortado.length >= 7) {
	                    String nome = cortado[0];
	                    String cpf = cortado[1];
	                    String senha = cortado[2];
	                    String telefone = cortado[3];
	                    LocalDate dataNascimento = LocalDate.parse(cortado[4]);
	                    LocalDate dataMatricula = LocalDate.parse(cortado[5]);
	                    String nomePlano = cortado[6];
	                    
	                    Plano plano = RegistroPlano.criarObjetoPlano(nomePlano);
	                    
	                    Aluno aluno = new Aluno(nome, cpf, dataNascimento, telefone, senha, dataMatricula, plano);
	                    listaAlunos.add(aluno);
	                } else {
	                    System.out.println("Linha inválida no arquivo: " + linha);
	                }
	            }
	        } catch (IOException e) {
	            System.out.println("Erro ao ler o arquivo: " + e);
	        }
	        return listaAlunos;
	    }
	    
	    public static Aluno criarObjetoAluno(String nomeProcurado, List<Aluno> listaAluno) throws IOException {
	        File arquivo = new File("C:\\Users\\Public\\Documents\\alunos.txt");
	        @SuppressWarnings("resource")
			BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
	        String linha = leitor.readLine();
	        try {
		        	for(Aluno aluno: listaAluno) {
			        	if(aluno.getNome().equalsIgnoreCase(nomeProcurado))	{
			        	String[] cortado = linha.split(";");
		                if (cortado.length >= 7) {
		                	String nome = cortado[0];
		                    String cpf = cortado[1];
		                    String senha = cortado[2];
		                    String telefone = cortado[3];
		                    LocalDate dataNascimento = LocalDate.parse(cortado[4]);
		                    LocalDate dataMatricula = LocalDate.parse(cortado[5]);
		                    String nomePlano = cortado[6];
		                    
		                    Plano plano = RegistroPlano.criarObjetoPlano(nomePlano);
		                    
		                    Aluno alunoEncontrado = new Aluno(nome, cpf, dataNascimento, telefone, senha, dataMatricula, plano);
		                    return alunoEncontrado;
		                }
	                  }
	                }
	        	}catch (Exception e) {
		            System.out.println("Erro ao ler o arquivo: " + e);
		        }
	            return null;
	    }
	    
//	    public static void main(String[] args) throws IOException {
//	    	listaAlunos = criarListaAlunos();
//			Aluno aluno = criarObjetoAluno("Maria Oliveira", listaAlunos);
//			
//			System.out.println(listaAlunos);
//			System.out.println(aluno);
//		}
}
