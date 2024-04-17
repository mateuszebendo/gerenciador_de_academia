package org.serrafit.registro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.serrafit.classes.Agendamento;
import org.serrafit.classes.Aluno;
import org.serrafit.classes.PersonalTrainer;

public class RegistroAgendamento {
	public static List<Agendamento> listaAgendamentos = new ArrayList<>();

    	public static List<Agendamento> criarListaAgendamentos() {
	        File arquivo = new File("C:\\Users\\Public\\Documents\\agendamentos.txt");
	        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
	            String linha;
	            while ((linha = leitor.readLine()) != null) {
	                String[] cortado = linha.split(";");
	                if (cortado.length >= 4) {
	                    LocalTime nome = LocalTime.parse(cortado[0]);
	                    String nomeAluno = cortado[1];
	                    String nomePersonal = cortado[2];
	                    LocalDate data = LocalDate.parse(cortado[3]);
	                    
	                    List<Aluno> listaAlunos = RegistroAluno.criarListaAlunos();
	                    Aluno aluno = RegistroAluno.criarObjetoAluno(nomeAluno, listaAlunos);
	                    
	                    List<PersonalTrainer> listaPersonal = RegistroPersonal.criarListaPersonal();
	                    PersonalTrainer personal = RegistroPersonal.criarObjetoPersonal(nomePersonal, listaPersonal);
	                    
	                    Agendamento agendamento = new Agendamento(nome, aluno, personal, data);
	                    listaAgendamentos.add(agendamento);
	                } else {
	                    System.out.println("Linha inválida no arquivo: " + linha);
	                }
	            }
	        } catch (IOException e) {
	            System.out.println("Erro ao ler o arquivo: " + e);
	            return null;
	        } return listaAgendamentos;
    	}
    	
    	public static Agendamento criarObjetoAgendamento(LocalTime horario, List<Agendamento> listaAgendamento) throws IOException {
	        File arquivo = new File("C:\\Users\\Public\\Documents\\agendamentos.txt");
	        @SuppressWarnings("resource")
			BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
	        String linha = leitor.readLine();
	        try {
		        	for(Agendamento agendamentos: listaAgendamento) {
			        	if(agendamentos.getHorario() == horario)	{
			        	String[] cortado = linha.split(";");
		                if (cortado.length >= 4) {
		                	LocalTime nome = LocalTime.parse(cortado[0]);
		                    String nomeAluno = cortado[1];
		                    String nomePersonal = cortado[2];
		                    LocalDate data = LocalDate.parse(cortado[3]);
		                    
		                    List<Aluno> listaAlunos = RegistroAluno.criarListaAlunos();
		                    Aluno aluno = RegistroAluno.criarObjetoAluno(nomeAluno, listaAlunos);
		                    
		                    List<PersonalTrainer> listaPersonal = RegistroPersonal.criarListaPersonal();
		                    PersonalTrainer personal = RegistroPersonal.criarObjetoPersonal(nomePersonal, listaPersonal);
		                    
		                    Agendamento agendamentoEncontrado = new Agendamento(nome, aluno, personal, data);
		                    return agendamentoEncontrado;
		                }
	                  }
	                }
	        	}catch (Exception e) {
		            System.out.println("Erro ao ler o arquivo: " + e);
		        }
	            return null;
	    }
        
}
