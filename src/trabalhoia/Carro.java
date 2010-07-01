package trabalhoia;

public class Carro implements Comparable {

    private String marca = null;
    private String modelo = null;
    private int portas = 0;
    private String combustivel = null;
    private String cor = null;
    private int preco = 0;
    private int ano = 0;
    private String categoria = null;
    private String cambio = null;
    private String id = null;
    private int afinidade = 0;
    private float nota = 0;
    private float avaliacao = -1;
    private float avaliacaoprev = -1;
      
    public int compareTo(Object o) {
        Carro carro = (Carro)o;
        return carro.afinidade - this.afinidade;
    }

    public String getMarca () {
    return marca;
    }

    public void setMarca (String val) {
    this.marca = val;
    }


    public String getModelo () {
    return modelo;
    }

    public void setModelo (String val) {
    this.modelo = val;
    }


    public int getPortas () {
    return portas;
    }

    public void setPortas (int val) {
    this.portas = val;
    }


    public String getCombustivel () {
    return combustivel;
    }

    public void setCombustivel (String val) {
    this.combustivel = val;
    }


    public String getCor () {
    return cor;
    }

    public void setCor (String val) {
    this.cor = val;
    }


    public int getPreco () {
    return preco;
    }

    public void setPreco (int val) {
    this.preco = val;
    }


    public int getAno () {
    return ano;
    }

    public void setAno (int val) {
    this.ano = val;
    }

    public String getCategoria () {
    return categoria;
    }

    public void setCategoria (String val) {
    this.categoria = val;
    }

    public String getCambio () {
    return cambio;
    }

    public void setCambio (String val) {
    this.cambio = val;
    }

    public String getID () {
    return id;
    }

    public void setID (String val) {
    this.id = val;
    }

    public int getAfinidade () {
    return afinidade;
    }

    public void setAfinidade (int val) {
    this.afinidade = val;
    }

    public float getNota () {
    return nota;
    }

    public void setNota (float val) {
    this.nota = val;
    }

    public float getAvaliacao () {
    return avaliacao;
    }

    public void setAvaliacao (float val) {
    this.avaliacao = val;
    }

    public float getAvaliacaoPrev () {
    return avaliacaoprev;
    }

    public void setAvaliacaoPrev (float val) {
    this.avaliacaoprev = val;
    }
}
