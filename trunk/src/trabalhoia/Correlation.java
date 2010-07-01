package trabalhoia;

import java.io.*;

public class Correlation {
    public static double getPersonCorrelation(double[] scores1, double[] scores2){
        double result = 0;
        double sum_sq_x = 0;
        double sum_sq_y = 0;
        double sum_coproduct = 0;
        double mean_x = scores1[0];
        double mean_y = scores2[0];

        for(int i=2;i<scores1.length+1;i+=1){
            double sweep =Double.valueOf(i-1)/i;
            double delta_x = scores1[i-1]-mean_x;
            double delta_y = scores2[i-1]-mean_y;
            sum_sq_x += delta_x * delta_x * sweep;
            sum_sq_y += delta_y * delta_y * sweep;
            sum_coproduct += delta_x * delta_y * sweep;
            mean_x += delta_x / i;
            mean_y += delta_y / i;
        }
        
        double pop_sd_x = (double) Math.sqrt(sum_sq_x/scores1.length);
        double pop_sd_y = (double) Math.sqrt(sum_sq_y/scores1.length);
        double cov_x_y = sum_coproduct / scores1.length;
        result = cov_x_y / (pop_sd_x*pop_sd_y);
        return result;
    }
	/* Recebe 1 - vetor de tamanho 3 contendo: as avaliacoes dos usuarios mais proximos ao usuario Corrente
		  2 - vetor de tamanho 3 contendo: as medias das avaliacoes dadas pelo usuarios mais proximos
	          3 - a media das avaliacoes do Usuario Corrente
		  4 - vetor de tamanho 3 contendo a similaridade entre os usuarios e o usuario Corrente */

	public static double previsaoAval(double[] avalUsuarios,double[] avalMediaUsuarios,double mediaUsuarioCorr, double[] similUsuarios){
		int i;
		double difAlvo, somaDif=0,somaSimil=0;
                for(i=0;i<3;i++){
			difAlvo=avalUsuarios[i]-avalMediaUsuarios[i];
                        difAlvo=difAlvo*similUsuarios[i];
			somaDif=somaDif+difAlvo;
			somaSimil=somaSimil+similUsuarios[i];
                }
		/*Sean modificou aqui(inicio)*/
                double retorno = (mediaUsuarioCorr + (somaDif/somaSimil));
                if(retorno<0){retorno=-1;}
                retorno = ((float) ((Math.round(retorno * 10.0)) / 10.0));
                return retorno;
                /*Sean modificou aqui(Fim)*/
                //Original abaixo:
                //return mediaUsuarioCorr + (somaDif/somaSimil);
        }
}
