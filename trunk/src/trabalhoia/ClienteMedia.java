/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalhoia;

/**
 *
 * @author Rondon
 */
public class ClienteMedia
{
    private String nome = null;
    private float media = 0;
    private float dist = 0;

  public String getNome () {
    return nome;
    }

    public void setNome (String val) {
    this.nome = val;
    }


    public float getMedia()
    {
        return media;
    }

    public void setMedia(float dist)
    {
        this.media = dist;
    }

    public float getDist()
    {
        return dist;
    }

    public void setDist(float dist)
    {
        this.dist = dist;
    }
}
