package businessLogic;

public class Usuario {
    private boolean admin;
    private String cpf;
    private int senha;

    public Usuario(String cpf) {
        this.cpf = cpf;
    }
}
