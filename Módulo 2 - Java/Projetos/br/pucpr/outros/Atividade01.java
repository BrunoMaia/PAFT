package pucpr.outros;

import java.util.Scanner;

public class Atividade01 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Relogio relogio1 = new Relogio();
        Relogio relogio2 = new Relogio();
        relogio1.nome = "Relógio 1";
        relogio1.horas = 15;
        relogio1.minutos = 59;
        relogio2.nome = "Relógio 2";
        relogio2.horas = 23;
        relogio2.minutos = 58;

        System.out.println("Digite quantas vezes quer rodar o loop");
        int n_loop = Integer.parseInt(in.nextLine());
        for (int i = 0;i < n_loop; i++){
            System.out.printf("%s %s%n",relogio1.proximoMinuto(),relogio2.proximoMinuto());
        }
    }
}
