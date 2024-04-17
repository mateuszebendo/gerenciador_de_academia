package org.serrafit.registro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.serrafit.classes.Agendamento;
import org.serrafit.classes.Aluno;
import org.serrafit.classes.Avaliacao;
import org.serrafit.classes.PersonalTrainer;

public class RegistroAvaliacao {
	public static List<Avaliacao> listaAvaliacao = new ArrayList<>();

	public static List<Avaliacao> criarListaAvaliacoes() {
        File arquivo = new File("C:\\Users\\Public\\Documents\\avaliacao.txt");
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] cortado = linha.split(";");
                if (cortado.length >= 4) {
                    String nomeAluno = cortado[0];
                    String nomePersonal = cortado[1];
                    String descricao = cortado[2];
                    LocalDate data = LocalDate.parse(cortado[3]);
                    
                    List<Aluno> listaAlunos = RegistroAluno.criarListaAlunos();
                    Aluno aluno = RegistroAluno.criarObjetoAluno(nomeAluno, listaAlunos);
                    
                    List<PersonalTrainer> listaPersonal = RegistroPersonal.criarListaPersonal();
                    PersonalTrainer personal = RegistroPersonal.criarObjetoPersonal(nomePersonal, listaPersonal);
                    
                    Avaliacao avaliacao = new Avaliacao(aluno, personal, descricao, data);
                    listaAvaliacao.add(avaliacao);
                } else {
                    System.out.println("Linha inválida no arquivo: " + linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e);
            return null;
        } return listaAvaliacao;
	}
	
	public static Avaliacao criarObjetoAvaliacao(LocalDate data, List<Agendamento> listaAgendamento) throws IOException {
        File arquivo = new File("C:\\Users\\Public\\Documents\\avaliacao.txt");
        @SuppressWarnings("resource")
		BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
        String linha = leitor.readLine();
        try {
	        	for(Agendamento agendamentos: listaAgendamento) {
		        	if(agendamentos.getData() == data)	{
		        	String[] cortado = linha.split(";");
	                if (cortado.length >= 4) {
	                	String nomeAluno = cortado[0];
	                    String nomePersonal = cortado[1];
	                    String descricao = cortado[2];
	                    LocalDate data1 = LocalDate.parse(cortado[3]);
	                    
	                    List<Aluno> listaAlunos = RegistroAluno.criarListaAlunos();
	                    Aluno aluno = RegistroAluno.criarObjetoAluno(nomeAluno, listaAlunos);
	                    
	                    List<PersonalTrainer> listaPersonal = RegistroPersonal.criarListaPersonal();
	                    PersonalTrainer personal = RegistroPersonal.criarObjetoPersonal(nomePersonal, listaPersonal);
	                    
	                    Avaliacao avaliacaoEncontrada = new Avaliacao(aluno, personal, descricao, data1);
	                    return avaliacaoEncontrada;
	                }
                  }
                }
        	}catch (Exception e) {
	            System.out.println("Erro ao ler o arquivo: " + e);
	        }
            return null;
    }
}
