import java.util.Random;

public class Char {
    static final Random RND = new Random();
    String name;
    int skill;
    int defense;
    int life;

    static Char createGoblin(){
        String[] names = {"Spitz", "Gob", "Uga", "Dandar"};
        String[] surnames = {"the weak", "Baggins", "the ugly","the good","the bad"};
        String name = names[RND.nextInt(names.length)] + " " + surnames[RND.nextInt(names.length)];
        Char goblin = new Char();
        goblin.name = name;
        goblin.skill = 10 + RND.nextInt(6);
        goblin.defense = 3 + RND.nextInt(8);
        goblin.life = 30 + RND.nextInt(21);
        return goblin;
    }

    void takeDamage(int damage){
        life = Math.max(life - damage,0);
        System.out.printf("%s took %d damage. Life: %d%n",name,damage,life);
    }

    void attack(Char enemy){
        System.out.printf("%s attacks %s:", name, enemy.name);
        int dice1 = RND.nextInt(6) + 1;
        int dice2 = RND.nextInt(6) + 1;
        int dice3 = RND.nextInt(6) + 1;
        int roll = dice1 + dice2 + dice3;
        int goal = skill - enemy.defense;
        if(roll <= goal){
            System.out.println("HIT!");
            int damage = RND.nextInt(6) + 1;
            enemy.takeDamage(damage);
        }
        else {
            System.out.println("MISS!");
        }
    }
}
