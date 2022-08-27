import java.util.Scanner;

public class Exercico01 {
    public static void main(String[] args) {
        int quantidade_retangulos = 5;
        Retangulo [] retangulos = new Retangulo[quantidade_retangulos];
        Scanner sc = new Scanner(System.in);
        for(int i=0; i<quantidade_retangulos;i++){
            Retangulo leitura = new Retangulo();
            System.out.print("Por favor, digite a altura: ");
            leitura.altura = Double.parseDouble(sc.nextLine());
            System.out.print("Por favor, digite a largura: ");
            leitura.largura = Double.parseDouble(sc.nextLine());
            leitura.calculaArea();
            leitura.calculaPerimetro();
            retangulos[i] = leitura;
        }
        for(Retangulo valor : retangulos){
            String saida = String.format("Altura: %.2f largura: %.2f area: %.2f perimetro: %.2f",
                    valor.altura,valor.largura,valor.area,valor.perimetro);
            System.out.println(saida);
        }
    }
}
