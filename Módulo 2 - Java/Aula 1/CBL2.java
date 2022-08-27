import java.util.Arrays;
import java.util.Scanner;

public class Aula01 {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Digite um número com quantos valores quer ler");
        int numero_entrada = Integer.parseInt(in.nextLine());
        int [] valores_entrada = new int[numero_entrada];
        System.out.println("Digite os valores");
        for (int i=0; i < numero_entrada; i++){
            int entrada = Integer.parseInt(in.nextLine());
            valores_entrada[i] = entrada;
        }
        System.out.println(localizarExtremos(valores_entrada));
    }
    static String localizarExtremos(int[] valores) {
        int valor_menor = Integer.MIN_VALUE;
        int valor_maior = Integer.MAX_VALUE;
        for (int i=0; i < valores.length; i++) {
            if (i == 0) {
                valor_maior = valores[i];
                valor_menor = valores[i];
            } else if (valores[i] > valor_maior) {
                valor_maior = valores[i];
            } else if (valores[i] < valor_menor) {
                valor_menor = valores[i];
            }
        }
        String mensagem = String.format("O valor maior é: %d, o valor menor é: %d",valor_maior,valor_menor);
        return  mensagem;
    }
}
