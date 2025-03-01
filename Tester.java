import java.util.*;
import java.util.*;
public class Tester
{

  public static void main(String[] args) {
    System.out.println("\u001b[2J");
    Game.drawBackground();
    // Game.drawText("this is test text", 1, 56);

    // System.out.println();
    // System.out.println(Game.createRandomAdventurer());
    // System.out.println(Game.createRandomAdventurer());
    // System.out.println(Game.createRandomAdventurer());

    //Battle Testing:
    ArrayList<Adventurer> testers = new ArrayList<Adventurer>();
    Paladin paladinTester = new Paladin("Paladin Test");
    Necromancer necromancerTester = new Necromancer("Necromancer Test");
    Artificer artificerTester = new Artificer("Artificer Test");
    testers.add(paladinTester);
    testers.add(necromancerTester);
    testers.add(artificerTester);
    Game.drawParty(testers,1);
    ArrayList<Adventurer> enemies = new ArrayList<Adventurer>();
    Paladin paladinEnemy = new Paladin("Paladin Enemy");
    Necromancer necromancerEnemy = new Necromancer("Necromancer Enemy");
    Artificer artificerEnemy = new Artificer("Artificer Enemy");
    enemies.add(paladinEnemy);
    enemies.add(necromancerEnemy);
    enemies.add(artificerEnemy);
    Game.drawParty(enemies,26);
    Boss dragonTester = new Boss("Dragon Test");
    dragonTester.setSpecial(300);
    necromancerTester.setSpecial(300);
    Game.drawText(dragonTester.specialAttack(testers,0),15,1);
    Game.drawText(necromancerTester.specialAttack(enemies,0),10,1);
    Game.drawParty(testers,1);
    Game.drawParty(enemies,26);
    Game.quit();
/*
    //Accessor methods for the classes
    for(int i = 0; i < testers.size(); i++){
      System.out.println(testers.get(i).getName());
      System.out.println(testers.get(i).getHP());
      System.out.println(testers.get(i).getSpecialName());
      System.out.println(testers.get(i).getSpecial());
      System.out.println(testers.get(i).getSpecialMax());
      System.out.println();
    }

    //Combat Testers:
    //Paladin attacks necromancer
    System.out.println("Paladin attack:");
    System.out.println("HP before: " + testers.get(1).getHP());
    System.out.println(testers.get(0).attack(testers.get(1)));
    System.out.println("HP after: " + testers.get(1).getHP());

    //Paladin supports Necromancer
    System.out.println("Paladin Support");
    System.out.println("Special before: " + testers.get(1).getSpecial());
    System.out.println(testers.get(0).support(testers.get(1)));
    System.out.println("Special after: " + testers.get(1).getSpecial());
*/
}
}
