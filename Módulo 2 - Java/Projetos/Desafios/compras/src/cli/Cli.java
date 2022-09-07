package cli;

import java.util.Scanner;

public class Cli {
    private static final Scanner SC = new Scanner(System.in);

    public static void exibeBoasVindas(){
        System.out.println("**********************************************************");
        System.out.println("*                                                        *");
        System.out.println("*                BEM VINDO A LOJA!                       *");
        System.out.println("*                                                        *");
        System.out.println("**********************************************************");
    }

    public static boolean fazLogin(){
        System.out.print("Digite seu CPF: ");
        String login = SC.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = SC.nextLine();
        if (login.equals("admin") && login.equals("admin")) return true;
        else return false;
    }

    public static int exibeMenu(){
        int retorno = 0;
        System.out.println("**********************************************************");
        System.out.println("*                      Menu                              *");
        System.out.println("*                                                        *");
        System.out.println("*  [1] Comprar      [2] Relatorio   [3] Trocar usuario   *");
        System.out.println("*  [4] Sobre        [5] Sair                             *");
        System.out.println("*                                                        *");
        System.out.println("**********************************************************");
        String opcao = SC.nextLine();
        if (opcao == null || opcao.isEmpty()) exibeMenu();
        try {
            retorno = Integer.parseInt(opcao);
        }catch (NumberFormatException erro){
            System.out.println("Opcao nao encontrada, digite novamente");
            exibeMenu();
        }
        return retorno;
    }
}
