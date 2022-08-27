public class Battle {
    public static void main(String[] args) {
        Char hero = new Char();
        hero.name = "Sir Gallahad";
        hero.skill = 14;
        hero.life = 80;
        hero.defense = 5;
        Char monster = Char.createGoblin();

        // BATALHA ATE A MORTE
        // QUEREMOS SANGUE
        System.out.printf("%s life: %d versus %s life: %d%n",hero.name, hero.life,monster.name,monster.life);
        while (hero.life > 0 && monster.life > 0){
            hero.attack(monster);
            monster.attack(hero);
            System.out.println();
        }
        if (hero.life > 0){
            System.out.printf("%s wins! Life: %d", hero.name,hero.life);
        }
        else {
            System.out.printf("%s wins! Life: %d", monster.name, monster.life);
        }
    }
}
