package businessLogic.system;

import java.util.ArrayList;
import java.util.List;

public class Compra {
    private List<Produto> produtos = new ArrayList<>();
    private List<Integer> quantidades = new ArrayList<>();
    private int quantidadeItensTotal;
    private double custoTotal;
    private boolean compraEfetuada = false;

    public Compra(){};

    public boolean adcionaProduto(Produto produto, int quantidade){
        if (compraEfetuada){
            return false;
        }else {
            if (quantidade > produto.getEstoque()){
                return false;
            }
            produtos.add(produto);
            quantidades.add(quantidade);
            quantidadeItensTotal += quantidade;
            custoTotal += (produto.getPreco() * quantidade);
            produto.modificaEstoque(quantidade,true);
            return true;
        }
    }

    public boolean removeProduto(Produto produto, int quantidade){
        if (!compraEfetuada) {
            int index = produtos.indexOf(produto);
            produtos.remove(produto);
            quantidades.remove(index);
            quantidadeItensTotal -= quantidade;
            custoTotal -= (produto.getPreco() * quantidade);
            produto.modificaEstoque(quantidade,false);
            return true;
        }
        return false;
    }

    public void fechaCompra(){
        compraEfetuada = true;
    }

    public int getQuantidadeItensTotal() {
        return quantidadeItensTotal;
    }

    public double getCustoTotal() {
        return custoTotal;
    }

    public boolean isCompraEfetuada() {
        return compraEfetuada;
    }

    public String imprimeCompra(){
        StringBuilder retorno = new StringBuilder();
        if (produtos.isEmpty()) return retorno.toString();
        for (int i=0; i < produtos.size();i++){
            Produto produto = produtos.get(i);
            int quantidade = quantidades.get(i);
            double custo = produto.getPreco() * quantidade;
            retorno.append(String.format("|%03d|%-25s %03dX%.2f = R$ %.2f%n", i, produto.getNome(), quantidade, produto.getPreco(), custo));
        }
        return retorno.toString();
    }
}
