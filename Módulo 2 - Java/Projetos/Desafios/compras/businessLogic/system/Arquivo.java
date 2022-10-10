package businessLogic.system;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Arquivo {
    public Set<Usuario> carregaUsuarios(String caminho);
    public Map<Integer,Produto> carregaProdutos(String caminho);
    public void salvaValorVenda(String caminho, double valor);
    public double carregaValorVenda(String caminho);
    public boolean salvaUsuarios(Set<Usuario> usuarios, String caminho);
    public boolean salvaProdutos(Map<Integer,Produto> produtos,String caminho);
}
