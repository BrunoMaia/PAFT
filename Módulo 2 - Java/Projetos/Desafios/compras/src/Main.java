import businessLogic.Usuario;
import cli.Cli;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Usuario usuario_logado = null;
        Cli.exibeBoasVindas();
        do {
            usuario_logado = Cli.fazLogin();
        }while (usuario_logado == null);
        int opcao = Cli.exibeMenu();
        if (opcao == 5){
            Cli.exibeSobre();
            opcao = Cli.exibeMenu();
        }
    }
}