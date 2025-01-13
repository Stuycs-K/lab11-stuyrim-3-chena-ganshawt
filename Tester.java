public class Tester
{

  public static void main(String[] args) {
    System.out.println("\u001b[2J");
    Game.drawBackground();
    Game.drawText("this is test text", 1, 56);

    Game.TextBox(26,1,24,5,"ABCDEFGHIJKLMNOPQRSTUVWXYZ");

    System.out.println();
    System.out.println(Game.createRandomAdventurer());
    System.out.println(Game.createRandomAdventurer());
    System.out.println(Game.createRandomAdventurer());

    ArrayList<Adventurer> testparty = new ArrayList<Adventurer>(0);
    drawParty

  }
}
