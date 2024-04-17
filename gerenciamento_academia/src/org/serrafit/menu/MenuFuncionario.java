package org.serrafit.menu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.serrafit.classes.Aluno;
import org.serrafit.classes.Avaliacao;
import org.serrafit.classes.Funcionario;
import org.serrafit.classes.PersonalTrainer;
import org.serrafit.classes.Pessoa;
import org.serrafit.classes.Plano;
import org.serrafit.registro.RegistroAgendamento;
import org.serrafit.registro.RegistroAluno;
import org.serrafit.registro.RegistroAvaliacao;
import org.serrafit.registro.RegistroPersonal;
import org.serrafit.registro.RegistroPlano;
import org.serrafit.service.Registra;
import org.serrafit.service.ValidacaoPersonal;
import org.serrafit.service.ValidacaoPessoa;
import org.serrafit.service.ValidacaoPlano;

public class MenuFuncionario implements Menu {
	Funcionario funcionario = null;
	
	List <Plano> listaPlanos = new ArrayList<>();
	List <Aluno> listaAlunos = new ArrayList<>();
	List <PersonalTrainer> listaPersonal = new ArrayList<>();
	List <Funcionario> listaFuncionario = new ArrayList<>();
	List <Avaliacao> listaAvaliacao = new ArrayList<>();
	
	public MenuFuncionario(Funcionario funcionario) throws IOException {
		super();
		this.funcionario = funcionario;
		this.listaPlanos = Registra.criaPlano();
		this.listaAlunos = Registra.criaAlunos();
		this.listaPersonal = Registra.criaPersonais();
		this.listaAvaliacao = Registra.criaAvaliacao();
	}
	
	@Override
	public void exibirMenu(Scanner sc) throws InterruptedException{
		var mensagem = String.format("""
				============================================
				         BEM VINDO - %S
				============================================
				1 - Cadastrar novo plano
				2 - Cadastrar novo aluno
				3 - Cadastrar novo Personal
				4 - Emitir relatório de planos
				5 - Emitir relatório de alunos
				6 - Emitir relatório de equipe (Funcionarios e Personal Trainers)
				7 - Emitir relação de avaliações por período
				0 - Sair
				""", funcionario.getNome());
		System.out.println(mensagem);
		int opcao = sc.nextInt();
		sc.nextLine();
		do {
		switch(opcao) {
			case 1:
				cadastrarPlano(sc, listaPlanos);				
				break;
			case 2:
				cadastrarAluno(sc, listaAlunos, listaPlanos);
				break;
			case 3:
				cadastrarPersonalTrainer(sc, listaPersonal);
				break;
			case 4:
				emitirRelatorioPlanos(sc, listaPlanos);
				break;
			case 5:
				emitirRelatorioAlunos(sc, listaAlunos);
				break;
			case 6:
				emitirRelatorioEquipe(sc, listaPersonal, listaFuncionario);
				break;
			case 7:
				emitirRelacaoAvaliacaoPorPeriodo(sc, listaAvaliacao);
				break;
			case 0:
				System.out.println("Finalizando programa..");
				sc.close();
				System.exit(0);
				break;
		}
	  }while(opcao != 0);
	}

	public void cadastrarPlano(Scanner sc, List <Plano> listaPlano) throws InterruptedException{
	    System.out.println("\n=== CADASTRAR NOVO PLANO ===");
	    
	    String nomePlano = ValidacaoPlano.validaNomePlano(sc, listaPlano);

	    double valor = ValidacaoPlano.validaPreco(sc);

	    String descricao = ValidacaoPlano.validaDescricao(sc);

	    System.out.println("Duração em meses: ");
	    
	    int duracao = sc.nextInt();
	    sc.nextLine();
	    
	    Plano novoPlano = new Plano(nomePlano, valor, descricao, duracao);
	    
	    listaPlano.add(novoPlano);

	    System.out.println("\n-- CADASTRO - PLANO - CONCLUÍDO --");
	    System.out.println("Cadastro do plano '" + nomePlano + "' concluído com sucesso!");
	    System.out.println(novoPlano);
	   	
	    exibirMenu(sc);
	}

	public void cadastrarAluno(Scanner sc, List <Aluno> listaPessoa, List <Plano> listaPlanos) throws InterruptedException {
	    System.out.println("\n=== CADASTRO DE NOVO ALUNO ===");
	    
	    String nome = ValidacaoPessoa.validaNome(sc);
	    
	    String cpf = ValidacaoPessoa.validaCpf(sc);
	    
	    LocalDate dataNascimento = ValidacaoPessoa.validaDataNascimento(sc);

	    String contato = ValidacaoPessoa.validaContato(sc);

	    String senha = ValidacaoPessoa.validaSenha(sc);

	    LocalDate dataMatricula = LocalDate.now();

	    String nomePlano = ValidacaoPlano.validaPlano(sc, listaPlanos);
	    
	    int planoEscolhido = ValidacaoPlano.posicaoPlano(listaPlanos, nomePlano);

	    Pessoa novoAluno = new Aluno(nome, cpf, dataNascimento, contato, senha, dataMatricula, listaPlanos.get(planoEscolhido));
	    
	    listaPessoa.add((Aluno) novoAluno);

	    System.out.println("\n-- Novo aluno cadastrado com sucesso! --");
	    System.out.println(novoAluno);
	    
	    exibirMenu(sc);
	}

