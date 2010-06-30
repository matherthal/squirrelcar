package trabalhoia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ArquivoCarro {
    public BufferedReader arq;
        //Cria um arquivo de entrada
    	public ArquivoCarro(String NomeDoArquivo) throws IOException{
		try {
	        arq = new BufferedReader(new FileReader(NomeDoArquivo));
	        arq.readLine(); //Le primeira linha de coment�rio e dispensa
	    }
		catch (IOException e) {
			//Caso arquivo n�o exista, Imprime no console
	    	System.out.println("Arquivo nao existe");
	    }
        }
        //lê uma linha do arquivo
	private String LerLinha() throws IOException{
		//Arquivo esta pronto? Entao retorna linha String
		//Senao , retorna vazio string;
		return arq.ready() ? arq.readLine() : "" ;
	}

    //converte linha de arquivo em um objeto do tipo processo
	public Carros LerCarrosDoArquivo() throws IOException{
                int portas, preco, ano;
                String portasSTR, precoSTR, anoSTR;
		String marca, modelo, combustivel, cor, categoria;
		String	cambio, id;
		String linha;

		linha = this.LerLinha();
                //Metodo .trim() retira espacos em branco , para o parseInt funcionar
		marca = linha.substring(0,12).trim();
		modelo = linha.substring(14,29).trim();
		portasSTR = linha.substring(31,32);
		combustivel = linha.substring(34,44).trim();
		cor = linha.substring(46,64).trim();
		precoSTR = linha.substring(66,72);
		anoSTR = linha.substring(74,78);
		categoria = linha.substring(80,91).trim();
                cambio = linha.substring(93,103).trim();
                id = linha.substring(105,108).trim();
                //Converte os strings para inteiro
		portas = Integer.parseInt(portasSTR.trim());
                preco = Integer.parseInt(precoSTR.trim());
		ano = Integer.parseInt(anoSTR.trim());

                System.out.println("--------CARRO---------------------");
                System.out.println("Marca: " + marca);
                System.out.println("Modelo: " + modelo);
                System.out.println("Portas: " + portas);
                System.out.println("Combustivel: " + combustivel);
                System.out.println("Cor: " + cor);
                System.out.println("Preco: " + preco);
                System.out.println("Ano " + ano);
                System.out.println("Categoria: " + categoria);
                System.out.println("Cambio: " + cambio);
                System.out.println("ID: " + id);
                System.out.println("----------------------------------");

                Carros carro = new Carros();
                carro.setMarca(marca);
                carro.setModelo(modelo);
                carro.setPortas(portas);
                carro.setCombustivel(combustivel);
                carro.setCor(cor);
                carro.setPreco(preco);
                carro.setAno(ano);
                carro.setCategoria(categoria);
                carro.setCambio(cambio);
                carro.setID(id);

		return carro;
	}


}
