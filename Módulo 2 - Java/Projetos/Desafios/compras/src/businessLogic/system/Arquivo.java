package businessLogic.system;

import java.util.ArrayList;
import java.util.List;

public class Arquivo {
    public Arquivo(String caminho){

    }
    public List<Usuario> carregaUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("12345678901","senha bem forte"));
        return usuarios;
    }

    public List<Produto> carregaProdutos(){
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(19999,"Pocao de vida",150,1.50));
        return produtos;
    }
}
