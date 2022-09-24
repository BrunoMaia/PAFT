package br.pucpr.rpg.items;

import br.pucpr.rpg.system.DiceRoll;
import br.pucpr.rpg.system.Rollable;

public class Weapon {
    public static final Weapon FISTS = new Weapon("Fists",new DiceRoll(0,1,1));
    private String name;
    private Rollable damage;

    public Weapon(String name, Rollable damage) {
        if (damage == null){
            throw new IllegalArgumentException("Damage cannot be null!!");
        }
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("You must provide a name");
        }
        this.name = name;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public Rollable getDamage() {
        return damage;
    }

    public int roll(){
        return damage.roll();
    }

    @Override
    public String toString() {
        return String.format("%s (%s)",name,damage);
    }
}
