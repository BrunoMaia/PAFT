import java.util.Arrays;
import java.util.Scanner;

public class Aula01 {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Digite um número");
        int numero_entrada = Integer.parseInt(in.nextLine());
        int [] valores_entrada = new int[numero_entrada];
        int valor_menor = Integer.MIN_VALUE;
        int valor_maior = Integer.MAX_VALUE;
        System.out.println("Digite os valores");
        for (int i=0; i < numero_entrada; i++){
            int entrada = Integer.parseInt(in.nextLine());
            valores_entrada[i] = entrada;
            if (i == 0){
                valor_maior = entrada;
                valor_menor = entrada;
            }
            else if (entrada > valor_maior){
                valor_maior = entrada;
            }
            else if (entrada < valor_menor){
                valor_menor = entrada;
            }
        }
        System.out.println("O valor maior é:\n" + String.valueOf(valor_maior) +
                "\nO valor menor é:\n" + String.valueOf(valor_menor));
    }
}
