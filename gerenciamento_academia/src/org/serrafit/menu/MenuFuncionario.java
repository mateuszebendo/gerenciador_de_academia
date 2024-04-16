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
		this.listaPlanos = Registra.criarPlano();
		this.listaAlunos = Registra.criaAlunos();
		this.listaPersonal = Registra.criaPersonais();
		this.listaFuncionario = Registra.criaFuncionario();
		this.listaAvaliacao = Registra.criaAvaliacao();
	}
	
	@Override
	public void exibirMenu(Scanner sc) {
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

	public void cadastrarPlano(Scanner sc, List <Plano> listaPlano) {
	    System.out.println("\n=== CADASTRAR NOVO PLANO ===");
	    System.out.print("Nome do Plano: ");
	    String nome = sc.next();

	    System.out.print("Valor: R$");
	    double valor = sc.nextDouble();

	    System.out.print("Descrição: ");
	    String descricao = sc.next();

	    System.out.print("Duração em meses: ");
	    int duracao = sc.nextInt();
//	    LocalDate fimDoPlano = dataMatricula.plusMonths(duracao);acrescentar if
	    
//	    System.out.println("Se a data matrícula for " + dataMatricula + " o encerramento do plano é na data:" + fimDoPlano);	
	    
	    Plano novoPlano = new Plano(nome, valor, descricao, duracao);
	    
	    listaPlano.add(novoPlano);

	    System.out.println("\n-- CADASTRO - PLANO - CONCLUÍDO --");
	    System.out.println("Cadastro do plano '" + nome + "' concluído com sucesso!");
	    System.out.println(novoPlano);
	    sc.nextLine();
	}

	public void cadastrarAluno(Scanner sc, List <Aluno> listaPessoa, List <Plano> listaPlanos) {
	    System.out.println("\n=== CADASTRO DE NOVO ALUNO ===");
	    System.out.print("Nome: ");
	    String nome = sc.next();

	    System.out.print("CPF: ");
	    String cpf = sc.next();

	    System.out.print("Data de Nascimento (AAAA-MM-DD): ");
	    LocalDate dataNascimento = LocalDate.parse(sc.next());

	    System.out.print("Contato: ");
	    String contato = sc.next();

	    System.out.print("Senha: ");
	    String senha = sc.next();

	    System.out.print("Data de Matrícula (AAAA-MM-DD): ");
	    LocalDate dataMatricula = LocalDate.parse(sc.next());

	    System.out.print("Planos Disponiveis: ");
	    System.out.println(listaPlanos);
	    String nomePlano = sc.next();
	    
	    int planoEscolhido = 0;
	    for(int i = 0; i < listaPlanos.size(); i++) {
	    	if(nomePlano.equalsIgnoreCase(listaPlanos.get(i).getNome())){
	    		planoEscolhido = i; 
	    	}
	    }

	    Pessoa novoAluno = new Aluno(nome, cpf, dataNascimento, contato, senha, dataMatricula, listaPlanos.get(planoEscolhido));
	    
	    listaPessoa.add((Aluno) novoAluno);

	    System.out.println("\n-- CADASTRO - ALUNO - CONCLUÍDO --");
	    System.out.println("Cadastro do aluno '" + nome + "' concluído com sucesso!");
	    System.out.println(novoAluno);
	}

	public void cadastrarPersonalTrainer(Scanner sc, List <PersonalTrainer> listaPersonal) {
	    System.out.println("\n=== CADASTRO DE NOVO PERSONAL TRAINER ===");
	    System.out.print("Nome: ");
	    String nome = sc.next();

	    System.out.print("CPF: ");
	    String cpf = sc.next();

	    System.out.print("Data de Nascimento (AAAA-MM-DD): ");
	    LocalDate dataNascimento = LocalDate.parse(sc.next());

	    System.out.print("Contato: ");
	    String contato = sc.next();

	    System.out.print("Senha: ");
	    String senha = sc.next();

	    System.out.print("Ínicio do Horário de Atendimento (HH:MM): ");
	    LocalTime inicioAtendimento = LocalTime.parse(sc.next());
	    
	    System.out.print("Final do Horário de Atendimento (HH:MM): ");
	    LocalTime finalAtendimento = LocalTime.parse(sc.next());

	    System.out.print("CREF: ");
	    String cref = sc.next();

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
	
	public void voltarMenu(Scanner sc) {
	
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
