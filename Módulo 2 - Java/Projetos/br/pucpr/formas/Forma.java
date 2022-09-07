package pucpr.formas;

public abstract class Forma {
    private String cor;

    Forma(String cor){
        this.cor = cor;
    }

    public abstract double getArea();
    public abstract double getPerimetro();
}
