package pucpr.semaforo;

import java.util.Scanner;

public class controle {
    public static void main(String[] args) {
        final String VERDE = "Verde";
        final String AMARELO = "Amarelo";
        final String VERMELHO = "Vermelho";

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite a cor inicial do semaforo. Opç�es: Verde, Vermelho, Amarelo");
        String corLeitura = sc.nextLine();
        semaforo sf = new semaforo(corLeitura);
        System.out.printf("Cor do semaforo: %s%n",sf.getTextoCor());
        for (int i = 0; i < 10; i++){
            sf.proximaCor();
            System.out.printf("Cor do semaforo: %s%n",sf.getTextoCor());
        }
        sf.setCor(AMARELO);
        System.out.printf("Cor do semaforo: %s%n",sf.getTextoCor());
    }
}
