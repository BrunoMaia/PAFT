public class Relogio {
    String nome;
    int horas;
    int minutos;

    String proximoMinuto(){
        if (minutos == 59){
            minutos = 0;
            if (horas == 23){
                horas = 0;
            }
            else {
                horas += 1;
            }
        }
        else {
            minutos += 1;
        }
        String saida = String.format("%s: %02d:%02d",nome,horas,minutos);
        return saida;
    }
}
