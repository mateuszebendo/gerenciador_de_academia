package org.serrafit.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ValidacaoAvaliacao {
	
	public static LocalDate validaData (Scanner sc) throws InterruptedException {
		boolean dataCorreta = false;
	    LocalDate data = null;
	    String dataAvaliacao = "";
	    
	    while (!dataCorreta) {
	        System.out.println("Data do Agendamento(dd-MM-yyyy): ");
	        dataAvaliacao = sc.nextLine();
	        
	        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        
	        try {
	            data = LocalDate.parse(dataAvaliacao, df);
	            
	            if (data.isAfter(LocalDate.now()) || data.isEqual(LocalDate.now())) {
	                break;
	            }
	            System.out.println("Data inválida. A data deve ser hoje ou qualquer dia futuro.");
	            Thread.sleep(1000);
	        } catch (Exception e) {
	            System.out.println("Formato de data inválido. Use o formato dd-MM-yyyy.");
	            Thread.sleep(1000);
	        }
	    }
	    return data;
	}
	
	
}
