package businessLogic;

public abstract class Produto {
    private int codigo;
    private String nome;

    public abstract int getEstoque();
    public abstract int getQuantidadeMinima();
    public abstract String getNome();

}
