/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalhoia;

import java.io.IOException;


/**
 *
 * @author Troy
 */
public class Main {

    static SistemaRec recomendando;
    static Usuario usuario = new Usuario();
    static float avaliacao_grupo = 0;


    public static void main(String[] args) throws IOException {
        final View tela = new View();
        recomendando = new SistemaRec(tela);
        tela.setRecomendando(recomendando);
        recomendando.ProcessaArquivoCarro();
        recomendando.ProcessaArquivoCliente();
        tela.setUsuario(usuario);

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                tela.setVisible(true);
            }
        });

    }
}
