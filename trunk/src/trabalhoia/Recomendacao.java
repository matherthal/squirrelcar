package trabalhoia;

import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Collections;

public class Recomendacao {
    public int menor1 = 0;
    public int menor2 = 0;
    public int menor3 = 0;
    public LinkedList<Carro> carrosList = new LinkedList<Carro>();
    public LinkedList<Cliente> clienteList = new LinkedList<Cliente>();
    private Carro carro = new Carro();
    private Cliente cliente = new Cliente();
    public Carro interesse = new Carro();
    private View view;
    public LinkedList<ClienteMedia> clienteMediaList = new LinkedList<ClienteMedia>();

    public Recomendacao(View view)
    {
        this.view = view;
    }

    //Processa Arquivo contendo as entradas dos carros
    public void ProcessaArquivoCarro() throws IOException{
        ParserCarros arq_carro = new ParserCarros("EntradaCarros.txt");
        while(arq_carro.arq.ready())
        {
            carro = arq_carro.LerCarrosDoArquivo();
            carrosList.add(carro);
	}

    }

    //Processa as entradas do arquivo contendo as entradas dos clientes
    public void ProcessaArquivoCliente() throws IOException{
        ParserCliente arq_cliente = new ParserCliente("EntradaClientes.txt");
        while(arq_cliente.arqc.ready())
        {
            cliente = arq_cliente.LerClientesDoArquivo();
            clienteList.add(cliente);
	}
    }

    //calcula a média do cliente
    public void mediaInteresses()
    {
        ClienteMedia clienteMedia = new ClienteMedia();

        for (int i = 0; i<(clienteList.size()); i++)
        {
            String nome = clienteList.get(i).getNome();
            int c = 0;
            float nota = 0;
            boolean achou = false;
            for (int j = 0; j<(clienteList.size()); j++)
            {
                if (clienteList.get(j).getNome().equalsIgnoreCase(nome))
                {
                    ++c;
                    nota += clienteList.get(j).getNota();
                }
            }
            for(int w = 0; w<(clienteMediaList.size()); w++)
            {
                if (clienteMediaList.get(w).getNome().equalsIgnoreCase(nome))
                {
                    achou = true;
                }

            }
            if (!achou)
            {
                clienteMedia = new ClienteMedia();
                clienteMedia.setMedia(nota/c);
                clienteMedia.setNome(nome);
                clienteMedia.setDist(0);
                clienteMediaList.add(clienteMedia);
            }

        }
    }

    //Ordena Os clientes alfabeticamente
    public void ordenarporNome()
    {
        /*Cliente trocar = new Cliente();
        boolean trocou = true;
        while(trocou){
            trocou = false;
            for (int i = 0; i<(clienteList.size() -1); i++)
            {
                if((clienteList.get(i).getNome().compareToIgnoreCase(clienteList.get(i+1).getNome()))>0)
                {
                   trocar.setNome(clienteList.get(i+1).getNome());
                   trocar.setID(clienteList.get(i+1).getID());
                   trocar.setNota(clienteList.get(i+1).getNota());
                   trocar.setCategoria(clienteList.get(i+1).getCategoria());

                   clienteList.get(i+1).setNome(clienteList.get(i).getNome());
                   clienteList.get(i+1).setID(clienteList.get(i).getID());
                   clienteList.get(i+1).setNota(clienteList.get(i).getNota());
                   clienteList.get(i+1).setCategoria(clienteList.get(i).getCategoria());

                   clienteList.get(i).setNome(trocar.getNome());
                   clienteList.get(i).setID(trocar.getID());
                   clienteList.get(i).setNota(trocar.getNota());
                   clienteList.get(i).setCategoria(trocar.getCategoria());

                   trocou = true;
                }
            }
        }*/
        Collections.sort(this.clienteList);
    }

    //Ordena na interface por afinidade
    public void OrdenaporAfinidade(){
         Collections.sort(this.carrosList);
    }

