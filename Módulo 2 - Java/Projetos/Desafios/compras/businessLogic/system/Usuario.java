package businessLogic.system;

import businessLogic.system.Produto;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Usuario implements Serializable, Comparable<Usuario> {
    @Serial
    private static final long serialVersionUID = 7125719644116935059L;
    private boolean admin;
    private String cpf;
    private String senha;
    private String nome;
    private List<Compra> compras = new ArrayList<>();
    private Compra carrinho = new Compra();
    private int itensComprados;
    private double valorCompra;
    private double valorMedioCompra;

    public Usuario(String cpf, String senha, String nome, boolean admin){
        if (cpf == null || cpf.isEmpty() || senha == null || senha.isEmpty() || nome == null || nome.isEmpty()){
            throw new IllegalArgumentException("Voce deve passar corretamente os argumentos!");
        }
        this.cpf = cpf;
        this.senha = senha;
        this.nome = nome;
        this.admin = admin;

    }

    public Usuario(String cpf, String senha, String nome){
        this(cpf,senha,nome,false);
    }
    public Usuario(String cpf, String senha) {
        this(cpf,senha,"John Snow");
    }

    public Usuario(String cpf, String senha, boolean admin){
        this(cpf,senha, "Administrador", true);

    }

    public int getQuantidadecompras(){
        return compras.size();
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public int getItensComprados() {
        return itensComprados;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public double getValorMedioCompra() {
        return valorMedioCompra;
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
        valorCompra += compra.getCustoTotal();
        itensComprados += compra.getQuantidadeItensTotal();
        valorMedioCompra = valorCompra / itensComprados;
        compras.add(compra);
    }



    public void limpaCarrinho(){
        carrinho = new Compra();
    }

    @Override
    public int compareTo(Usuario usuario) {
        return (int) (valorCompra - usuario.getValorCompra());
    }
}