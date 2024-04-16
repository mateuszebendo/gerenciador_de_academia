package org.serrafit.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.serrafit.classes.Aluno;

public class ValidacaoPersonal {
	
	public static String validaCref(Scanner sc) {
		String numCref;
		
		while(true) {
			System.out.println("Insira apenas os numeros do CREF: ");
			numCref = sc.nextLine();
			
			Pattern pattern = Pattern.compile("^\\d{1,5}$");
            Matcher matcher = pattern.matcher(numCref);
            
            if(matcher.matches()) {
            	 return "CREF-" + numCref;
            }   
		}
	}
	
	public static LocalTime validaInicioAtendimento(Scanner sc) {
		String regex = "^(0[5-9]|1\\d|2[0-3]):[0-5]\\d$";
        boolean horarioValido = false;
        LocalTime inicio = null;

        while (!horarioValido) {
            System.out.println("Início do Atendimento: \n [05h00 AM-23h00 PM]\n");

            try {
                String horarioTexto = sc.nextLine();
                if (Pattern.matches(regex, horarioTexto)) {
                    inicio = LocalTime.parse(horarioTexto);
                    horarioValido = true;
                } else {
                    System.out.println("Horário inválido, digite um horário entre [05h00 AM-23h00 PM]");
                }
            } catch (Exception e) {
                System.out.println("Erro ao processar o horário: " + e.getMessage());
            }
        }

        return inicio;
    }
	
	public static LocalTime validaFinalAtendimento(LocalTime inicioAtendimento, Scanner sc) {
        LocalTime finalAtendimento = null;
        String regex = "^(0[5-9]|1\\d|2[0-3]):[0-5]\\d$";
        boolean horarioValido = false;

        while (finalAtendimento == null || finalAtendimento.isBefore(inicioAtendimento.plusHours(1))) {
            System.out.println("Fim do Atendimento: \n[min: 1h de distancia do Inicio]:");
            try {
            String horarioTexto = sc.nextLine();
            if (Pattern.matches(regex, horarioTexto)) {
            	finalAtendimento = LocalTime.parse(horarioTexto);
                horarioValido = true;
            } else {
                System.out.println("Horário inválido: mínimo de 1h de distancia do Inicio");
            }
            
	        } catch (Exception e) {
	            System.out.println("Erro ao processar o horário: " + e.getMessage());
	        }
        }
        return finalAtendimento;
    }
	
	public static Aluno validaAlunoExistente(Scanner sc, List <Aluno> listaAlunos) throws InterruptedException {
		String cpf = ValidacaoPessoa.validaCpf(sc);
		while (true) {
            System.out.println("CPF do Aluno: ");

            Aluno alunoAvaliacao = null;
            for (Aluno aluno : listaAlunos) {
                if (cpf.equals(aluno.getCpf())) {
                    alunoAvaliacao = aluno;
                    System.out.println("Aluno encontrado!");
                    return alunoAvaliacao;
                }
            }
            System.out.println("O CPF digitado não corresponde a nenhum aluno existente. Tente novamente.");
            Thread.sleep(1000);
        }
    }	
}
