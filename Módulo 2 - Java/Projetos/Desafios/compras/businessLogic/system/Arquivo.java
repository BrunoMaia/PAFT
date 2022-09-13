package businessLogic.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Arquivo {

    public Arquivo(){

    }
    public List<Usuario> carregaUsuarios(String caminho){
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("12345678901","senha bem forte", "Joao da Silva"));
        usuarios.add(new Usuario("admin","admin",true));
        return usuarios;
    }

    public List<Produto> carregaProdutos(String caminho) {
        List<Produto> produtos = new ArrayList<>();
        try{//TODO: achar uma forma de fazer a importacao do arquivo csv
            Scanner sc = new Scanner(new File(caminho));
            sc.useDelimiter(";");
            sc.close();
        }catch (FileNotFoundException erro){
            produtos.add(new Produto(1,"Pocao de vida",150,1.50));
            produtos.add(new Produto(2,"Pocao de mana",150,3.00));
            produtos.add(new Produto(3,"Olho de lagarto",5,399.99));
            produtos.add(new Produto(4,"Frasco vazio",300,0.50));
        }
        return produtos;
    }
}
