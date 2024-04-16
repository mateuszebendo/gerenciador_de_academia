package org.serrafit.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacaoPessoa {
	
	public static String validaNome(Scanner sc) throws InterruptedException {
		String nome;
        while (true) {
            System.out.println("Digite o nome: ");
            nome = sc.nextLine();

            Pattern pattern = Pattern.compile("^[a-zA-ZÀ-ÿ]+(\\s+[a-zA-ZÀ-ÿ]+)*$");
            Matcher matcher = pattern.matcher(nome);

            if (matcher.matches()) {
                break;
            } else if (!(matcher.matches())) {
                System.out.println("Nome inválido. Use apenas letras e espaços.");
                Thread.sleep(1000);
            }
        }
        return nome;
    }
	
	public static String validaCpf(Scanner sc) throws InterruptedException {
    	String cpf = "";
    	String cpfNumerico = "";
    	boolean cpfCorreto = false;
    	while(!cpfCorreto) {
	        System.out.print("Digite o CPF: ");
	        cpf = sc.nextLine();
	        cpfNumerico = cpf.replaceAll("[^0-9]", "");

	        if (cpfNumerico.length() != 11) {
	        	System.out.println("CPF INVALIDO! Digite novamente!");
	        	Thread.sleep(1000);
	        }else {
	        	break;
	        }
    	}
    	return cpfNumerico;
	}
	
	public static LocalDate validaDataNascimento(Scanner sc) throws InterruptedException{
		boolean dataCorreta = false;
		LocalDate data = null;
		String dataNascimento = "";
		
		while(!dataCorreta) {
			System.out.println("Digite a Data de Nascimento: (no formato dd-MM-yyyy) ");
			dataNascimento = sc.nextLine();
			
			DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			
			try {
				data = LocalDate.parse(dataNascimento, df);
				
				if (data.getYear() >= (LocalDate.now().getYear() - 140) && data.getYear() <= LocalDate.now().getYear()) {
                    break;
				}
                System.out.println("Ano inválido. O ano deve estar entre " + (LocalDate.now().getYear() - 140) +  " e " + LocalDate.now().getYear());
                Thread.sleep(1000);
			}catch(Exception e) {
				System.out.println("Formato de data inválido. Use o formato dd-MM-yyyy.");
				Thread.sleep(1000);
			}
		}
		return data;
	}
	
	public static String validaContato(Scanner sc) throws InterruptedException {
		boolean quebra = true;
		
		String telefoneRegex = "\\d{9}";
		String telefoneFormat = "";
		while(quebra == true) {
			System.out.println("Telefone 9 digitos ( apenas numero): ");
			String telefone = sc.nextLine();
			if(telefone.length() == 9) {
				if (Pattern.matches(telefoneRegex, telefone)) {
					telefoneFormat = telefone.replaceAll("(\\d{5})(\\d{4})", "$1-$2");
		            return telefoneFormat;
				}
			}else {   
	            System.out.println("Telefone inválido! Digite o telefone novamente!");
	            Thread.sleep(1000);
	            quebra = true;
		    }
		}
		return telefoneFormat;
	}
	

	public static String validaSenha(Scanner sc) throws InterruptedException {
    	String senha = "";
    	boolean senhaOK = false;
    	
    	while(!senhaOK) {
    		System.out.println("Digite uma senha de até 10 digitos: ");
    		senha = sc.nextLine();
    		if (senha.length() > 10) {
	        	System.out.println("Senha muito longa! Digite novamente!");
	        	Thread.sleep(1000);
	        }else {
	        	break;
	        }
    	}
    	return senha;
	}
	
	
	
}