	public void cadastrarPersonalTrainer(Scanner sc, List <PersonalTrainer> listaPersonal) throws InterruptedException {
	    System.out.println("\n=== CADASTRO DE NOVO PERSONAL TRAINER ===");
	    
	    String nome = ValidacaoPessoa.validaNome(sc);

	    String cpf = ValidacaoPessoa.validaCpf(sc);
	    
	    LocalDate dataNascimento = ValidacaoPessoa.validaDataNascimento(sc);

	    String contato = ValidacaoPessoa.validaContato(sc);

	    String senha = ValidacaoPessoa.validaSenha(sc);

	    LocalTime inicioAtendimento = ValidacaoPersonal.validaInicioAtendimento(sc);
	    
	    LocalTime finalAtendimento = ValidacaoPersonal.validaFinalAtendimento(inicioAtendimento, sc);

	    String cref = ValidacaoPersonal.validaCref(sc);

	    
	    System.out.print("Especialidade: ");
	    String especialidade = sc.nextLine();

	    Pessoa personalTrainer = new PersonalTrainer(nome, cpf, dataNascimento, contato, senha, inicioAtendimento, finalAtendimento, cref, especialidade);
	    
	    listaPersonal.add((PersonalTrainer) personalTrainer);

	    System.out.println("\n-- CADASTRO - PERSONAL - CONCLUÍDO --");
	    System.out.println("Cadastro do personal trainer '" + nome + "' concluído com sucesso!");
	    System.out.println(personalTrainer);
	    
	    exibirMenu(sc);
	}
	
	public void emitirRelatorioPlanos(Scanner sc, List<Plano> listaPlano) throws InterruptedException {
		try {
	        File arquivo = new File("C:\\Users\\Public\\Documents\\listaPlanos.txt");
	        FileWriter escritor = new FileWriter(arquivo);
	        if (listaPlano.isEmpty()) {
	            System.out.println("A lista de planos está vazia.");
	        } else {
	            for (Plano plano: listaPlano) {
	                escritor.write(plano + "\n");
	            }
	            System.out.println("Relatório de planos emitido com sucesso.");
	        }
	        
	        escritor.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		System.out.println("Planos cadastrados: ");
	    for (Plano plano : listaPlano) {
			System.out.println(plano);
		}
	    exibirMenu(sc);
	}

	public void emitirRelatorioAlunos(Scanner sc, List<Aluno> listaAlunos) throws InterruptedException {
		try {
	        File arquivo = new File("C:\\Users\\Public\\Documents\\listaAlunos.txt");
	        FileWriter escritor = new FileWriter(arquivo);
	        if (listaAlunos.isEmpty()) {
	            System.out.println("A lista de alunos está vazia.");
	        } else {
	            for (Aluno aluno : listaAlunos) {
	                escritor.write(aluno + "\n");
	            }
	            System.out.println("Relatório de alunos emitido com sucesso.");
	        }
	        
	        escritor.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		System.out.println("Alunos cadastrados: ");
	    for (Aluno aluno : listaAlunos) {
			System.out.println(aluno);
		}
	    exibirMenu(sc);
	}
	
	public void emitirRelatorioEquipe(Scanner sc, List<PersonalTrainer> listaPersonal, List<Funcionario> listaFuncionario) throws InterruptedException {
		try {
	        File arquivo = new File("C:\\Users\\Public\\Documents\\listaEquipe.txt");
	        FileWriter escritor = new FileWriter(arquivo);
	        if (listaAlunos.isEmpty()) {
	            System.out.println("A lista de funcionários está vazia.");
	        } else {
	            for (PersonalTrainer personal : listaPersonal) {
	                escritor.write(personal + "\n");
	            }
	            for (Funcionario funcionario: listaFuncionario) {
	            	escritor.write(funcionario + "\n");
	            }
	            System.out.println("Relatório de "
	            		+ "funcionários emitido com sucesso.");
	        }
	        
	        escritor.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		System.out.println("Funcionarios cadastrados: ");
		for (PersonalTrainer personal : listaPersonal) {
            System.out.println(personal);
        }
		for (Funcionario funcionario: listaFuncionario) {
			System.out.println(funcionario);
        }
		
		exibirMenu(sc);
	}
	
	public void emitirRelacaoAvaliacaoPorPeriodo(Scanner sc, List<Avaliacao> listaAvaliacoes) throws InterruptedException {
		try {
			File arquivo = new File("C:\\Users\\Public\\Documents\\listaAvaliacao.txt");
	        FileWriter escritor = new FileWriter(arquivo);
	        
	        System.out.println("Avaliações de acordo com o mês");
	        for (int mes = 1; mes <= 12; mes++) {
                for (Avaliacao avaliacao : listaAvaliacoes) {
                    if (avaliacao.getData().getMonthValue() == mes) {
                    	System.out.println(avaliacao);
                        escritor.write(avaliacao + "\n");
                    }
                }
            }
			escritor.close();
		} catch (IOException e) {
	        e.printStackTrace();
	    }		
		
		exibirMenu(sc);
	}
	
	public void voltarMenu(Scanner sc) throws InterruptedException {
        String voltar;
        boolean opcaoValida = false;

        do {
            System.out.print("Voltar ao Menu? (S/N)");
            voltar = sc.next().trim().toUpperCase();

            if (voltar.equals("S")) {
                exibirMenu(sc);
                opcaoValida = true;
            } else if (voltar.equals("N")) {
                opcaoValida = true;
            } else {
                System.err.println("Opção inválida.\nPor favor, escolha 'S' para sim ou 'N' para não.");
            }
        } while (!opcaoValida);
    }

}
