package pucpr.arquivos;

public class Sistema {
    public static void main(String[] args) {
        Pasta documentos = new Pasta("Documentos");
        System.out.printf("Tamanho vazio: %d%n",documentos.tamanho());

        documentos.adicionar(new Arquivo("algumacoisa","java",120));
        System.out.printf("Tamanho com um arquivo: %d%n",documentos.tamanho());

        Pasta outros_documentos = new Pasta("outros documentos");
        outros_documentos.adicionar(new Arquivo("algumacoisa","java",120));

        Pasta mais_alguns_documentos = new Pasta("Talvez algo");
        mais_alguns_documentos.adicionar(new Arquivo("achou"));
        mais_alguns_documentos.adicionar(new Arquivo("algumacoisa","java",120));

        Pasta trabalhos_pucpr = new Pasta("rpg");
        trabalhos_pucpr.adicionar(new Arquivo("Battle","java",3));
        trabalhos_pucpr.adicionar(new Arquivo("algumacoisa","java",120));

        documentos.adicionar(outros_documentos);
        documentos.adicionar(mais_alguns_documentos);
        documentos.adicionar(trabalhos_pucpr);

        System.out.printf("Tamanho com várias pastas e arquivos: %d%n",documentos.tamanhoTotal());

        documentos.excluir("algumacoisa",false);
        System.out.printf("Tamanho após excluir um arquivo: %d%n",documentos.tamanhoTotal());
        documentos.excluir("algumacoisa",true);
        System.out.printf("Tamanho após excluir arquivos recusirvamente: %d%n",documentos.tamanhoTotal());

    }
}
