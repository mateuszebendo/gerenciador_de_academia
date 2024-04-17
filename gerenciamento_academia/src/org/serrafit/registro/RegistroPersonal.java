package org.serrafit.registro;
	import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.serrafit.classes.PersonalTrainer;

	public class RegistroPersonal {
	    public static List<PersonalTrainer> listaPersonal = new ArrayList<>();

	    public static List<PersonalTrainer> criarListaPersonal(){
	    	File arquivo = new File("C:\\Users\\Public\\Documents\\personal.txt");
	        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
	            String linha;
	            while ((linha = leitor.readLine()) != null) {
	                String[] cortado = linha.split(";");
	                if (cortado.length >= 9) {
	                    String nome = cortado[0];
	                    String cpf = cortado[1];
	                    String senha = cortado[2];
	                    String telefone = cortado[3];
	                    LocalDate dataNascimento = LocalDate.parse(cortado[4]);
	                    LocalTime inicioAtendimento = LocalTime.parse(cortado[5]);
	                    LocalTime fimAtendimento = LocalTime.parse(cortado[6]);
	                    String cref = cortado[7];
	                    String especialidade = cortado[8];
	                    
	                    PersonalTrainer personal = new PersonalTrainer(nome, cpf, dataNascimento, telefone, senha,
	                            inicioAtendimento, fimAtendimento, cref, especialidade);
	                    listaPersonal.add(personal);
	                } else {
	                    System.out.println("Linha inválida no arquivo: " + linha);
	                }
	            }
	        } catch (IOException e) {
	            System.out.println("Erro ao ler o arquivo: " + e);
	        }
	        return listaPersonal;
	    }

	    public static PersonalTrainer criarObjetoPersonal(String nomeProcurado, List<PersonalTrainer> listaPersonal) throws IOException {
	        File arquivo = new File("C:\\Users\\Public\\Documents\\personal.txt");
	        @SuppressWarnings("resource")
			BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
	        String linha = leitor.readLine();
	        try {
		        	for(PersonalTrainer personal: listaPersonal) {
			        	if(personal.getNome().equalsIgnoreCase(nomeProcurado))	{
			        	String[] cortado = linha.split(";");
		                if (cortado.length >= 9) {
		                    String nome = cortado[0];
		                    String cpf = cortado[1];
		                    String senha = cortado[2];
		                    String telefone = cortado[3];
		                    LocalDate dataNascimento = LocalDate.parse(cortado[4]);
		                    LocalTime inicioAtendimento = LocalTime.parse(cortado[5]);
		                    LocalTime fimAtendimento = LocalTime.parse(cortado[6]);
		                    String cref = cortado[7];
		                    String especialidade = cortado[8];
		                    
		                    PersonalTrainer personalEncotrado = new PersonalTrainer(nome, cpf, dataNascimento, telefone, senha,
		                            inicioAtendimento, fimAtendimento, cref, especialidade);
		                    return personalEncotrado;
		                }
	                 }
	                }
	        	}catch (Exception e) {
    	            System.out.println("Erro ao ler o arquivo: " + e);
    	        }
                return null;
	    }
//
//	    public static void main(String[] args) throws IOException {
//	    	listaPersonal = criarListaPersonal();
//	    	PersonalTrainer personal = criarObjetoPersonal("Carlos Andrade", listaPersonal);
//	        System.out.println(personal);
//	    }
	}

