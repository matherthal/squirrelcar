package trabalhoia;

import java.io.IOException;

public class Main {

    static Recomendacao recomendando;
    static Interesse interesse = new Interesse();
    static float avaliacao_grupo = 0;

    public static void main(String[] args) throws IOException {
        final View tela = new View();
        recomendando = new Recomendacao(tela);
        tela.setRecomendando(recomendando);
        recomendando.ProcessaArquivoCarro();
        recomendando.ProcessaArquivoCliente();
        tela.setUsuario(interesse);

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                tela.setVisible(true);
            }
        });

    }
}