    //Imprime resultado da pesquisa
    public void Imprimir(Carro imprime){
        System.out.println("ITEM----------------------------------------");
        System.out.println("Afinidade: " + imprime.getAfinidade());
        System.out.println("Marca: " + imprime.getMarca());
        System.out.println("Modelo: " + imprime.getModelo());
        System.out.println("Numero Portas: " + imprime.getPortas());
        System.out.println("Tipo Combustivel: " + imprime.getCombustivel());
        System.out.println("Cor: " + imprime.getCor());
        System.out.println("Preco: " + imprime.getPreco());
        System.out.println("Ano: " + imprime.getAno());
        System.out.println("Categoria: " + imprime.getCategoria());
        System.out.println("Cambio: " + imprime.getCambio());
        System.out.println("ID: " + imprime.getID());
        System.out.println("--------------------------------------------");
    }

    //Imprime tudo
    public void Imprimir_Tudo(){
        ListIterator<Carro> iter = this.carrosList.listIterator();
        Carro carro = null;
        int i = 0;
        while (iter.hasNext())
        {
            carro = iter.next();
            view.insert(i, carro);
            ++i;

        }
    }

    //Reseta as afinidades do usuário atual, quando for pesquisado outro usuário
    public void ResetAfinidade(){
        ListIterator<Carro> iter = this.carrosList.listIterator();
        Carro carro_reset = null;
        while (iter.hasNext()) {
            carro_reset = iter.next();
            carro_reset.setAfinidade(0);
        }
    }

    //Reseta as médias do usuário atual, quando for pesquisado outro usuário
    public void ResetMediaList(){
        LinkedList<ClienteMedia> iter = this.clienteMediaList;
        iter.clear();

    }

    //Reseta as notas de carros dadas pelo usuário atual, quando for pesquisado outro usuário
    public void ResetNotaCarro(){
    ListIterator<Carro> iter = this.carrosList.listIterator();
    Carro carro_resetnota = null;
    while (iter.hasNext())
    {
        carro_resetnota = iter.next();
        carro_resetnota.setNota(0);
        carro_resetnota.setAvaliacao(0);
        carro_resetnota.setAvaliacaoPrev(0);
        }
    }

