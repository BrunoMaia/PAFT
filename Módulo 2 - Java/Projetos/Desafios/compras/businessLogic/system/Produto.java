package businessLogic.system;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class Produto implements Serializable {
    @Serial
    private static final long serialVersionUID = 7125719644116935060L;
    private String nome;
    private int estoque;
    private double preco;

    public Produto(String nome, int estoque, double preco) {
        this.nome = nome;
        this.estoque = estoque;
        this.preco = preco;
    }

    public Produto(String nome){
        this(nome,0,0);
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
        return this.nome == outroProduto.getNome() &&
                this.preco == outroProduto.getPreco() &&
                this.estoque == outroProduto.getEstoque();
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome,preco,estoque);
    }

    public void setNome(String nome) {
        this.nome = nome == null ? "" : nome;
    }

    public void setPreco(double preco) {
        this.preco = Math.max(preco,0);
    }

    public void setEstoque(int estoque) {
        this.estoque = Math.max(estoque,0);
    }
}
