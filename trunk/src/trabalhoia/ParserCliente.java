package trabalhoia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ParserCliente {
    public BufferedReader arqc;

    public ParserCliente(String NomeDoArquivo) throws IOException{
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
		return arqc.readLine();
	}

    public Cliente LerClientesDoArquivo() throws IOException{
                float nota;
                String notaSTR;
		String nome, id, categoria;
		String linha;


		linha = arqc.readLine();
                StringTokenizer st = new StringTokenizer(linha);

                //Metodo .trim() retira espacos em branco , para o parseFloat funcionar
		nome = st.nextToken().trim();
		id = st.nextToken().trim();
		notaSTR = st.nextToken().trim();
                categoria = st.nextToken().trim();
                //Converte os strings para float
                System.out.println("Nota: " + notaSTR);
		nota = Float.parseFloat(notaSTR.trim());

                System.out.println("CLIENTE---------------------------");
                System.out.println("Nome: " + nome);
                System.out.println("IDCarro: " + id);
                System.out.println("Nota: " + nota);
                System.out.println("Categoria: " + categoria);
                System.out.println("----------------------------------");

		Cliente cliente = new Cliente();
                cliente.setNome(nome);
                cliente.setID(id);
                cliente.setNota(nota);
                cliente.setCategoria(categoria);

		return cliente;
	}
}