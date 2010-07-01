package trabalhoia;

public class Clientes {
    
    private String nome = null;
    private String categoria = null;
    private String id = null;
    private float nota = 0;
    private float media = 0;
    

    public Clientes(){}
    

    public String getNome () {
    return nome;
    }

    public void setNome (String val) {
    this.nome = val;
    }


    public String getCategoria () {
    return categoria;
    }

    public void setCategoria (String val) {
    this.categoria = val;
    }


    public String getID () {
    return id;
    }

    public void setID (String val) {
    this.id = val;
    }


    public float getNota () {
    return nota;
    }

    public void setNota (float val) {
    this.nota = val;
    }
}
