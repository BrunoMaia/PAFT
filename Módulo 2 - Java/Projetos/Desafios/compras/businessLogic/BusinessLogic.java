package businessLogic;

import businessLogic.system.*;

import java.util.*;
import java.util.stream.Collectors;

public class BusinessLogic {
    private static final Arquivo DADOS = new DadosPersistentes();
    private static final String CAMINHO = System.getProperty("user.dir")+"/";
    private final Set<Usuario> usuarios = new HashSet<>(DADOS.carregaUsuarios(CAMINHO+"usuarios.data"));
    private final Map<Integer,Produto> produtosCadastrados= new HashMap<>(DADOS.carregaProdutos(CAMINHO+"produtos.data"));
    private double valorVendas = DADOS.carregaValorVenda(CAMINHO+"valorVenda.data");
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

    public  Map<Integer,Produto> buscarProduto(String nomeProduto, boolean soComEstoque){
        if (soComEstoque) {
            return new HashMap<>(produtosCadastrados.entrySet().stream()
                    .filter(a -> a.getValue().getEstoque() > 0)
                    .filter(a -> a.getValue().getNome().contains(nomeProduto))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
            );
        }
        return new HashMap<>(produtosCadastrados);
    }

    public Map<Integer,Produto> buscarProduto(boolean soComEstoque){
        return buscarProduto("",soComEstoque);
    }

    public Map<Integer,Produto> getProdutos(){
        return new HashMap<>(produtosCadastrados);
    }

    public boolean adicionarAoCarrinho(int item, int quantidade, Usuario usuario){
        Produto teste = produtosCadastrados.get(item);
        if (teste != null){
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
            usuario.addCompra(carrinhoUsuario);
            usuario.limpaCarrinho();
            usuarios.add(usuario);
            valorVendas += carrinhoUsuario.getCustoTotal();
        }
    }

    public String relatorioCompras(){
        StringBuilder retorno = new StringBuilder();
        List<Usuario> resultado = usuarios.stream()
                .sorted(Comparator.reverseOrder()).toList();
        for (Usuario usuario : resultado){
            retorno.append(String.format("%-20s | %012d | R$ %5.2f | R$ %5.2f%n", usuario.getNome(),
                    usuario.getQuantidadecompras(), usuario.getValorCompra(), usuario.getValorMedioCompra()));
        }
        retorno.append(String.format("-----------------------------------------------------------------%nValor total de vendas: %.2f%n",valorVendas));
        return retorno.toString();
    }

    public boolean adicionaProduto(int codigo, String nomeProduto) {
        Produto novoProduto = new Produto(nomeProduto);
        if (produtosCadastrados.get(codigo) != null) return false;
        produtosCadastrados.put(codigo,novoProduto);
        return true;
    }

    public boolean removeProduto(int codigo) {
        boolean podeRemover = true;
        for (Usuario usuario : usuarios){
            if (usuario.getCarrinho().contemProduto(codigo)){
                podeRemover = false;
                break;
            }
        }
        if (podeRemover) produtosCadastrados.remove(codigo);
        return podeRemover;
    }

    public void renomeiaProduto(int codigo, String novoNome) {
        Produto produtoTeste = produtosCadastrados.get(codigo);
        produtoTeste.setNome(novoNome);
        produtosCadastrados.put(codigo,produtoTeste);

    }

    public void alteraPrecoProduto(int codigo, double novoPreco) {
        Produto produtoTeste = produtosCadastrados.get(codigo);
        produtoTeste.setPreco(novoPreco);
        produtosCadastrados.put(codigo,produtoTeste);
    }


    public void alteraEstoqueProduto(int codigo, int novoEstoque) {
        Produto produtoTeste = produtosCadastrados.get(codigo);
        produtoTeste.setEstoque(novoEstoque);
        produtosCadastrados.put(codigo,produtoTeste);
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

    public void salvarDados() {
        DADOS.salvaUsuarios(usuarios,CAMINHO+"usuarios.data");
        DADOS.salvaProdutos(produtosCadastrados,CAMINHO+"produtos.data");
        DADOS.salvaValorVenda(CAMINHO+"valorVenda.data",valorVendas);
    }
}
