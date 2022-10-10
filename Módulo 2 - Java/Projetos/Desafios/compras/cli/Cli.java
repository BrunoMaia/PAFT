package cli;

import businessLogic.BusinessLogic;
import businessLogic.system.*;

import java.util.*;

public class Cli {
    private Usuario usuarioLogado;
    private static final BusinessLogic BL = new BusinessLogic();
    private static final Scanner SC = new Scanner(System.in);
    private final String separador;
    private final int tamanhoCLI;

    public Cli(int tamanho, char separador){
        this.separador = String.valueOf(separador);
        this.tamanhoCLI = Math.max(tamanho,25);
    }

    public Cli(){
        this(60,'*');
    }

    private void exibeBoasVindas(){
        String titulo = "|" + " ".repeat((tamanhoCLI-22)/2)+"BEM VINDO A TABERNA!"+ " ".repeat((tamanhoCLI-22)/2)+"|";
    String espacador = "|" + " ".repeat(tamanhoCLI-2) + "|";
        String retorno = String.format("%s%n%s%n%s%n%s%n%s%n",separador.repeat(tamanhoCLI),espacador,titulo,espacador,separador.repeat(tamanhoCLI));
        System.out.print(retorno);
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
                System.out.print("Digite o nome do novo usuario: ");
                String nomeNovoUsuario = leString();
                return BL.cadastrarUsuario(login,senha,nomeNovoUsuario);
            }
        }
        return usuario;
    }

    private int leOpcao(int maximo){
        int retorno = 0;
        String opcao = "";
        System.out.print("\nDigite a opcao: ");
        opcao = SC.nextLine();
        if (opcao == null || opcao.isEmpty()) exibeMenu(usuarioLogado.isAdmin());
        try {
            assert opcao != null;
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

    private String geraMenu(String titulo, String opcoes){
        return String.format("""
                %n%s
                    
                %s
                
                %s
                """,geraCabecalho(titulo),opcoes,separador.repeat(tamanhoCLI));
    }

    private String geraCabecalho(String titulo){
        int tamanhoEspador = (tamanhoCLI - titulo.length()-2)/2;
        return String.format("%s %s %s",separador.repeat(tamanhoEspador),titulo,separador.repeat(tamanhoEspador));
    }
    private int exibeMenu(boolean admin){
        String menu;
        if (admin){
            menu = geraMenu("Menu", """
                    [1] Compras      [2] Relatorio   [3] Trocar usuario
                    [4] Estoque      [5] Sobre       [6] Sair""");
        }
        else {
            menu = geraMenu("Menu", """
                    [1] Comprar      [3] Trocar usuario   [5] Sobre
                    [6] Sair""");
        }
        System.out.print(menu);

        int opcao = leOpcao(6);

        if (!admin && (opcao == 2 || opcao == 4)){
            System.out.println("Opcao nao encontrada, digite novamente!");
            exibeMenu(false);
        }
        return opcao;
    }

    private void exibeSobre(){
        System.out.println(geraMenu("Sobre", """
                Criado por: Bruno Maia
                GitHub: github.com/BrunoMaia/PAFT
                Ano: 2022
                V2.0"""));
    }

    private void exibeCompras(){
        System.out.print(geraMenu("Menu de compra","""
                [1] Buscar produto [2] Listar produtos  [3] Adicionar ao carrinho
                [4] Exibir carrinho                     [5] Voltar ao menu"""));
        int opcao = leOpcao(5);
        switch (opcao) {
            case 1 -> exibeBuscaProduto();
            case 2 -> {
                StringBuilder relatorio = new StringBuilder();
                for (Map.Entry<Integer,Produto> entrada: BL.getProdutos().entrySet()) {
                    Produto produto = entrada.getValue();
                    if (entrada.getValue().getEstoque() > 0) {
                        relatorio.append(String.format("|%06d| %-25s Estoque: %03d Preco: R$%.2f%n", entrada.getKey(),
                                produto.getNome(), produto.getEstoque(), produto.getPreco()));
                    }
                }
                System.out.println(geraMenu("Lista de produtos ",relatorio.toString()));
            }
            case 3 -> exibeAdicionarAoCarrinho();
            case 4 -> {
                System.out.println(geraMenu("Carrinho de compras ",usuarioLogado.imprimeCarrinho()));
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
        System.out.println(geraCabecalho("Adicionar ao carrinho"));
        int item = 0;
        int quantidade = 0;
        try {
            System.out.print("Digite qual o codigo do item: ");
            item = Math.max(Integer.parseInt(SC.nextLine()),0);
            System.out.print("Digite quantos itens deseja adicionar: ");
            quantidade = Math.max(Integer.parseInt(SC.nextLine()),0);
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
        System.out.println(geraMenu("Relatorio de compras", String.format("""
                        Nome cliente         | Qtd. Compras | Vlr. Tot | Vlr. Medio
                        
                        %s
                        """,BL.relatorioCompras())));
    }

    private void exibeTrocarUsuario(){
        usuarioLogado = null;
        executarCli();

    }

    private void exibeEstoque(){
        System.out.println(geraMenu("Menu de estoque","""
                [1] Adicionar produto [2] Remover produto [3] Alterar produto
                [4] Listar todos os produtos"""));
        int opcao = leOpcao(4);
        switch (opcao){
            case 1 -> adicionaProduto();
            case 2 -> removeProduto();
            case 3 -> alteraProduto();
            case 4 ->{for (Map.Entry<Integer,Produto> entrada : BL.buscarProduto(false).entrySet()) {
                Produto produto = entrada.getValue();
                System.out.printf("|%06d| %-25s Estoque: %03d Preco: R$%.2f%n", entrada.getKey(),
                        produto.getNome(), produto.getEstoque(), produto.getPreco());
                }
            }
        }
    }

    private void adicionaProduto() {
        System.out.println(geraCabecalho("Adicionar produto "));
        System.out.print("Digite o codigo do produto a ser adicionado: ");
        int codigo = leInt();
        System.out.print("Digite o nome do produto: ");
        String nomeProduto = leString();
        if(BL.adicionaProduto(codigo, nomeProduto)){
            System.out.println("Produto adicionado!");
            for (Map.Entry<Integer,Produto> entrada : BL.buscarProduto(false).entrySet()) {
                Produto produto = entrada.getValue();
                System.out.printf("|%06d| %-25s Estoque: %03d Preco: R$%.2f%n", entrada.getKey(),
                        produto.getNome(), produto.getEstoque(), produto.getPreco());
            }
        }else {
            System.out.println("Houve um erro, tente novamente, o codigo ja existe?");
            adicionaProduto();
        }
    }

    private void removeProduto() {
        System.out.println(geraCabecalho("Remover produto "));
        System.out.print("Digite o codigo do produto a ser REMOVIDO: ");
        int codigo = leInt();
        if(BL.removeProduto(codigo)){
            System.out.println("Produto removido!");
        }else {
            System.out.println("Produto nao foi removido, ele esta presente em uma compra!");
            adicionaProduto();
        }
    }

    private void alteraProduto() {
        System.out.println(geraMenu("Alterar produto ","[1] Alterar nome [2] Alterar preco [3] Alterar quantidade"));
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
        System.out.println(geraCabecalho("Busca de produto"));
        System.out.print("Digite o nome do produto: ");
        Map<Integer,Produto> produtosLocalizados = BL.buscarProduto(SC.nextLine(),true);
        if (!produtosLocalizados.isEmpty()){
            System.out.println("Foram encontrados os seguintes produtos: ");
            for (Map.Entry<Integer,Produto> entrada : produtosLocalizados.entrySet()) {
                Produto produto = entrada.getValue();
                System.out.printf("|%06d| %-25s Estoque: %03d Preco: R$%.2f%n", entrada.getKey(),
                        produto.getNome(), produto.getEstoque(), produto.getPreco());
            }
        }else{
            System.out.println("Nao foram encontrados produtos =[");
        }
        exibeCompras();

    }


    public void executarCli(){
        try {
            exibeBoasVindas();
            do {
                usuarioLogado = fazLogin();
            } while (usuarioLogado == null);
            while (true) {
                int retorno = exibeMenu(usuarioLogado.isAdmin());
                switch (retorno) {
                    case 1 -> exibeCompras();
                    case 2 -> exibeRelatorio();
                    case 3 -> exibeTrocarUsuario();
                    case 4 -> exibeEstoque();
                    case 5 -> exibeSobre();
                    case 6 -> {
                        BL.salvarDados();
                        System.exit(0);
                    }
                }
            }
        } catch (Exception e) {
            System.out.printf("Ocorreu um erro na execucao da CLI:%n%s%n%s",e.toString(),Arrays.toString(e.getStackTrace()));
            BL.salvarDados();
            System.exit(1);
        }

    }
}
