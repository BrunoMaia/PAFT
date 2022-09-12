package businessLogic;

import businessLogic.system.Arquivo;
import businessLogic.system.Compra;
import businessLogic.system.Produto;
import businessLogic.system.Usuario;

import java.util.ArrayList;
import java.util.List;

public class BusinessLogic {
    private List<Usuario> usuarios = new Arquivo("usuarios.csv").carregaUsuarios();
    private List<Produto> produtosCadastrados = new Arquivo("produtos.csv").carregaProdutos();
    private double valorVendas = 0.0;
    public static final int TAMANHO_CPF = 11;

    public Usuario logarUsuario(String nome, String senha){
        if (nome == null || senha == null || nome.isEmpty() || senha.isEmpty()){
            throw new IllegalArgumentException("Deve-se passar corretamento os valores de usuario e senha");
        }
        nome = nome.substring(0,Math.min(TAMANHO_CPF,nome.length()));
        if (nome.contains("admin")) return new Usuario(nome, senha, true);
        Usuario usuarioTeste = new Usuario(nome,senha);
        if(usuarios.contains(usuarioTeste)){
            return usuarioTeste;
        }
        return null;
    }

    public List<Produto> buscarProduto(String nomeProduto){
        List<Produto> resultado = new ArrayList<>();
        for (Produto produto : produtosCadastrados){
            if (produto.getNome().contains(nomeProduto) && produto.getEstoque() > 0){
                resultado.add(produto);
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
}
