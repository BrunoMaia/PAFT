package businessLogic;

public class BusinessLogic {
    private Arquivo usuarios = new Arquivo("usuarios.csv",false);
    public static final int TAMANHO_CPF = 11;

    public Usuario logarUsuario(String nome, String senha){
        if (nome == null || senha == null || nome.isEmpty() || senha.isEmpty()){
            throw new IllegalArgumentException("Deve-se passar corretamento os valores de usuario e senha");
        }
        nome = nome.substring(0,Math.min(TAMANHO_CPF,nome.length()));
        if (nome.equals("admin") && senha.equals("admin")){
            return new Usuario(nome,true);
        }
        if(usuarios.existeUsuario(nome, senha.hashCode())){
            return new Usuario(nome, senha.hashCode());
        }
        return null;
    }
}
