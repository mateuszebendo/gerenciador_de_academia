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
		this.listaPlanos = RegistroPlano.criarListaPlanos();
		this.listaAlunos = RegistroAluno.criarListaAlunos();
		this.listaPersonal = RegistroPersonal.criarListaPersonal();
		this.listaAvaliacao = RegistroAvaliacao.criarListaAvaliacoes();
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
				break;
			case 5:
				emitirRelatorioAlunos(listaAlunos);
				break;
			case 6:
				emitirRelatorioEquipe(listaPersonal, listaFuncionario);
				break;
			case 7:
				emitirRelacaoAvaliacaoPorPeriodo(listaAvaliacao);
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
	    
	    String nomePlano = ValidacaoPlano.validaNomePlano(sc, listaPlano);

	    double valor = ValidacaoPlano.validaPreco(sc);

	    String descricao = ValidacaoPlano.validaDescricao(sc);

	    System.out.println("Duração em meses: ");
	    
	    int duracao = sc.nextInt();
	    
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

	    System.out.println("\n-- Novo aluno cadastrado com sucesso! --");
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
	        File arquivo = new File("C:\\Users\\Public\\listaEquipe.txt");
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
	}
	
	public void emitirRelatorioEquipe(List<PersonalTrainer> listaPersonal, List<Funcionario> listaFuncionario) {
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
	}
	
	public void emitirRelacaoAvaliacaoPorPeriodo(List<Avaliacao> listaAvaliacoes) {
		try {
			File arquivo = new File("C:\\Users\\Public\\Documents\\listaAvaliacao.txt");
	        FileWriter escritor = new FileWriter(arquivo);
	        for (int mes = 1; mes <= 12; mes++) {
                for (Avaliacao avaliacao : listaAvaliacoes) {
                    if (avaliacao.getData().getMonthValue() == mes) {
                        escritor.write(avaliacao + "\n");
                    }
                }
            }
			escritor.close();
		} catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void voltarMenu(Scanner sc) throws InterruptedException {
        String voltar;
        boolean opcaoValida = false;

        do {
            System.out.print("Voltar ao Menu? (S/n)");
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
