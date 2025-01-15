import java.util.*;
public class Game{
  private static final int WIDTH = 80;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLACK;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

  public static void main(String[] args) {
    run();
  }

  //Display the borders of your screen that will not change.
  //Do not write over the blank areas where text will appear or parties will appear.
  public static void drawBackground(){
    System.out.println("\u001b[2J");
    String borderChar = " ";
    System.out.print("\u001b[5;1f");
    System.out.print("\u001b[41m");
    for(int i = 1; i < 81; i++)
    {
      System.out.print(borderChar);
    }
    System.out.print("\u001b[25;1f");
    for(int i = 1; i < 81; i++)
    {
      System.out.print(borderChar);
    }
    //Vertical

    for(int i = 1; i < 5;i++)
    {
    System.out.print("\u001b[" + i + ";25f");
    System.out.println(borderChar);
    System.out.print("\u001b[" + i + ";55f");
    System.out.println(borderChar);

    }

    for(int i = 1; i < 6;i++)
    {
    System.out.print("\u001b[" + (i+24) + ";25f");
    System.out.print(borderChar);
    System.out.print("\u001b[" + (i+24) + ";55f");
    System.out.print(borderChar);
    }
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        System.out.print("\u001b[0m");
  }

  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){

        System.out.print("\u001b[" + startRow + ";" + startCol +"f");
        System.out.print(s);
  }

  /*Use this method to place text on the screen at a particular location.
  *When the length of the text exceeds width, continue on the next line
  *for up to height lines.
  *All remaining locations in the text box should be written with spaces to
  *clear previously written text.
  *@param row the row to start the top left corner of the text box.
  *@param col the column to start the top left corner of the text box.
  *@param width the number of characters per row
  *@param height the number of rows
  */
  public static void TextBox(int row, int col, int width, int height, String text){

    String words = "";
    System.out.println(text.length());
    for(int i = 0; i < text.length();i++)
    {
      if((words + text.charAt(i)).length() > width)
      {

        row++;
        words = text.charAt(i) + "";
        drawText(words,row,col);
      }
      else{
        words += text.charAt(i);
        drawText(words,row,col);
      }



    }

    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }




    //return a random adventurer (choose between all available subclasses)
    //feel free to overload this method to allow specific names/stats.
    public static Adventurer createRandomAdventurer(){
      ArrayList<Adventurer> possibleAdventurers = new ArrayList<Adventurer>();
      possibleAdventurers.add(new Paladin());
      possibleAdventurers.add(new Artificer());
      possibleAdventurers.add(new Necromancer());
      int adventurerChosen = (int)(Math.random() * 3);
      return possibleAdventurers.get(adventurerChosen);
    }

    /*Display a List of 2-4 adventurers on the rows row through row+3 (4 rows max)
    *Should include Name HP and Special on 3 separate lines.
    *Note there is one blank row reserved for your use if you choose.
    *Format:
    *Bob          Amy        Jun
    *HP: 10       HP: 15     HP:19
    *Caffeine: 20 Mana: 10   Snark: 1
    * ***THIS ROW INTENTIONALLY LEFT BLANK***
    */
    public static void drawParty(ArrayList<Adventurer> party,int startRow){
      int row = startRow;
      int col = 1;
      for(int i = 0; i < party.size();i++)
      {


        drawText(party.get(i).getName(),row,col);
        drawText("HP: " + colorByPercent(party.get(i)),row+1,col);
        drawText(party.get(i).getSpecialName() + ": " + party.get(i).getSpecial(),row+2,col);
        drawText("Atk Buff: " + party.get(i).getBuff(),row+3,col);
        if(i == 0)
        {
        col+= 25;
        }
        else
        {col+=30;}

      }


    }


  //Use this to create a colorized number string based on the % compared to the max value.
  public static String colorByPercent(Adventurer player){
    int hp = player.getHP();
    int maxHP = player.getmaxHP();
    String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
    //COLORIZE THE OUTPUT IF HIGH/LOW:
    if(hp / ((double)maxHP) <= 0.25){
      output = Text.colorize(output, 31);
    }
    else if(hp / ((double)maxHP) <= 0.75){
      output = Text.colorize(output, 33);
    }
    else{
      output = Text.colorize(output, 37);
    }
    // under 25% : red
    // under 75% : yellow
    // otherwise : white
    return output;
  }





  //Display the party and enemies
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method.
  public static void drawScreen(ArrayList<Adventurer> party, ArrayList<Adventurer> enemies){

    drawBackground();
    Game.drawParty(party,1);
    //draw player party
    Game.drawParty(party,1);

    //draw enemy party
    Game.drawParty(enemies,26);

    //Move cursor to current input
    //I have no idea where the current input will go.
  }

  public static String userInput(Scanner in){
      //Move cursor to prompt location

      //show cursor

      String input = in.nextLine();

      //clear the text that was written

      return input;
  }

  public static void quit(){
    Text.reset();
    Text.showCursor();
    Text.go(32,1);
  }

  public static void run(){
    //Clear and initialize
    Text.hideCursor();
    Text.clear();


    //Things to attack:
    //Make an ArrayList of Adventurers and add 1-3 enemies to it.
    //If only 1 enemy is added it should be the boss class.
    //start with 1 boss and modify the code to allow 2-3 adventurers later.
    ArrayList<Adventurer>enemies = new ArrayList<Adventurer>();
    int choices = (int)(Math.random() * 10) + 1;
    if(choices > 4)
    {
      for(int i = 0; i < 3; i++)
      {
      enemies.add(createRandomAdventurer());
      }
    }
    else if(choices > 1)
    {
      for(int i = 0; i < 2; i++)
      {
      enemies.add(createRandomAdventurer());
      }
    }
    else
    {
      Adventurer boss = new Boss();
      enemies.add(boss);
    }

    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    ArrayList<Adventurer> party = new ArrayList<>();
    party.add(createRandomAdventurer());
    party.add(createRandomAdventurer());
    party.add(createRandomAdventurer());

    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 0;
    String input = "";//blank to get into the main loop.
    Scanner in = new Scanner(System.in);
    //Draw the window border

    //You can add parameters to draw screen!
    drawScreen(party,enemies);//initial state.

    //Main loop

    //display this prompt at the start of the game.
    String preprompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
    System.out.println();
    System.out.print(preprompt);
    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){

    if(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")))
    {
      for(int i = 0; i < party.size();i++)
      {

        if(input.startsWith("a"))
        {

        }
        else if(input.startsWith("sp"))
        {

        }
        else if(input.startsWith("su"))
        {
          
        }
        else if((input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")))
        {
          // Put quit behavior here.
        }else{
          //Print try again
          i-= 1;}



      }




    }


    if((! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))))
    {




    }

      drawScreen(party,enemies);


    }//end of main game loop


    //After quit reset things:
    quit();
  }
}
