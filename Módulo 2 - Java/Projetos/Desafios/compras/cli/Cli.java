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
        Usuario usuario = new BusinessLogic().logarUsuario(login,senha);
        if (usuario == null){
            System.out.print("Usuario nao cadastrado, deseja cadastrar (s/n)? ");
            boolean cadastrar = leString().contains("s");
            if (cadastrar){
                System.out.print("Digite o cpf do novo usuario: ");
                String cpfNovoUsuario = leString();
                if (cpfNovoUsuario.contains("admin")){
                    System.out.println("Usuario invalido");
                    return null;
                }
                System.out.print("Digite a senha do novo usuario: ");
                String senhaNovoUsuario = leString();
                System.out.print("Digite o nome do novo usuario: ");
                String nomeNovoUsuario = leString();
                return BL.cadastrarUsuario(cpfNovoUsuario,senhaNovoUsuario,nomeNovoUsuario);
            }
        }
        return usuario;
    }

    private int leOpcao(int maximo){
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
        if (retorno > maximo || retorno < 1){
            System.out.println("Opcao nao encontrada, digite novamente");
            exibeMenu(usuarioLogado.isAdmin());
        }
        return retorno;
    }

    private int leInt(){
        int retorno = 0;
        String opcao = "";
        opcao = SC.nextLine();
        if (opcao == null || opcao.isEmpty()) return 0;
        try {
            retorno = Integer.parseInt(opcao);
        }catch (NumberFormatException erro){
            return 0;
        }
        return Math.max(retorno, 0);
    }

    private String leString(){
        String retorno = SC.nextLine();
        if (retorno == null || retorno.isEmpty()) return "";
        return retorno;
    }

    private Double leDouble(){
        double retorno;
        String leitura = SC.nextLine();
        if (leitura == null || leitura.isEmpty()) return 0.0;
        try {
            retorno = Double.parseDouble(leitura);
        }catch (NumberFormatException erro){
            return 0.0;
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

        int opcao = leOpcao(6);

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
        System.out.println("""
                *********************************************************************
                * [1] Buscar produto [2] Listar produtos  [3] Adicionar ao carrinho *
                * [4] Exibir carrinho                     [5] Voltar ao menu        *
                *********************************************************************
                """);
        int opcao = leOpcao(5);
        switch (opcao) {
            case 1 -> exibeBuscaProduto();
            case 2 -> {
                System.out.println("***************** Produtos cadastrados **************************");
                for (Produto produto : BL.getProdutos()) {
                    if (produto.getEstoque() > 0) {
                        System.out.printf("* |%06d| %-25s Estoque: %03d Preco: R$%.2f *%n", produto.getCodigo(),
                                produto.getNome(), produto.getEstoque(), produto.getPreco());
                    }
                }
                System.out.println("*****************************************************************");
            }
            case 3 -> exibeAdicionarAoCarrinho();
            case 4 -> {
                System.out.println("***************** Carrinho de compras ***************************");
                System.out.println(usuarioLogado.imprimeCarrinho());
                System.out.printf("Valor total do carrinho: R$ %.2f%n", usuarioLogado.getValorCarrinho());
                System.out.print("Deseja finalizar a compra s/n: ");
                if (SC.nextLine().contains("s")) {
                    BL.finalizarCompra(usuarioLogado);
                }
            }
            case 5 -> exibeMenu(usuarioLogado.isAdmin());
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
        System.out.println("""
                ******************* Gestao de estoque ***************************
                * [1] Adicionar produto [2] Remover produto [3] Alterar produto *
                * [4] Listar todos os produtos                                  *
                *****************************************************************
                """);
        int opcao = leOpcao(4);
        switch (opcao){
            case 1 -> adicionaProduto();
            case 2 -> removeProduto();
            case 3 -> alteraProduto();
            case 4 ->{for (Produto produto : BL.buscarProduto("",false)) {
                System.out.printf("* |%06d| %-25s Estoque: %03d Preco: R$%.2f *%n", produto.getCodigo(),
                        produto.getNome(), produto.getEstoque(), produto.getPreco());
                }
            }
        }
    }

    private void adicionaProduto() {
        System.out.println("******************* Gestao de estoque ***************************");
        System.out.print("* Digite o codigo do produto a ser adicionado: ");
        int codigo = leInt();
        System.out.print("* Digite o nome do produto: ");
        String nomeProduto = leString();
        if(BL.adicionaProduto(codigo, nomeProduto)){
            System.out.println("Produto adicionado!\n*********************************");
            for (Produto produto : BL.buscarProduto("",false)){
                System.out.printf("* |%06d| %-25s Estoque: %03d Preco: R$%.2f *%n",produto.getCodigo(),
                        produto.getNome(),produto.getEstoque(),produto.getPreco());
            }
        }else {
            System.out.println("Houve um erro, tente novamente, o codigo ja existe?");
            adicionaProduto();
        }
    }

    private void removeProduto() {
        System.out.println("******************* Gestao de estoque ***************************");
        System.out.print("* Digite o codigo do produto a ser REMOVIDO: ");
        int codigo = leInt();
        if(BL.removeProduto(codigo)){
            System.out.println("Produto removido!");
        }else {
            System.out.println("Produto nao foi removido, ele esta presente em uma compra!");
            adicionaProduto();
        }
    }

    private void alteraProduto() {
        System.out.println("""
                ******************* Gestao de estoque ***************************
                * [1] Alterar nome [2] Alterar preco [3] Alterar quantidade     *
                *****************************************************************
                """);
        int opcao = leOpcao(3);
        System.out.print("Digite o codigo do produto: ");
        int codigo = leInt();
        switch (opcao){
            case 1 ->{
                System.out.print("Digite o novo nome do produto: ");
                String novoNome = leString();
                BL.renomeiaProduto(codigo, novoNome);
            }
            case 2 ->{
                System.out.print("Digite o novo preco do produto: ");
                double novoPreco = leDouble();
                BL.alteraPrecoProduto(codigo, novoPreco);
            }
            case 3 ->{
                System.out.print("Digite o novo estoque do produto: ");
                int novoEstoque = leInt();
                BL.alteraEstoqueProduto(codigo, novoEstoque);
            }
        }

    }

    private void exibeBuscaProduto(){
        System.out.println("********************** Busca de produto *************************");
        System.out.print("Digite o nome do produto: ");
        List<Produto> produtosLocalizados = BL.buscarProduto(SC.nextLine(),true);
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
