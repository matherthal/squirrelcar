package trabalhoia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Vector;
import java.util.Collections;

public class SistemaRec {

    

    public int menor1 = 0;
    public int menor2 = 0;
    public int menor3 = 0;
    public LinkedList<Carros> carrosList = new LinkedList<Carros>();
    public LinkedList<Clientes> clienteList = new LinkedList<Clientes>();
    private Carros carro = new Carros();
    private Clientes cliente = new Clientes();
    public Carros usuario = new Carros();
    private View view;
    public LinkedList<ClienteMedia> clienteMediaList = new LinkedList<ClienteMedia>();
    public LinkedList<Float> vetorPearson = new LinkedList<Float>();

    public SistemaRec(View view)
    {
        this.view = view;
    }


    public void ProcessaArquivoCarro() throws IOException{
        ArquivoCarro arq_carro = new ArquivoCarro("../EntradaCarros.txt");
        while(arq_carro.arq.ready())
        {
            carro = arq_carro.LerCarrosDoArquivo();
            carrosList.add(carro);
	}

    }

    public void ProcessaArquivoCliente() throws IOException{
        ArquivoCliente arq_cliente = new ArquivoCliente("../EntradaClientes.txt");
        while(arq_cliente.arqc.ready())
        {
            cliente = arq_cliente.LerClientesDoArquivo();
            clienteList.add(cliente);
	}

    }

    public void mediaUsuarios()
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

