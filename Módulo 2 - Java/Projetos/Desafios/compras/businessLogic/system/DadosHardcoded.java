package businessLogic.system;

import java.util.*;

public class DadosHardcoded implements Arquivo{
    public Set<Usuario> carregaUsuarios(String caminho){
        Set<Usuario> usuarios = new DadosPersistentes().carregaUsuarios(caminho);
        usuarios.add(new Usuario("admin","admin",true));
        return usuarios;
    }

    public Map<Integer,Produto> carregaProdutos(String caminho) {
        Map<Integer,Produto> produtos = new HashMap<>();
        produtos.put(1,new Produto("Pocao de vida",150,1.50));
        produtos.put(2,new Produto("Pocao de mana",150,3.00));
        produtos.put(3,new Produto("Olho de lagarto",5,399.99));
        produtos.put(4,new Produto("Frasco vazio",300,0.50));
        return produtos;
    }

    @Override
    public void salvaValorVenda(String caminho, double valor) {

    }

    @Override
    public double carregaValorVenda(String caminho) {
        return 0;
    }

    @Override
    public boolean salvaUsuarios(Set<Usuario> usuarios, String caminho) {
        return false;
    }

    @Override
    public boolean salvaProdutos(Map<Integer, Produto> produtos, String caminho) {
        return false;
    }
}
