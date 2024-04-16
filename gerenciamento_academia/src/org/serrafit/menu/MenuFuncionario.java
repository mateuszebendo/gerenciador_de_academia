package org.serrafit.menu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.serrafit.classes.Aluno;
import org.serrafit.classes.Avaliacao;
import org.serrafit.classes.Funcionario;
import org.serrafit.classes.PersonalTrainer;
import org.serrafit.classes.Pessoa;
import org.serrafit.classes.Plano;
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
	
	public MenuFuncionario(Funcionario funcionario) {
		super();
		this.funcionario = funcionario;
		this.listaPlanos = Registra.criaPlano();
		this.listaAlunos = Registra.criaAlunos();
		this.listaPersonal = Registra.criaPersonais();
		this.listaFuncionario = Registra.criaFuncionario();
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
		while(opcao != 0) {
		switch(opcao) {
			case 1:
				cadastrarPlano(sc, listaPlanos);
				voltarMenu(sc);
				break;
			case 2:
				cadastrarAluno(sc, listaAlunos, listaPlanos);
				voltarMenu(sc);
				break;
			case 3:
				cadastrarPersonalTrainer(sc, listaPersonal);
				voltarMenu(sc);
				break;
			case 4:
				emitirRelatorioPlanos(listaPlanos);
				voltarMenu(sc);
				break;
			case 5:
				emitirRelatorioAlunos(listaAlunos);
				voltarMenu(sc);
				break;
			case 6:
				emitirRelatorioEquipe(listaPersonal, listaFuncionario);
				voltarMenu(sc);
				break;
			case 7:
				emitirRelacaoAvaliacaoPorPeriodo(listaAvaliacao);
				voltarMenu(sc);
				break;
			case 0:
				System.out.println("Finalizando programa..");
				sc.close();
				System.exit(0);
				break;
		}
	  }
	}

	public void cadastrarPlano(Scanner sc, List <Plano> listaPlano) throws InterruptedException{
	    System.out.println("\n=== CADASTRAR NOVO PLANO ===");
	    String nomePlano = ValidacaoPlano.validaPlano(sc, listaPlanos);

	    double valor = ValidacaoPlano.validaPreco(sc);

	    String descricao = ValidacaoPlano.validaDescricao(sc);

	    System.out.print("Duração em meses: ");
	    int duracao = sc.nextInt();
//	    LocalDate fimDoPlano = dataMatricula.plusMonths(duracao);acrescentar if
	    
//	    System.out.println("Se a data matrícula for " + dataMatricula + " o encerramento do plano é na data:" + fimDoPlano);	
	    
	    Plano novoPlano = new Plano(nomePlano, valor, descricao, duracao);
	    
	    listaPlano.add(novoPlano);

	    System.out.println("\n-- CADASTRO - PLANO - CONCLUÍDO --");
	    System.out.println("Cadastro do plano '" + nomePlano + "' concluído com sucesso!");
	    System.out.println(novoPlano);
	    sc.nextLine();
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

	    System.out.println("\n-- CADASTRO - ALUNO - CONCLUÍDO --");
	    System.out.println("Cadastro do aluno '" + nome + "' concluído com sucesso!");
	    System.out.println(novoAluno);
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

	    //colocar especialidade como enum
	    System.out.print("Especialidade: ");
	    String especialidade = sc.next();

	    Pessoa personalTrainer = new PersonalTrainer(nome, cpf, dataNascimento, contato, senha, inicioAtendimento, finalAtendimento, cref, especialidade);
	    
	    listaPersonal.add((PersonalTrainer) personalTrainer);

	    System.out.println("\n-- CADASTRO - PERSONAL - CONCLUÍDO --");
	    System.out.println("Cadastro do personal trainer '" + nome + "' concluído com sucesso!");
	    System.out.println(personalTrainer);
	}
	
	public void emitirRelatorioPlanos(List<Plano> listaPlano) {
	    try {
	        File arquivo = new File("C:\\Users\\parru\\Documents\\listaPlanos.txt");
	        FileWriter escritor = new FileWriter(arquivo);
	        if (listaPlano.isEmpty()) {
	            System.out.println("A lista de planos está vazia.");
	        } else {
	            for (Plano plano : listaPlano) {
	                escritor.write(plano + "\n");
	            }
	            System.out.println("Relatório de planos emitido com sucesso.");
	        }
	        
	        escritor.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void emitirRelatorioAlunos(List<Aluno> listaAlunos) {
		try {
	        File arquivo = new File("C:\\Users\\parru\\Documents\\listaAlunos.txt");
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
	}
	
	public void emitirRelatorioEquipe(List<PersonalTrainer> listaPersonal, List<Funcionario> listaFuncionario) {
		try {
	        File arquivo = new File("C:\\Users\\parru\\Documents\\listaEquipe.txt");
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
	            System.out.println("Relatório de funcionários emitido com sucesso.");
	        }
	        
	        escritor.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void emitirRelacaoAvaliacaoPorPeriodo(List<Avaliacao> listaAvaliacoes) {
		boolean continua = true; 
		int i = 0;
		try {
		File arquivo = new File("C:\\Users\\parru\\Documents\\listaAvaliacao.txt");
        FileWriter escritor = new FileWriter(arquivo);
		while(continua) {
			for(Avaliacao avaliacao: listaAvaliacoes) {
				LocalDate data = listaAvaliacoes.get(i).getData();
		        if (data.getMonthValue() == i && listaAvaliacoes.get(i) != null) {
		        	escritor.write(avaliacao + "\n");
		        } else if(listaAvaliacoes.isEmpty()){
		        	System.out.println("A lista de avaliações esta vazia.");
		        } else if( i == 13) continua = false;
		        
		          } i++;
		       } 
				System.out.println("Relatório de Avaliações emitido com sucesso.");
				escritor.close();
		    }catch (IOException e) {
		        e.printStackTrace();
		    }
	}
	
	public void voltarMenu(Scanner sc) throws InterruptedException{
	
        String voltar;
        do {
            System.out.print("Voltar ao Menu? (S/n)");
            voltar = sc.nextLine().trim(); // remove espaços em branco antes e depois da entrada

            if (voltar.isEmpty()) { // verifica se a entrada está em branco
                voltar = sc.nextLine().trim();
            }
        } while (voltar.isEmpty()); // continua solicitando entrada até que não esteja em branco

        switch (voltar.toUpperCase()) {
            case "S":
                exibirMenu(sc);
                break;
            case "N":
                
            default:
                System.out.println("Opção inválida. Por favor, escolha 'S' para sim ou 'N' para não.");
                voltarMenu(sc); // chama novamente o método se a opção for inválida
                break;
        }
    }

}