    //Seta o nível de afinidade que um usuário terá com um carro
    public void Recomendacao(Interesse interesse){
        ListIterator<Carro> iter = this.carrosList.listIterator();
        Carro carro_aux = null;
        while (iter.hasNext()) {
            carro_aux = iter.next();
            if (interesse.getMarca() != null && !interesse.getMarca().isEmpty())
            {
                if (carro_aux.getMarca().equalsIgnoreCase(interesse.getMarca()))
                    carro_aux.setAfinidade(carro_aux.getAfinidade()+3);
            }
            else
                carro_aux.setAfinidade(carro_aux.getAfinidade()+3);
            if(interesse.getModelo() != null && !interesse.getModelo().isEmpty())
            {
                if(carro_aux.getModelo().equalsIgnoreCase(interesse.getModelo()))
                    carro_aux.setAfinidade(carro_aux.getAfinidade()+3);
            }
            else
                carro_aux.setAfinidade(carro_aux.getAfinidade()+3);
            if(interesse.getPortas() != -1)
            {
                if(carro_aux.getPortas() == (interesse.getPortas()))
                    carro_aux.setAfinidade(carro_aux.getAfinidade()+1);
            }
            else
                carro_aux.setAfinidade(carro_aux.getAfinidade()+1);
            if(interesse.getCombustivel() != null && !interesse.getCombustivel().isEmpty())
            {
                if(carro_aux.getCombustivel().equalsIgnoreCase(interesse.getCombustivel()))
                    carro_aux.setAfinidade(carro_aux.getAfinidade()+2);
            }
            else
                carro_aux.setAfinidade(carro_aux.getAfinidade()+2);
            if(interesse.getCor() != null && !interesse.getCor().isEmpty())
            {
                if(carro_aux.getCor().equalsIgnoreCase(interesse.getCor()))
                    carro_aux.setAfinidade(carro_aux.getAfinidade()+2);
            }
            else
                carro_aux.setAfinidade(carro_aux.getAfinidade()+2);

            if(interesse.getPrecomin() != -1 && interesse.getPrecomax() != 999999)
            {
                if((carro_aux.getPreco() >= (interesse.getPrecomin())) && ((carro_aux.getPreco() <= (interesse.getPrecomax()))))
                    carro_aux.setAfinidade(carro_aux.getAfinidade()+3);

            }
            else
                carro_aux.setAfinidade(carro_aux.getAfinidade()+3);

            if(interesse.getAno() != -1)
            {
                if(carro_aux.getAno() == (interesse.getAno()))
                    carro_aux.setAfinidade(carro_aux.getAfinidade()+2);
            }
            else
                carro_aux.setAfinidade(carro_aux.getAfinidade()+2);

            if(interesse.getCategoria() != null && !interesse.getCategoria().isEmpty())
            {
                if(carro_aux.getCategoria().equalsIgnoreCase(interesse.getCategoria()))
                    carro_aux.setAfinidade(carro_aux.getAfinidade()+3);
            }
            else
                carro_aux.setAfinidade(carro_aux.getAfinidade()+3);

            if(interesse.getCambio() != null && !interesse.getCambio().isEmpty())
            {
                if(carro_aux.getCambio().equalsIgnoreCase(interesse.getCambio()))
                    carro_aux.setAfinidade(carro_aux.getAfinidade()+1);
            }
            else
                carro_aux.setAfinidade(carro_aux.getAfinidade()+1);
            //total 20
            System.out.println("RECOMENDACAO-----------------------------");
            System.out.println("ID: " + carro_aux.getID());
            System.out.println("Afinidade: " + carro_aux.getAfinidade());
            System.out.println("-----------------------------------------");

        }

    }
    
    //Aqui se avalia os carros
    public void AvaliacaoClientesCarros() {
        ListIterator<Carro> iter = this.carrosList.listIterator();
        ListIterator<Cliente> iter2 = this.clienteList.listIterator();
        Carro carro_aux = null;
        Cliente cliente_aux2 = null;

        while (iter.hasNext())
        {
            float nota = 0;
            float media = 0;
            int count = 0;
            carro_aux = iter.next();
            while(iter2.hasNext())
            {
                cliente_aux2 = iter2.next();
                if (cliente_aux2.getID().equalsIgnoreCase(carro_aux.getID()))
                {
                    nota = nota + cliente_aux2.getNota();
                    count = count + 1;
                }
            }
            iter2 = this.clienteList.listIterator();
            System.out.println("AVALIACAO-------------------------------");
            System.out.println("Nota: " + nota);
            System.out.println("Count: " + count);
            if (count != 0)
            {
                media = nota / count;
                carro_aux.setNota((float) ((Math.round(media * 10.0)) / 10.0));
            }
            System.out.println("Media: " + media);
            System.out.println("-----------------------------------------");
        }
    }

    // A media das avaliacoes utilizadas para o calculo da Correlação
    public float AchaMedia(String nome) {
        ListIterator<ClienteMedia> iter = this.clienteMediaList.listIterator();
        ClienteMedia cliente = null;
        float media = 0;
        boolean achou = false;

        while (iter.hasNext() && !achou)
        {
            cliente = iter.next();
            if (cliente.getNome().equalsIgnoreCase(nome))
            {
                achou = true;
                media = cliente.getMedia();
            }

        }
        return media;
    }

    // A nota dos interesses utilizadas para o calculo do Correlação
    public float PegaNotaInteresseProximo(String nome, String ID) {
        ListIterator<Cliente> iter = this.clienteList.listIterator();
        Cliente cliente = null;
        float nota = 0;
        boolean achou = false;

        while (iter.hasNext() && !achou)
        {
            cliente = iter.next();
            if (cliente.getNome().equalsIgnoreCase(nome) && cliente.getID().equalsIgnoreCase(ID))
            {
                achou = true;
                nota = cliente.getNota();
                return nota;
            }
        }
        return -1;
    }