    public void ordenarporNome()
    {
        Clientes trocar = new Clientes();
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
        }
    }

    public void OrdenaporAfinidade(){
         Collections.sort(this.carrosList);
    }


        public void DebugOrdenar(){
        ListIterator<Carros> iter = this.carrosList.listIterator();
        Carros imprime = null;
        while (iter.hasNext()) {
            imprime = iter.next();
            System.out.println("************ORDENANDO******************");
            System.out.println("Afinidade: " + imprime.getAfinidade());
            System.out.println("ID: " + imprime.getID());
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");


        }
    }

    public void Imprimir(Carros imprime){
        System.out.println("------------IMPRIMINDO----------------------");
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
    public void Imprimir_Resultado(){
        ListIterator<Carros> iter = this.carrosList.listIterator();
        Carros carro = null;
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

    public void Imprimir_Tudo(){
        ListIterator<Carros> iter = this.carrosList.listIterator();
        Carros carro = null;
        int i = 0;
        while (iter.hasNext())
        {
            carro = iter.next();
            view.insert(i, carro);
            ++i;

        }
    }
    public void ResetAfinidade(){
        ListIterator<Carros> iter = this.carrosList.listIterator();
        Carros carro_reset = null;
        while (iter.hasNext()) {
            carro_reset = iter.next();
            carro_reset.setAfinidade(0);
            System.out.println("----------------------------------");
            System.out.println("ID: " + carro_reset.getID());
            System.out.println("AfinidadeRESETADA: " + carro_reset.getAfinidade());
        }
    }

    public void ResetMediaList(){
        LinkedList<ClienteMedia> iter = this.clienteMediaList;
        iter.clear();

    }

    public void ResetNotaCarro(){
    ListIterator<Carros> iter = this.carrosList.listIterator();
    Carros carro_resetnota = null;
    while (iter.hasNext())
    {
        carro_resetnota = iter.next();
        carro_resetnota.setNota(0);
        carro_resetnota.setAvaliacao(0);
        carro_resetnota.setAvaliacaoPrev(0);
        System.out.println("----------------------------------");
        System.out.println("ID: " + carro_resetnota.getID());
        System.out.println("AfinidadeRESETADA: " + carro_resetnota.getAfinidade());
        }
    }
    public void Recomendacao(Usuario usuario){
        ListIterator<Carros> iter = this.carrosList.listIterator();
        Carros carro_aux = null;
        while (iter.hasNext()) {
            carro_aux = iter.next();
            if (usuario.getMarca() != null)
            {
                if (carro_aux.getMarca().equalsIgnoreCase(usuario.getMarca()))
                    carro_aux.setAfinidade(carro_aux.getAfinidade()+3);
            }
            else
                carro_aux.setAfinidade(carro_aux.getAfinidade()+3);
            if(usuario.getModelo() != null)
            {
                if(carro_aux.getModelo().equalsIgnoreCase(usuario.getModelo()))
                    carro_aux.setAfinidade(carro_aux.getAfinidade()+3);
            }
            else
                carro_aux.setAfinidade(carro_aux.getAfinidade()+3);
            if(usuario.getPortas() != -1)
            {
                if(carro_aux.getPortas() == (usuario.getPortas()))
                    carro_aux.setAfinidade(carro_aux.getAfinidade()+1);
            }
            else
                carro_aux.setAfinidade(carro_aux.getAfinidade()+1);
            if(usuario.getCombustivel() != null)
            {
                if(carro_aux.getCombustivel().equalsIgnoreCase(usuario.getCombustivel()))
                    carro_aux.setAfinidade(carro_aux.getAfinidade()+2);
            }
            else
                carro_aux.setAfinidade(carro_aux.getAfinidade()+2);
            if(usuario.getCor() != null)
            {
                if(carro_aux.getCor().equalsIgnoreCase(usuario.getCor()))
                    carro_aux.setAfinidade(carro_aux.getAfinidade()+2);
            }
            else
                carro_aux.setAfinidade(carro_aux.getAfinidade()+2);
            
            if(usuario.getPrecomin() != -1 || usuario.getPrecomax() != 999999)
            {
                if((carro_aux.getPreco() >= (usuario.getPrecomin())) && ((carro_aux.getPreco() <= (usuario.getPrecomax()))))
                    carro_aux.setAfinidade(carro_aux.getAfinidade()+3);
            }
            else
                carro_aux.setAfinidade(carro_aux.getAfinidade()+3);

            if(usuario.getAno() != -1)
            {
                if(carro_aux.getAno() == (usuario.getAno()))
                    carro_aux.setAfinidade(carro_aux.getAfinidade()+2);
            }
            else
                carro_aux.setAfinidade(carro_aux.getAfinidade()+2);

            if(usuario.getCategoria() != null)
            {
                if(carro_aux.getCategoria().equalsIgnoreCase(usuario.getCategoria()))
                    carro_aux.setAfinidade(carro_aux.getAfinidade()+3);
            }
            else
                carro_aux.setAfinidade(carro_aux.getAfinidade()+3);

            if(usuario.getCambio() != null)
            {
                if(carro_aux.getCambio().equalsIgnoreCase(usuario.getCambio()))
                    carro_aux.setAfinidade(carro_aux.getAfinidade()+1);
            }
            else
                carro_aux.setAfinidade(carro_aux.getAfinidade()+1);
            //total 20
            System.out.println("--------DEBUGANDO RECOMENDA--------------");
            System.out.println("ID: " + carro_aux.getID());
            System.out.println("Afinidade: " + carro_aux.getAfinidade());
            System.out.println("-----------------------------------------");

        }

    }

    public void AvaliacaoClientesCarros() {
        ListIterator<Carros> iter = this.carrosList.listIterator();
        ListIterator<Clientes> iter2 = this.clienteList.listIterator();
        Carros carro_aux = null;
        Clientes cliente_aux2 = null;
        
        //System.out.println("----------Categoria Usuario: " + categoria + "-------------------");
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
            System.out.println("--------DEBUGANDO MediaCarro--------------");
            System.out.println("Nota: " + nota);
            System.out.println("Count: " + count);
            if (count != 0)
            {
                /*Sean modificou aqui(inicio)*/
                media = nota / count;
                carro_aux.setNota((float) ((Math.round(media * 10.0)) / 10.0));
                /*Sean modificou aqui(Fim)*/
                //Original abaixo:
                //carro_aux.setNota(media);
            }
            System.out.println("Media: " + media);
            System.out.println("-----------------------------------------");
        }
    }



/*a media das avaliacoes utilizadas para o calculo do Correlation*/
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

    /*a nota dos usuarios utilizadas para o calculo do Correlation*/
        public float PegaNotaUsuarioProximo(String nome, String ID) {
        ListIterator<Clientes> iter = this.clienteList.listIterator();
        Clientes cliente = null;
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


/* Funcao para calcular a distancia euclidiana. */
    public void DistanciaEuclidiana(String nome)
            //o nome = nome do usario corrente
    {

        String nome_aux = null;
        ListIterator<Clientes> iter = this.clienteList.listIterator();
        ListIterator<Clientes> iter2 = this.clienteList.listIterator();
        Clientes usuarioCorrente = null;
        Clientes cliente2 = null;
        float dist = 0;
        String ID = null;
        String ID2 = null;
        int w = 0;


        for (int i = 0; i<clienteMediaList.size(); i++)
        {
            nome_aux = clienteMediaList.get(i).getNome();
            for (int j = 0; j<clienteList.size(); j++)
            {

                    if (clienteList.get(j).getNome().equalsIgnoreCase(nome))
                    {
                        for (int k = 0; k<clienteList.size(); k++)
                        {
                            if(clienteList.get(k).getNome().equalsIgnoreCase(nome_aux) && (clienteList.get(k).getID().equalsIgnoreCase(clienteList.get(j).getID())))
                            {
                                dist = (float)(dist + Math.pow((clienteList.get(j).getNota()/10) - (clienteList.get(k).getNota()/10), 2));
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
            if(clienteMediaList.get(i).getNome().equalsIgnoreCase(nome_aux))
            {
               return i;
            }
        }
        return -1;
    }

    public boolean SearchNameEID(String nome_aux,String ID) {
        for (int i = 0; i<clienteList.size(); i++)
        {
            if(clienteList.get(i).getNome().equalsIgnoreCase(nome_aux) &&
                    clienteList.get(i).getID().equalsIgnoreCase(ID))
            {
               return true;
            }
        }
        return false;
    }
/*
        for (int i = 0; i<clienteMediaList.size(); i++)
        {
            nome_aux = clienteMediaList.get(i).getNome();
            for (int j = 0; j<clienteList.size(); j++)
            {
                    if (clienteList.get(j).getNome().equalsIgnoreCase(nome_aux))
                    {
                        for (int k = 0; k<clienteList.size(); k++)
                        {
                            if(clienteList.get(k).getNome().equalsIgnoreCase(nome) && (clienteList.get(k).getID().equalsIgnoreCase(clienteList.get(j).getID())))
                            {
                                System.out.println("--------CALCULANDO DIST-------------------------------------");
                                System.out.println("Nome: " + clienteList.get(k).getNome());
                                System.out.println("nota1:" + (clienteList.get(j).getNota()/10));
                                System.out.println("nota2:" + (clienteList.get(k).getNota()/10));
                                System.out.println("nota1 - nota2:" + Math.pow((clienteList.get(j).getNota()/10) - (clienteList.get(k).getNota()/10), 2));
                                System.out.println("--------**************************************--------------");
                                dist = (float)(dist + Math.pow((clienteList.get(j).getNota()/10) - (clienteList.get(k).getNota()/10), 2));
                            }
                        }
                    }
            }
            dist = (float) Math.sqrt(dist);
            System.out.println("--------**************************************--------------");
            System.out.println("Nome: " + clienteMediaList.get(i).getNome());
            System.out.println("Dist:" + dist);
            System.out.println("--------**************************************--------------");
            clienteMediaList.get(i).setDist(dist);







*/
            /*
            while (iter.hasNext())
            {
                usuarioCorrente = iter.next();
                if (usuarioCorrente.getNome().equalsIgnoreCase(nome))
                {
                    iter2 = this.clienteList.listIterator();
                    while (iter2.hasNext())
                    {
                        cliente2 = iter2.next();
                        if (cliente2.getNome().equalsIgnoreCase(nome_aux) && cliente2.getID().equalsIgnoreCase(usuarioCorrente.getID()))
                        {
                           //dist = dist + ((usuarioCorrente.getNota() - cliente2.getNota())*(usuarioCorrente.getNota() - cliente2.getNota()));
                            //dist = dist + quadrado da (nota usuario - nota cliente)
                            dist = (float)(dist + Math.pow((usuarioCorrente.getNota()/10) - (cliente2.getNota()/10), 2));
                        }
                    }
                }
            }

            dist = (float) Math.sqrt(dist);
            clienteMediaList.get(i).setDist(dist);
             */
        
/* procura as 3 pessoas que estao + proximas do usuario */
    public void acha3menores ()
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
                
                /* Nada, pode apagar
                if (clienteMediaList.get(menor1).getDist() > clienteMediaList.get(i).getDist())
                {
                    System.out.println("LALALALALLALALALALALALALALLALALALALALALALLALALALALA");
                    menor3 = menor2;
                    menor2 = menor1;
                    menor1 = i;
                }
                else
                {
                    if(clienteMediaList.get(menor2).getDist() > clienteMediaList.get(i).getDist() )
                    {
                        menor3 = menor2;
                        menor2 = i;
                    }
                    else
                        if (clienteMediaList.get(menor3).getDist() > clienteMediaList.get(i).getDist() )
                            menor3 = i; 
                }
              */
            }
        }


    }

    public void CalculandoNotaPrevista(Usuario usuario){


        //Vetores para a media e as notas das 3 pessoas + proximas
        double[] vetormedia = new double[3];
        double[] vetoravaliacao = new double[3];
        double[] vetorsim = new double[3];
        ListIterator<Carros> iter = this.carrosList.listIterator();
        Carros carro = null;
        int i = 0;
        float notaUsuario = 0;
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

        DistanciaEuclidiana(usuario.getNome());
        ImprimirMediaList();
        acha3menores ();
        
        System.out.println("--------**************************************--------------");
        System.out.println("Menor1:" + clienteMediaList.get(menor1).getNome());
        System.out.println("Menor2:" + clienteMediaList.get(menor2).getNome());
        System.out.println("Menor3:" + clienteMediaList.get(menor3).getNome());
        System.out.println("--------**************************************--------------");
        
        while (iter.hasNext())
        {
            carro = iter.next();
            notaUsuario = PegaNotaUsuarioProximo(usuario.getNome(), carro.getID());
            carro.setAvaliacao(notaUsuario);
            if (carro.getAvaliacao() <= -1)
            {
                nota1 = PegaNotaUsuarioProximo(clienteMediaList.get(menor1).getNome(), carro.getID());
                nota2 = PegaNotaUsuarioProximo(clienteMediaList.get(menor2).getNome(), carro.getID());
                nota3 = PegaNotaUsuarioProximo(clienteMediaList.get(menor3).getNome(), carro.getID());
                if (nota1 == -1.0){nota1 = 0;}
                if (nota2 == -1.0){nota2 = 0;}
                if (nota3 == -1.0){nota3 = 0;}
                vetoravaliacao[0] = nota1;
                vetoravaliacao[1] = nota2;
                vetoravaliacao[2] = nota3;
                media1 = AchaMedia(clienteMediaList.get(menor1).getNome());
                media2 = AchaMedia(clienteMediaList.get(menor2).getNome());
                media3 = AchaMedia(clienteMediaList.get(menor3).getNome());
                mediaU = AchaMedia(usuario.getNome());
                vetormedia[0] = media1;
                vetormedia[1] = media2;
                vetormedia[2] = media3;
                sim1 = clienteMediaList.get(menor1).getDist();
                sim2 = clienteMediaList.get(menor1).getDist();
                sim3 = clienteMediaList.get(menor1).getDist();
                vetorsim[0] = sim1;
                vetorsim[1] = sim2;
                vetorsim[2] = sim3;
                 
                float result = (float) Correlation.previsaoAval(vetoravaliacao,vetormedia,mediaU,vetorsim);
                carro.setAvaliacaoPrev(result);

            }

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
            System.out.println("------------MEDIALIST----------------------");
            System.out.println("Nome: " + imprime.getNome());
            System.out.println("Media: " + imprime.getMedia());
            System.out.println("Dist: " + imprime.getDist());
            System.out.println("--------------------------------------------");


        }
    }

        public void ImprimirClienteList(){
        ListIterator<Clientes> iter = this.clienteList.listIterator();
        Clientes imprime = null;
        while (iter.hasNext()) {
            imprime = iter.next();
            System.out.println("------------CLIENTELIST------XXXXXXXXXXXX---");
            System.out.println("Nome: " + imprime.getNome());
            System.out.println("--------------------------------------------");


        }
    }
    /* Faz o (1-dist) para achar a distancia verdadeira */
    /*
    public void DistanciaUsuarioClientes()
    {
        ListIterator<ClienteMedia> iter = this.clienteMediaList.listIterator();
        ClienteMedia cliente2 = null;
        while (iter.hasNext())
        {
            cliente2 = iter.next();
            cliente2.setDist(Math.abs(1 - cliente2.getDist()));
        }
    }
    */
}

