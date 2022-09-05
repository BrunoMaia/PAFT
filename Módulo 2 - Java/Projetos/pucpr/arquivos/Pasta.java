package br.pucpr.arquivos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pasta {
    private String nome;
    private List<Pasta> pastas = new ArrayList<>();
    private List<Arquivo> arquivos = new ArrayList<>();

    public void adicionar(Arquivo arquivo){
        arquivos.add(arquivo);
    }
    public void adicionar(Pasta pasta){
        pastas.add(pasta);
    }
    public Pasta(String nome){
        if (nome == null || nome.isEmpty()){
            throw new IllegalArgumentException("Deve-se passar um nome para pasta");
        }
        this.nome = nome;
    }
    public int tamanho(){
        int resultado = 0;
        for (Arquivo arquivo : arquivos){
            resultado += arquivo.getTamanho();
        }
        return resultado;
    }

    public int tamanhoTotal(){
        int resultado = 0;
        for (Arquivo arquivo : arquivos){
            resultado += arquivo.getTamanho();
        }
        for (Pasta pasta : pastas){
            resultado += pasta.tamanhoTotal();
        }
        return resultado;
    }

    public void excluir(String nomeArquivo, boolean recursivo){
        if (nomeArquivo == null || nomeArquivo.isEmpty()){
            throw new IllegalArgumentException("Deve-se inserir um nome de arquivo");
        }
        Iterator<Arquivo> itArquivo = arquivos.iterator();
        while (itArquivo.hasNext()){
            Arquivo arquivoTeste = itArquivo.next();
            if (arquivoTeste.getNome() == nomeArquivo){
                itArquivo.remove();
            }
        }
        if (recursivo){
            for (Pasta pasta : pastas){
                pasta.excluir(nomeArquivo,true);
            }
        }
    }
}
