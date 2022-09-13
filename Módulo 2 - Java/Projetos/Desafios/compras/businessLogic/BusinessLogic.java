package businessLogic;

import businessLogic.system.Arquivo;
import businessLogic.system.Compra;
import businessLogic.system.Produto;
import businessLogic.system.Usuario;

import java.util.ArrayList;
import java.util.List;

public class BusinessLogic {
    private List<Usuario> usuarios = new Arquivo().carregaUsuarios("usuarios.csv");
    private List<Produto> produtosCadastrados = new Arquivo().carregaProdutos("produtos.csv");
    private double valorVendas = 0.0;
    public static final int TAMANHO_CPF = 11;

    public Usuario logarUsuario(String nome, String senha){
        if (nome == null || senha == null || nome.isEmpty() || senha.isEmpty()){
            throw new IllegalArgumentException("Deve-se passar corretamento os valores de usuario e senha");
        }
        nome = nome.substring(0,Math.min(TAMANHO_CPF,nome.length()));
        if (nome.equals("admin") && senha.equals("admin")) return new Usuario(nome, senha, true);
        Usuario usuarioTeste = new Usuario(nome,senha);
        if(usuarios.contains(usuarioTeste)){
            return usuarioTeste;
        }
        return null;
    }

    public List<Produto> buscarProduto(String nomeProduto, boolean soComEstoque){
        List<Produto> resultado = new ArrayList<>();
        // TODO: pensar em uma expressao que remova o if
        if (soComEstoque){
            for (Produto produto : produtosCadastrados){
                if (produto.getNome().contains(nomeProduto) && produto.getEstoque() > 0){
                    resultado.add(produto);
                }
            }
        }else {
            for (Produto produto : produtosCadastrados){
                if (produto.getNome().contains(nomeProduto)){
                    resultado.add(produto);
                }
            }
        }

        return resultado;
    }

    public List<Produto> getProdutos(){
        return produtosCadastrados;
    }

    public boolean adicionarAoCarrinho(int item, int quantidade, Usuario usuario){
        Produto teste = new Produto(item, "Teste");
        if (produtosCadastrados.contains(teste)){
            int i = produtosCadastrados.indexOf(teste);
            teste = produtosCadastrados.get(i);
            Compra carrinho = usuario.getCarrinho();
            carrinho.adcionaProduto(teste,quantidade);
            return true;
        }

        return false;
    }

    public void finalizarCompra(Usuario usuario){
        if (usuarios.contains(usuario)){
            Compra carrinhoUsuario = usuario.getCarrinho();
            carrinhoUsuario.fechaCompra();
            int indexUsuario = usuarios.indexOf(usuario);
            usuario.addCompra(carrinhoUsuario);
            usuario.limpaCarrinho();
            usuarios.set(indexUsuario,usuario);
            valorVendas += carrinhoUsuario.getCustoTotal();
        }
    }

    public String relatorioCompras(){
        StringBuilder retorno = new StringBuilder();
        for (Usuario usuario : usuarios){
            retorno.append(String.format("%-20s | %012d | R$ %5.2f | R$ %5.2f%n", usuario.getNome(),
                    usuario.getQuantidadecompras(), usuario.getValorCompra(), usuario.getValorMedioCompra()));
        }
        retorno.append(String.format("-----------------------------------------------------------------%nValor total de vendas: %.2f%n",valorVendas));
        return retorno.toString();
    }

    public boolean adicionaProduto(int codigo, String nomeProduto) {
        Produto novoProduto = new Produto(codigo,nomeProduto);
        if (produtosCadastrados.contains(novoProduto)) return false;
        produtosCadastrados.add(novoProduto);
        return true;
    }

    public boolean removeProduto(int codigo) {
        Produto produtoTeste = new Produto(codigo);
        int indiceProduto = produtosCadastrados.indexOf(produtoTeste);
        boolean podeRemover = true;
        for (Usuario usuario : usuarios){
            if (usuario.getCarrinho().contemProduto(produtoTeste)){
                podeRemover = false;
                break;
            }
        }
        if (podeRemover) produtosCadastrados.remove(indiceProduto);
        return podeRemover;
    }

    public boolean renomeiaProduto(int codigo, String novoNome) {
        Produto produtoTeste = new Produto(codigo);
        int indiceProduto = produtosCadastrados.indexOf(produtoTeste);
        if (!produtosCadastrados.contains(produtoTeste)) return false;
        Produto produtoRenomear = produtosCadastrados.get(indiceProduto);
        produtoRenomear.setNome(novoNome);
        return true;

    }

    public boolean alteraPrecoProduto(int codigo, double novoPreco) {
        Produto produtoTeste = new Produto(codigo);
        int indiceProduto = produtosCadastrados.indexOf(produtoTeste);
        if (!produtosCadastrados.contains(produtoTeste)) return false;
        Produto produtoReprecificar = produtosCadastrados.get(indiceProduto);
        produtoReprecificar.setPreco(novoPreco);
        return true;
    }


    public boolean alteraEstoqueProduto(int codigo, int novoEstoque) {
        Produto produtoTeste = new Produto(codigo);
        int indiceProduto = produtosCadastrados.indexOf(produtoTeste);
        if (!produtosCadastrados.contains(produtoTeste)) return false;
        Produto produtoAlterarEstoque = produtosCadastrados.get(indiceProduto);
        produtoAlterarEstoque.setEstoque(novoEstoque);
        return true;
    }

    public Usuario cadastrarUsuario(String cpf, String senha, String nome) {
        if (nome == null || senha == null || nome.isEmpty() || senha.isEmpty() || cpf == null || cpf.isEmpty()){
            throw new IllegalArgumentException("Deve-se passar corretamento os valores de usuario e senha");
        }
        cpf = cpf.substring(0,Math.min(TAMANHO_CPF,cpf.length()));
        Usuario usuarioTeste = new Usuario(cpf,senha,nome);
        if(!usuarios.contains(usuarioTeste)){
            usuarios.add(usuarioTeste);
            return usuarioTeste;
        }
        return null;
    }
}
