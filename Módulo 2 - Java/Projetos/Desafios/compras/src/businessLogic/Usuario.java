package businessLogic;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private boolean admin;
    private String cpf;
    private int hash_senha;
    private List<Produto> carrinho = new ArrayList<>();
    private List<Produto> historico = new ArrayList<>();

    public Usuario(String cpf, int hash_senha) {
        this.cpf = cpf;
        this.hash_senha = hash_senha;
    }

    public Usuario(String cpf, boolean admin){
        if (admin){
            this.cpf = cpf;
            this.hash_senha = 0;
            this.admin = true;
        }
    }

    public String getCpf() {
        return cpf;
    }

    public int getHash_senha() {
        return hash_senha;
    }
}
