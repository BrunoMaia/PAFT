package br.pucpr.rpg;

import br.pucpr.rpg.system.Char;

public class Battle {
    public static void main(String[] args) {
        Char hero = new Char("Sir Gallahad",14,80,5);
        Char monster = Char.createGoblin();

        // BATALHA ATE A MORTE
        // QUEREMOS SANGUE
        System.out.printf("%s life: %d versus %s life: %d%n",hero.getName(),
                hero.getLife(),monster.getName(),monster.getLife());
        while (hero.isAlive() && monster.isAlive()){
            hero.attack(monster);
            monster.attack(hero);
            System.out.println();
        }
        Char winner = hero.isAlive() ? hero :monster;
        System.out.printf("%s wins! Life: %d",winner.getName(),winner.getLife());
        }
    }
