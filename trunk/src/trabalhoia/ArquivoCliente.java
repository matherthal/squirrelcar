/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalhoia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Troy
 */
public class ArquivoCliente {
    public BufferedReader arqc;

    public ArquivoCliente(String NomeDoArquivo) throws IOException{
		try {
	        arqc = new BufferedReader(new FileReader(NomeDoArquivo));
	        arqc.readLine(); //Le primeira linha de coment�rio e dispensa
	    }
		catch (IOException e) {
			//Caso arquivo nao exista, Imprime no console
	    	System.out.println("Arquivo nao existe");
	    }
        }

    //lê uma linha do arquivo
    private String LerLinha() throws IOException{
		//Arquivo esta pronto? Entao retorna linha String
		//Senao , retorna vazio string;
		return arqc.ready() ? arqc.readLine() : "" ;
	}

    public Clientes LerClientesDoArquivo() throws IOException{
                float nota;
                String notaSTR;
		String nome, id, categoria;
		String linha;

		linha = this.LerLinha();
                //Metodo .trim() retira espacos em branco , para o parseFloat funcionar
		nome = linha.substring(0,19).trim();
		id = linha.substring(21,24).trim();
		notaSTR = linha.substring(26,30);
                categoria = linha.substring(32,43);
                //Converte os strings para float
                System.out.println("Nota: " + notaSTR);
		nota = Float.parseFloat(notaSTR.trim());

                System.out.println("--------CLIENTE---------------------");
                System.out.println("Nome: " + nome);
                System.out.println("IDCarro: " + id);
                System.out.println("Nota: " + nota);
                System.out.println("Categoria: " + categoria);
                System.out.println("----------------------------------");

		Clientes cliente = new Clientes();
                cliente.setNome(nome);
                cliente.setID(id);
                cliente.setNota(nota);
                cliente.setCategoria(categoria);

		return cliente;
	}

}
