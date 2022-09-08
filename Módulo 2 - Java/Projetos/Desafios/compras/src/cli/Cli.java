package cli;

import businessLogic.BusinessLogic;
import businessLogic.Usuario;

import java.util.Scanner;

public class Cli {
    private static final Scanner SC = new Scanner(System.in);
    private Usuario usuario_logado;

    public static void exibeBoasVindas(){
        System.out.println("**********************************************************");
        System.out.println("*                                                        *");
        System.out.println("*                BEM VINDO A LOJA!                       *");
        System.out.println("*                                                        *");
        System.out.println("**********************************************************");
    }

    public static Usuario fazLogin(){
        System.out.print("Digite seu CPF: ");
        String login = SC.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = SC.nextLine();
        Usuario usuario = new BusinessLogic().logarUsuario(login,senha);
        return usuario;
    }

    public static int exibeMenu(){
        int retorno = 0;
        System.out.println("**********************************************************");
        System.out.println("*                      Menu                              *");
        System.out.println("*                                                        *");
        System.out.println("*  [1] Comprar      [2] Relatorio   [3] Trocar usuario   *");
        System.out.println("*  [4] Estoque      [5] Sobre       [6] Sair             *");
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

        if (retorno > 6 || retorno < 1){
            System.out.println("Opcao nao encontrada, digite novamente");
            exibeMenu();
        }
        return retorno;
    }

    public static void exibeSobre(){
        System.out.println("**********************************************************");
        System.out.println("* Criado por: Bruno Maia                                 *");
        System.out.println("* GitHub: github.com/BrunoMaia/PAFT                      *");
        System.out.println("* Ano: 2022                                              *");
        System.out.println("**********************************************************");
    }
}
