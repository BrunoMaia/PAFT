package br.pucpr.rpg.system;

import br.pucpr.rpg.items.LifePotion;
import br.pucpr.rpg.items.Weapon;

import java.util.Random;

public class Char {
    static final Random RND = new Random();
    private final String name;
    private int skill;
    private int defense;
    private int life;
    private final int initial_life;
    private Weapon weapon = Weapon.FISTS;
    private LifePotion lifePotion;

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
        initial_life = life;
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

    /**
     * @return Retorna a arma do personagem. Caso ele esteja desarmado, retorna os punhos.
     * @see Weapon#FISTS
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Substitui a arma do personagem.
     * @param weapon A nova arma. Nulo se estiver desarmado.
     *               Neste caso, a arma será substituida pelos punhos.
     * @see Weapon#FISTS
     */
    public void setWeapon(Weapon weapon) {
        if (weapon == null) weapon = Weapon.FISTS;
        this.weapon = weapon;
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

    public int getInitial_life() {
        return initial_life;
    }

    public LifePotion getLifePotion() {
        return lifePotion;
    }

    public void setLifePotion(LifePotion lifePotion) {
        this.lifePotion = lifePotion;
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

        if (this.life <= 0){
            throw new IllegalStateException("Cannot attack while dead");
        }
        else if (this.life <= (this.initial_life*0.25) || this.life <= 2){
            this.drinkLifePotion();
        }else {
            System.out.printf("%s attacks %s with %s:", name, enemy.name,weapon.getName());
            int roll = new DiceRoll(3,6,1).roll();
            int goal = skill - enemy.defense;
            if(roll <= goal){
                System.out.println("HIT!");
                enemy.takeDamage(weapon.roll());
            }
            else {
                System.out.println("MISS!");
            }
        }
    }

    public void drinkLifePotion(){
        if (lifePotion == null || lifePotion.estaVazia()){
            lifePotion = null;
            System.out.printf("%s tentou beber uma poção, mas acabou =[",name);
        }
        else {
            int pocao = lifePotion.beber();
            int vidaFaltante = initial_life - life;
            if (pocao > vidaFaltante){
                pocao = vidaFaltante;
            }
            System.out.printf("%s bebeu uma poção de vida. Recuperou %d de vida.",name,pocao);
            life += pocao;
        }
    }
}
