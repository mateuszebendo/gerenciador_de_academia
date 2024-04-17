package org.serrafit.registro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.serrafit.classes.Aluno;
import org.serrafit.classes.Funcionario;
import org.serrafit.classes.PersonalTrainer;
import org.serrafit.classes.Pessoa;
import org.serrafit.classes.Plano;

public class RegistroCadastro {
	static List <Pessoa> cadastro = new ArrayList<>();
	
	public static List <Pessoa> criaCadastro (){
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
                    
                    Pessoa aluno = new Aluno(nome, cpf, dataNascimento, telefone, senha, dataMatricula, plano);
                    cadastro.add(aluno);
                } else {
                    System.out.println("Linha inválida no arquivo: " + linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e);
        }
        
        arquivo = new File("C:\\Users\\Public\\Documents\\funcionarios.txt");
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

                    Pessoa funcionario = new Funcionario(nome, cpf, dataNascimento, telefone, senha, cargo);
                    cadastro.add(funcionario);
                } else {
                    System.out.println("Linha inválida no arquivo: " + linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e);
        }
        
        arquivo = new File("C:\\Users\\Public\\Documents\\personal.txt");
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
                    
                    Pessoa personal = new PersonalTrainer(nome, cpf, dataNascimento, telefone, senha,
                            inicioAtendimento, fimAtendimento, cref, especialidade);
                    cadastro.add(personal);
                } else {
                    System.out.println("Linha inválida no arquivo: " + linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e);
        }
        
        return cadastro;
	}
}
