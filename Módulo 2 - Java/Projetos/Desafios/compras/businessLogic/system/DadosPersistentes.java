package businessLogic.system;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DadosPersistentes implements Arquivo{
    @Override
    public Set<Usuario> carregaUsuarios(String caminho) {
        Set<Usuario> retorno = new HashSet<>();
        try{
            FileInputStream arquivo = new FileInputStream(caminho);
            ObjectInputStream objetoArquivo = new ObjectInputStream(arquivo);
            Usuario usuario = (Usuario) objetoArquivo.readObject();
            while (usuario != null){
                retorno.add(usuario);
                usuario = (Usuario) objetoArquivo.readObject();
            }
            objetoArquivo.close();
            arquivo.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ocorreu um erro ao ler os aquivos de usuarios");
            System.out.println(e.toString());
        }
        retorno.add(new Usuario("admin","admin",true));
        return new HashSet<>(retorno);
    }

    @Override
    public Map<Integer,Produto> carregaProdutos(String caminho){
        Map<Integer,Produto> produtos = null;
        try{
            FileInputStream arquivo = new FileInputStream(caminho);
            ObjectInputStream objetoArquivo = new ObjectInputStream(arquivo);
            produtos = (Map<Integer, Produto>) objetoArquivo.readObject();
           
            objetoArquivo.close();
            arquivo.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Ocorreu um erro ao ler os aquivos de produtos");
            System.out.println(e.toString());
        }
        catch (IOException e) {
            System.out.println("Arquivo de produtos nao encontrado");
            produtos = new HashMap<>();
            produtos.put(1,new Produto("Pocao de vida",150,1.50));

        }
        return produtos;
    }

    @Override
    public double carregaValorVenda(String caminho) {
        double retorno = 0;
        try{
            FileInputStream arquivo = new FileInputStream(caminho);
            ObjectInputStream objetoArquivo = new ObjectInputStream(arquivo);
            retorno = objetoArquivo.readDouble();
            objetoArquivo.close();
            arquivo.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o aquivo de valor de venda");
            System.out.println(e.toString());
        }
        return retorno;
    }

    @Override
    public void salvaValorVenda(String caminho, double valor) {
            try {
                FileOutputStream arquivo = new FileOutputStream(caminho);
                ObjectOutputStream objetoArquivo = new ObjectOutputStream(arquivo);
                objetoArquivo.writeDouble(valor);
                objetoArquivo.close();
                arquivo.close();

            } catch (FileNotFoundException e) {
                System.out.println("Arquivo valor venda nao encontrado");
                System.out.println(e.toString());
            } catch (IOException e) {
                System.out.println("Erro ao iniciar a strema de dados do valor de venda");
                System.out.println(e.toString());
            }
    }

    public boolean salvaUsuarios(Set<Usuario> usuarios, String caminho){
            try {
                FileOutputStream arquivo = new FileOutputStream(caminho);
                ObjectOutputStream objetoArquivo = new ObjectOutputStream(arquivo);

                for (Usuario usuario: usuarios){
                    objetoArquivo.writeObject(usuario);
                }
                objetoArquivo.writeObject(null); // para marcar o fim da lista
                objetoArquivo.close();
                arquivo.close();
                return true;

            } catch (FileNotFoundException e) {
                System.out.println("Arquivo nao encontrado");
                System.out.println(e.toString());
            } catch (IOException e) {
                System.out.println("Erro ao iniciar a strema de dados dos usuarios");
                System.out.println(e.toString());
            }
            return false;
    }

    public boolean salvaProdutos(Map<Integer,Produto> produtos, String caminho){
        try {
            FileOutputStream arquivo = new FileOutputStream(caminho);
            ObjectOutputStream objetoArquivo = new ObjectOutputStream(arquivo);
            objetoArquivo.writeObject(produtos);
            objetoArquivo.close();
            arquivo.close();
            return true;

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo nao encontrado");
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println("Erro ao iniciar a strema de dados dos produtos");
            System.out.println(e.toString());
        }
        return false;
    }

}
