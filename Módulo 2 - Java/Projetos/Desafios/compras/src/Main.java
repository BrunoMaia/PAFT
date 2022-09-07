import cli.Cli;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean logado = false;
        Cli.exibeBoasVindas();
        do {
            logado = Cli.fazLogin();
        }while (logado != true);
        int opcao = Cli.exibeMenu();
        if (opcao == 4){
            Cli.exibeSobre();
            opcao = Cli.exibeMenu();
        }
    }
}