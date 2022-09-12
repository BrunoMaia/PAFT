package businessLogic.system;

import java.util.ArrayList;
import java.util.List;

public class Arquivo {
    public Arquivo(String caminho){

    }
    public List<Usuario> carregaUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("12345678901","senha bem forte", "Joao da Silva"));
        usuarios.add(new Usuario("admin","admin",true));
        return usuarios;
    }

    public List<Produto> carregaProdutos(){
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(1,"Pocao de vida",150,1.50));
        return produtos;
    }
}