    // Funcao para calcular a distancia euclidiana
    public void DistanciaEuclidiana(String nome)
    //o nome = nome do usario corrente
    {

        String nome_aux = null;
        ListIterator<Cliente> iter = this.clienteList.listIterator();
        ListIterator<Cliente> iter2 = this.clienteList.listIterator();
        Cliente interesseCorrente = null;
        Cliente cliente2 = null;
        float dist = 0;
        String ID = null;
        String ID2 = null;
        int w = 0;


        for (int i = 0; i< clienteMediaList.size(); i++)
        {
            nome_aux = clienteMediaList.get(i).getNome();
            for (int j = 0; j< clienteList.size(); j++)
            {

                    if (clienteList.get(j).getNome().equalsIgnoreCase(nome))
                    {
                        for (int k = 0; k < clienteList.size(); k++)
                        {
                            if(clienteList.get(k).getNome().equalsIgnoreCase(nome_aux) && (clienteList.get(k).getID().equalsIgnoreCase(clienteList.get(j).getID())))
                            {
                                dist = (float)(dist + Math.pow((clienteList.get(j).getNota() / 10) - (clienteList.get(k).getNota() / 10), 2));
                            }
                        }
                    }
            }
            dist = (float) Math.sqrt(dist);
            clienteMediaList.get(i).setDist(dist);
            dist = 0;
        }
    }

    private int SearchName(String nome_aux) {
        for (int i = 0; i<clienteMediaList.size(); i++)
        {
            if(clienteMediaList.get(i).getNome().equalsIgnoreCase(nome_aux)) {
               return i;
            }
        }
        return -1;
    }

    public boolean SearchNameEID(String nome_aux,String ID) {
        for (int i = 0; i< clienteList.size(); i++)
        {
            if(clienteList.get(i).getNome().equalsIgnoreCase(nome_aux) &&
                    clienteList.get(i).getID().equalsIgnoreCase(ID))
            {
               return true;
            }
        }
        return false;
    }

    //Procura as 3 pessoas que estao + proximas do interesse
    public void setMenores ()
    {
        menor1 = 0;
        menor2 = 0;
        menor3 = 0;
        float dist1 = 1;
        float dist2 = 1;
        float dist3 = 1;
        if (clienteMediaList.get(0).getDist() == 0)
        {
            menor1 = 1;
            menor2 = 1;
            menor3 = 1;
        }

        for (int i = 0; i<clienteMediaList.size(); i++)
        {
            if (clienteMediaList.get(i).getDist() > 0)
            {
                if (dist1 > clienteMediaList.get(i).getDist())
                {
                    menor3 = menor2;
                    menor2 = menor1;
                    menor1 = i;
                    dist3 = dist2;
                    dist2 = dist1;
                    dist1 = clienteMediaList.get(i).getDist();
                }
                else
                {
                    if(dist2 > clienteMediaList.get(i).getDist() )
                    {
                        menor3 = menor2;
                        menor2 = i;
                        dist3 = dist2;
                        dist2 = clienteMediaList.get(i).getDist();
                    }
                    else
                        if (dist3 > clienteMediaList.get(i).getDist() )
                        {
                            menor3 = i;
                            dist3 = clienteMediaList.get(i).getDist();
                        }
                }
            }
        }


    }

