package pucpr.formas;

public class Circulo  extends Forma{
    private double raio;

    public Circulo(double raio, String cor){
        super(cor);
        this.raio = raio;
    }

    @Override
    public double getArea() {
        return 0;
    }

    @Override
    public double getPerimetro() {
        return 0;
    }
}
