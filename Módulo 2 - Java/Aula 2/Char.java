package br.pucpr.rpg.system;

import java.util.Random;

public class Char {
    static final Random RND = new Random();
    private String name;
    private int skill;
    private int defense;
    private int life;

    public Char(String name, int skill, int defense, int life) {
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("Insira um valor no nome!");
        }
        this.name = name;
        if (skill <= 0){
            throw new IllegalArgumentException("Insira um valor de skill válido");
        }
        this.skill = skill;
        if (defense <= 0){
            throw new IllegalArgumentException("Insira um valor de defesa válido");
        }
        this.defense = defense;
        if (life < 0){
            throw new IllegalArgumentException("Insira um valor de vida válido");
        }
        this.life = life;
    }

    public String getName() {
        return name;
    }

    public int getSkill() {
        return skill;
    }

    public int getDefense() {
        return defense;
    }

    public int getLife() {
        return life;
    }

    public void setSkill(int skill) {
        if (skill <= 0){
            throw new IllegalArgumentException("Insert a valid skill value");
        }
        this.skill = skill;
    }

    public void setDefense(int defense) {
        if (defense <= 0){
            throw new IllegalArgumentException("Insert a valid defense value");
        }
        this.defense = defense;
    }

    public void setLife(int life) {
        if (life <= 0){
            throw new IllegalArgumentException("Insert a valid life value");
        }
        this.life = life;
    }

    public boolean isAlive(){
        return life > 0;
    }

    public boolean isDead(){
        return !isAlive();
    }

    public static Char createGoblin(){
        String[] names = {"Spitz", "Gob", "Uga", "Dandar"};
        String[] surnames = {"the weak", "Baggins", "the ugly","the good","the bad"};
        String name = names[RND.nextInt(names.length)] + " " + surnames[RND.nextInt(names.length)];
        return new Char(
                name,
                new DiceRoll(1,6,10).roll(),
                new DiceRoll(1,8,3).roll(),
                new DiceRoll(1,21,30).roll()
        );
    }

    void takeDamage(int damage){
        if (damage < 0) damage = 0;
        life = Math.max(life - damage,0);
        System.out.printf("%s took %d damage. Life: %d%n",name,damage,life);
    }

    public void attack(Char enemy){
        if (enemy == null){
            throw new IllegalArgumentException("You must provide an enemy");
        }

        if (getLife() <= 0){
            throw new IllegalStateException("Cannot attack while dead");
        }
        System.out.printf("%s attacks %s:", name, enemy.name);
        /* int dice1 = RND.nextInt(6) + 1;
        int dice2 = RND.nextInt(6) + 1;
        int dice3 = RND.nextInt(6) + 1;
        int roll = dice1 + dice2 + dice3; */
        int roll = new DiceRoll(3,6,1).roll();
        int goal = skill - enemy.defense;
        if(roll <= goal){
            System.out.println("HIT!");
            int damage = new DiceRoll(1,6,1).roll();//RND.nextInt(6) + 1;
            enemy.takeDamage(damage);
        }
        else {
            System.out.println("MISS!");
        }
    }
}
