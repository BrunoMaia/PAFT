package businessLogic.system;

import businessLogic.system.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {
    private boolean admin;
    private String cpf;
    private String senha;
    private List<Compra> compras = new ArrayList<>();
    private Compra carrinho = new Compra();

    public Usuario(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    public Usuario(String cpf, boolean admin){
        if (admin){
            this.cpf = cpf;
            this.senha = "admin";
            this.admin = true;
        }
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        Usuario outroUsuario = (Usuario) obj;
        return this.admin == outroUsuario.admin &&
                this.cpf.equals(outroUsuario.cpf) &&
                this.senha.equals(outroUsuario.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(admin,senha,cpf);
    }

    public String imprimeCarrinho() {
        return carrinho.imprimeCompra();
    }

    public Compra getCarrinho(){
        return carrinho;
    }

    public double getValorCarrinho(){
        return carrinho.getCustoTotal();
    }

    public void addCompra(Compra compra){
        compras.add(compra);
    }

    public void limpaCarrinho(){
        carrinho = new Compra();
    }

}