    public void CalculandoNotaPrevista(Interesse interesse){
        //Vetores para a media e as notas das 3 pessoas + proximas
        double[] vetormedia = new double[3];
        double[] vetoravaliacao = new double[3];
        double[] vetorsim = new double[3];
        ListIterator<Carro> iter = this.carrosList.listIterator();
        Carro carro = null;
        int i = 0;
        float notaInteresse = 0;
        float nota1 = 0;
        float nota2 = 0;
        float nota3 = 0;
        float media1 = 0;
        float media2 = 0;
        float media3 = 0;
        float mediaU = 0;
        float sim1 = 0;
        float sim2 = 0;
        float sim3 = 0;

        DistanciaEuclidiana(interesse.getNome());
        ImprimirMediaList();
        setMenores ();

        System.out.println("VIZINHOS-----------------------------------------");
        System.out.println("Menor1:" + clienteMediaList.get(menor1).getNome());
        System.out.println("Menor2:" + clienteMediaList.get(menor2).getNome());
        System.out.println("Menor3:" + clienteMediaList.get(menor3).getNome());
        System.out.println("-------------------------------------------------");

        while (iter.hasNext())
        {
            carro = iter.next();
            notaInteresse = PegaNotaInteresseProximo(interesse.getNome(), carro.getID());
            carro.setAvaliacao(notaInteresse);
            if (carro.getAvaliacao() <= -1)
            {
                nota1 = PegaNotaInteresseProximo(clienteMediaList.get(menor1).getNome(), carro.getID());
                nota2 = PegaNotaInteresseProximo(clienteMediaList.get(menor2).getNome(), carro.getID());
                nota3 = PegaNotaInteresseProximo(clienteMediaList.get(menor3).getNome(), carro.getID());
                if (nota1 == -1.0){nota1 = 0;}
                if (nota2 == -1.0){nota2 = 0;}
                if (nota3 == -1.0){nota3 = 0;}
                vetoravaliacao[0] = nota1;
                vetoravaliacao[1] = nota2;
                vetoravaliacao[2] = nota3;
                media1 = AchaMedia(clienteMediaList.get(menor1).getNome());
                media2 = AchaMedia(clienteMediaList.get(menor2).getNome());
                media3 = AchaMedia(clienteMediaList.get(menor3).getNome());
                mediaU = AchaMedia(interesse.getNome());
                vetormedia[0] = media1;
                vetormedia[1] = media2;
                vetormedia[2] = media3;
                sim1 = clienteMediaList.get(menor1).getDist();
                sim2 = clienteMediaList.get(menor1).getDist();
                sim3 = clienteMediaList.get(menor1).getDist();
                vetorsim[0] = sim1;
                vetorsim[1] = sim2;
                vetorsim[2] = sim3;

                float result = (float) Correlacao.previsaoAval(vetoravaliacao,vetormedia,mediaU,vetorsim);
                carro.setAvaliacaoPrev(result);

            }
            Collections.sort(this.carrosList);
            int afinidade = 0;
            afinidade = this.carrosList.getFirst().getAfinidade();
            if (carro.getAfinidade() == afinidade)
            {
                view.insert(i, carro);
                ++i;
            }
        }
    }

    public void Imprimir_Resultado(){
        ListIterator<Carro> iter = this.carrosList.listIterator();
        Carro carro = null;
        int i = 0;
        while (iter.hasNext())
        {
            carro = iter.next();
            if (carro.getAfinidade() >= 10)
            {
                view.insert(i, carro);
                ++i;
            }
        }
    }

    public void ImprimirMediaList(){
        ListIterator<ClienteMedia> iter = this.clienteMediaList.listIterator();
        ClienteMedia imprime = null;
        while (iter.hasNext()) {
            imprime = iter.next();
            System.out.println("MEDIALIST-----------------------------------");
            System.out.println("Nome: " + imprime.getNome());
            System.out.println("Media: " + imprime.getMedia());
            System.out.println("Dist: " + imprime.getDist());
            System.out.println("--------------------------------------------");
        }
    }

    public void ImprimirClienteList(){
        ListIterator<Cliente> iter = this.clienteList.listIterator();
        Cliente imprime = null;
        while (iter.hasNext()) {
            imprime = iter.next();
            /*
             * Pra que serve isso aqui? -Giulio
             */
            System.out.println("------------CLIENTELIST------XXXXXXXXXXXX---");
            System.out.println("Nome: " + imprime.getNome());
            System.out.println("--------------------------------------------");
        }
    }
}

