package businessLogic.system;

import java.util.Objects;

public class Produto {
    private int codigo;
    private String nome;
    private int estoque;
    private double preco;

    public Produto(int codigo, String nome, int estoque, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.estoque = estoque;
        this.preco = preco;
    }

    public Produto(int codigo, String nome){
        this(codigo,nome,0,0);
    }

    public Produto(int codigo) {
        this(codigo,"SEM NOME");
    }

    public int getCodigo() {
        return codigo;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public String getNome() {
        return nome;
    }

    protected int modificaEstoque(int movimentacao, boolean retiraEstoque){
        if (retiraEstoque){
            if (estoque < movimentacao){
                return -1;
            }
            estoque -= movimentacao;
            return estoque;
        }
        estoque += movimentacao;
        return estoque;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        Produto outroProduto = (Produto) obj;
        return this.codigo == outroProduto.getCodigo();
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
}
