package br.pucpr.semaforo;

import java.util.Objects;

public class semaforo {
    private int cor;
    private final String[] CORES = {"Verde","Amarelo","Vermelho"};

    public semaforo(String cor) {
        if (Objects.equals(cor, "Vermelho")){
          this.cor = 2;
        }
        else if (Objects.equals(cor,"Amarelo")){
            this.cor = 1;
        }
        else if (Objects.equals(cor,"Verde")){
            this.cor = 0;
        }
    }
    public semaforo(){
        cor = 2;
    }

    public semaforo(int cor){
        this.cor = cor;
    }

    public void proximaCor(){
        if (cor == 0){
            cor = 1;
        }
        else if (cor == 1){
            cor = 2;
        }
        else if (cor == 2){
            cor = 0;
        }
    }
    public int getCor(){
        return cor;
    }
    public void setCor(String cor){
        if (Objects.equals(cor, "Vermelho")){
            this.cor = 2;
        }
        else if (Objects.equals(cor,"Amarelo")){
            this.cor = 1;
        }
        else if (Objects.equals(cor,"Verde")){
            this.cor = 0;
        }
        else{
            throw new IllegalArgumentException("Passe um parametro de cor v�lido");
        }
    }

    public String getTextoCor(){
        return CORES[cor];
    }
}
