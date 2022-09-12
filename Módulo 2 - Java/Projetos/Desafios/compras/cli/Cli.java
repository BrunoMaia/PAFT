package cli;

import businessLogic.BusinessLogic;
import businessLogic.system.Compra;
import businessLogic.system.Produto;
import businessLogic.system.Usuario;

import java.util.List;
import java.util.Scanner;

public class Cli {
    private Usuario usuarioLogado;
    private static final BusinessLogic BL = new BusinessLogic();
    private static final Scanner SC = new Scanner(System.in);

    private void exibeBoasVindas(){
        System.out.println("**********************************************************");
        System.out.println("*                                                        *");
        System.out.println("*                BEM VINDO A LOJA!                       *");
        System.out.println("*                                                        *");
        System.out.println("**********************************************************");
    }

    private Usuario fazLogin(){
        System.out.print("Digite seu CPF: ");
        String login = SC.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = SC.nextLine();
        return new BusinessLogic().logarUsuario(login,senha);
    }

    private int leOpcao(){
        int retorno = 0;
        String opcao = "";
        opcao = SC.nextLine();
        if (opcao == null || opcao.isEmpty()) exibeMenu(usuarioLogado.isAdmin());
        try {
            retorno = Integer.parseInt(opcao);
        }catch (NumberFormatException erro){
            System.out.println("Opcao nao encontrada, digite novamente");
            exibeMenu(usuarioLogado.isAdmin());
        }
        if (retorno > 6 || retorno < 1){
            System.out.println("Opcao nao encontrada, digite novamente");
            exibeMenu(usuarioLogado.isAdmin());
        }
        return retorno;
    }

    private int exibeMenu(boolean admin){
        int retorno = 0;
        String menu = """
                **********************************************************
                *                      Menu                              *
                *                                                        *
                """;
        if (admin){
            menu += """
                    *  [1] Compras      [2] Relatorio   [3] Trocar usuario   *
                    *  [4] Estoque      [5] Sobre       [6] Sair             *
                    """;
        }
        else {
            menu += """
                    *  [1] Comprar      [3] Trocar usuario   [5] Sobre       *
                    *  [6] Sair                                              *
                    """;
        }
        menu += "*                                                        *\n" +
                "**********************************************************";
        System.out.println(menu);

        int opcao = leOpcao();

        if (!admin && (opcao == 2 || opcao == 4)){
            System.out.println("Opcao nao encontrada, digite novamente!");
            exibeMenu(false);
        }
        return opcao;
    }

    private void exibeSobre(){
        System.out.println("**********************************************************");
        System.out.println("* Criado por: Bruno Maia                                 *");
        System.out.println("* GitHub: github.com/BrunoMaia/PAFT                      *");
        System.out.println("* Ano: 2022                                              *");
        System.out.println("**********************************************************");
    }

    private void exibeCompras(){
        System.out.println("*********************************************************************");
        System.out.println("* [1] Buscar produto [2] Listar produtos  [3] Adicionar ao carrinho *");
        System.out.println("* [4] Exibir carrinho                     [5] Voltar ao menu        *");
        System.out.println("*********************************************************************");
        int opcao = leOpcao();
        switch (opcao){
            case 1:
                exibeBuscaProduto();
                break;
            case 2:
                System.out.println("***************** Produtos cadastrados **************************");
                for (Produto produto : BL.getProdutos()){
                    if (produto.getEstoque() > 0){
                        System.out.printf("* |%06d| %-25s Estoque: %03d Preco: R$%.2f *%n",produto.getCodigo(),
                                produto.getNome(),produto.getEstoque(),produto.getPreco());
                    }
                }
                System.out.println("*****************************************************************");
                break;
            case 3:
                exibeAdicionarAoCarrinho();
                break;
            case 4:
                System.out.println("***************** Carrinho de compras ***************************");
                System.out.println(usuarioLogado.imprimeCarrinho());
                System.out.printf("Valor total do carrinho: R$ %.2f%n",usuarioLogado.getValorCarrinho());
                System.out.print("Deseja finalizar a compra s/n: ");
                if (SC.nextLine().contains("s")){
                    BL.finalizarCompra(usuarioLogado);
                }
                break;
            case 5:
                exibeMenu(usuarioLogado.isAdmin());
                break;

        }
    }

    private void exibeAdicionarAoCarrinho(){
        System.out.println("******************** Adicionar ao carrinho **********************");
        int item = -1;
        int quantidade = -1;
        try {
            System.out.print("Digite qual o codigo do item: ");
            item = Integer.parseInt(SC.nextLine());
            System.out.print("Digite quantos itens deseja adicionar: ");
            quantidade = Integer.parseInt(SC.nextLine());
        }catch (NumberFormatException erro){
            System.out.println("Voce digitou dados que nao sao validos, tente novamente");
            exibeAdicionarAoCarrinho();
        }
        if(BL.adicionarAoCarrinho(item,quantidade, usuarioLogado)){
            System.out.println("Produto adicionado com sucesso!");
            exibeCompras();
        }else {
            System.out.println("Nao foi possivel adicionar o produdo, ele existe?");
            exibeAdicionarAoCarrinho();
        }
    }

    private void exibeRelatorio(){
        System.out.println("******************** Relatorio de compras ***********************");
        System.out.printf("%-20s | Qtd. Compras | Vlr. Tot | Vlr. Medio%n","Nome cliente");
        System.out.println("-----------------------------------------------------------------");
        System.out.println(BL.relatorioCompras());

    }

    private void exibeTrocarUsuario(){
        usuarioLogado = null;
        executarCli();

    }

    private void exibeEstoque(){

    }

    private void exibeBuscaProduto(){
        System.out.println("********************** Busca de produto *************************");
        System.out.print("Digite o nome do produto: ");
        List<Produto> produtosLocalizados = BL.buscarProduto(SC.nextLine());
        if (!produtosLocalizados.isEmpty()){
            System.out.println("Foram encontrados os seguintes produtos: ");
            for (Produto produto : produtosLocalizados){
                System.out.printf("* |%06d| %-25s Estoque: %03d Preco: R$%.2f *%n",produto.getCodigo(),
                        produto.getNome(),produto.getEstoque(),produto.getPreco());
            }
        }else{
            System.out.println("Nao foram encontrados produtos =[");
        }
        exibeCompras();

    }


    public void executarCli(){
        exibeBoasVindas();
        do {
            usuarioLogado = fazLogin();
        }while (usuarioLogado == null);
        while (true) {
            int retorno = exibeMenu(usuarioLogado.isAdmin());
            switch (retorno) {
                case 1 -> exibeCompras();
                case 2 -> exibeRelatorio();
                case 3 -> exibeTrocarUsuario();
                case 4 -> exibeEstoque();
                case 5 -> exibeSobre();
                case 6 -> System.exit(0);
            }
        }


    }
}
