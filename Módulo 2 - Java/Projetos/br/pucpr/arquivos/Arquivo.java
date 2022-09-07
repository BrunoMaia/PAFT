package pucpr.arquivos;
public class Arquivo {
    private String nome;
    private String extencao;
    private int tamanho;

    public int getTamanho(){
        return this.tamanho;
    }

    public String getNome() {
        return nome;
    }

    public String getExtencao() {
        return extencao;
    }

    public Arquivo(String nome, String extencao, int tamanho){
        if (nome == null || nome.isEmpty()){
            throw new IllegalArgumentException("Deve-se passar um nome do arquivo");
        } else if (extencao == null || extencao.isEmpty()) {
            throw new IllegalArgumentException("Deve-se passar uma extenção para o arquivo");
        } else if (tamanho < 0) {
            throw new IllegalArgumentException("O tamanho não pode ser menor que 0 bytes");
        }
        this.nome = nome;
        this.extencao = extencao;
        this.tamanho = tamanho;
    }

    public Arquivo(String nome, String extencao){
        this(nome,extencao,1);
    }

    public Arquivo(String nome){
        this(nome,".txt",1);
    }
}