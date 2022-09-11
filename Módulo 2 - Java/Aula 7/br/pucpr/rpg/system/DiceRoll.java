package br.pucpr.rpg.system;

import java.util.Random;

public class DiceRoll {
    private static final Random RND = new Random();
    private int dices;
    private int faces;
    private int bonus = 0;

    int getDices(){
        return this.dices;
    }

    int getFaces(){
        return this.faces;
    }

    int getBonus(){
        return this.bonus;
    }

    void setDices(int n){
        dices = n;
    }

    void setFaces(int n){
        faces = n;
    }

    void setBonus(int n){
        bonus = n;
    }

    public DiceRoll(int dados, int faces, int bonus){
        if (dados < 0) dados = 0;
        if (faces < 1) {
            throw new IllegalArgumentException("Invalid faces: " + faces);
        }
        this.dices = dados;
        this.faces = faces;
        this.bonus = bonus;
    }

    public DiceRoll(int dados, int faces){
        this(dados,faces,0);
    }

    public DiceRoll(int faces){
        this(1,faces,0);
    }

    public int roll(){
        int resultado = 0 + bonus;
        for (int i =0;i<dices;i++){
            resultado += RND.nextInt(faces) + 1;
        }
        return resultado;
    }

    public String toString(){
        String bonus_formatado =  "";
        if (bonus > 0){
            bonus_formatado =  String.format("+%d",bonus);
        } else if (bonus < 0) {
            bonus_formatado = String.format("-%d", bonus);
        }
        String saida = String.format("%dD%d%s",dices,faces,bonus_formatado);
        return saida;
    }

    public static String roll(String ... strings){
        return strings[RND.nextInt(strings.length)];
    }

}