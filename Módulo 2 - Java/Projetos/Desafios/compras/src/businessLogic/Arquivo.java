package businessLogic;

import java.util.ArrayList;
import java.util.List;

public class Arquivo {
    private List<Usuario> dados_usuarios = new ArrayList<>();
    private List<Produto> dados_produtos = new ArrayList<>();

    public Arquivo(String caminho, boolean eProduto){

    }

    public boolean existeUsuario(String nome, int hash_senha){
        for (Usuario usr : dados_usuarios){
            if (usr.getCpf().equals(nome) && usr.getHash_senha() == hash_senha) return true;
        }
        return false;
    }
}
