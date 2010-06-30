package trabalhoia;

public class Usuario {

    private String nomeusuario = null;
    private String marca = null;
    private String modelo = null;
    private int portas = -1;
    private String combustivel = null;
    private String cor = null;
    private int precomin = -1;
    private int precomax = 999999;
    private int ano = -1;
    private String categoria = null;
    private String cambio = null;

    /*
    portas - 2/4
    Categoria - Conversivel/monovolume/van/esportivo/picapes/wagon/hatch/sedan ?
    combustivel - alcool/gasolina/gnv/diesel
    Cambio - Manual/Automatico
     */

    public Usuario()
    {

    }


    public String getNome () {
    return nomeusuario;
    }

    public void setNome (String val) {
    this.nomeusuario = val;
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


    public int getPrecomin () {
    return precomin;
    }

    public void setPrecomin (int val) {
    this.precomin = val;
    }

    
    public int getPrecomax () {
    return precomax;
    }

    public void setPrecomax (int val) {
    this.precomax = val;
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

}
