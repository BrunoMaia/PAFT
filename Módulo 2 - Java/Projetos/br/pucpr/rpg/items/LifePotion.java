package pucpr.rpg.items;

import pucpr.rpg.system.DiceRoll;

public class LifePotion {
    public static final int POTE_PADRAO = 10;
    private int quantidade;

    public int getQuantidade() {
        return quantidade;
    }

    public LifePotion(int quantidade) {
        if (quantidade < 0){
            throw new IllegalArgumentException("A poção não pode ter quantidade negativa");
        }
        this.quantidade = quantidade;
    }
    public LifePotion(){
        this(POTE_PADRAO);
    }

    public int beber(){
        int gole = new DiceRoll(1,6).roll();
        if (gole > quantidade){
            gole = quantidade;
        }
        quantidade -= gole;
        return gole;
    }

    public boolean estaVazia(){
        return quantidade == 0;
    }

    @Override
    public String toString() {
        return String.format("Pote de vida com  %d pontos de restauraç�o",quantidade);
    }
}
