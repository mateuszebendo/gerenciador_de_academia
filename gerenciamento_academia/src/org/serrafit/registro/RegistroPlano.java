package org.serrafit.registro;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.serrafit.classes.Plano;

public class RegistroPlano {
	static List<Plano> listaPlanos = new ArrayList<>();
	
	  	public static List<Plano> criarListaPlanos() throws IOException {
	        File arquivo = new File("C:\\Users\\Public\\Documents\\planos.txt");

	        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
	            String linhas;
	            String nomePlano = null;
	            double valor = 0.0;
	            String descricao = null;
	            int duracao = 0;
	            int variavelCorrespondente = 1;

	            while (true) {
	                linhas = leitor.readLine();
	                if (linhas != null && !linhas.isEmpty()) {
	                    if (linhas.contains(":")) {
	                        int indiceDoisPontos = linhas.indexOf(":");
	                        String dado = linhas.substring(indiceDoisPontos + 2);
	                        switch (variavelCorrespondente) {
	                            case 1:
	                                nomePlano = dado;
	                                break;
	                            case 2:
	                                valor = Double.parseDouble(dado);
	                                break;
	                            case 3:
	                                descricao = dado;
	                                break;
	                            case 4:
	                                duracao = Integer.parseInt(dado.split(" ")[0]);
	                                break;
	                        }
	                        variavelCorrespondente++;
	                    }
	                } else {
	                    if (nomePlano != null && valor != 0.0 && descricao != null && duracao != 0) {
	                        Plano plano = new Plano(nomePlano, valor, descricao, duracao);
	                        listaPlanos.add(plano);
	                        nomePlano = null;
	                        valor = 0.0;
	                        descricao = null;
	                        duracao = 0;
	                        variavelCorrespondente = 1;
	                    }
	                    if (linhas == null) {
	                        break;
	                    }
	                }
	            }
	        }

	        return listaPlanos;
	    }
	  	
	    public static Plano criarObjetoPlano(String nomePlano) throws IOException {
	        File arquivo = new File("C:\\Users\\Public\\Documents\\planos.txt");

	        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
	            String linhas;
	            while ((linhas = leitor.readLine()) != null) {
	                if (linhas.contains("Nome do Plano:")) {
	                    String nomeLido = linhas.substring(linhas.indexOf(":") + 2).trim();
	                    if (nomeLido.equals(nomePlano)) {
	                        String[] dados = new String[3];
	                        for (int i = 0; i < 3; i++) {
	                            linhas = leitor.readLine();
	                            if (linhas.contains(":")) {
	                                dados[i] = linhas.substring(linhas.indexOf(":") + 2).trim();
	                            }
	                        }
	                        return new Plano(nomeLido, Double.parseDouble(dados[0]), dados[1], Integer.parseInt(dados[2].split(" ")[0]));
	                    }
	                }
	            }
	        }

	        return null;
	    }
	}

