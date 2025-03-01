import java.util.*;
public class Game{
  private static final int WIDTH = 80;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLACK;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;
  private static String gameResult = "";
  private static int turncount = 0;

  public static void main(String[] args) {
    run();
  }

  //Display the borders of your screen that will not change.
  //Do not write over the blank areas where text will appear or parties will appear.
  public static void drawBackground(){
    System.out.println("\u001b[2J");
    String borderChar = " ";
    String horizontalChar = "\u2500";
    String horizontalCharSplitUp = "\u2534";
    String horizontalCharSplitDown = "\u252C";
    String verticalChar = "\u2502";
    System.out.print("\u001b[5;1f");
    //System.out.print("\u001b[41m");
    for(int i = 1; i < 81; i++)
    {
      if(i == 25 || i == 55){
        System.out.print(horizontalCharSplitUp);
      }
      else{
        System.out.print(horizontalChar);
      }
    }
    System.out.print("\u001b[24;1f");
    for(int i = 1; i < 81; i++)
    {
      if(i == 25 || i == 55){
        System.out.print(horizontalCharSplitDown);
      }
      else{
        System.out.print(horizontalChar);
      }
    }
    //Vertical

    for(int i = 1; i < 5;i++)
    {
    System.out.print("\u001b[" + i + ";25f");
    System.out.println(verticalChar);
    System.out.print("\u001b[" + i + ";55f");
    System.out.println(verticalChar);

    }

    for(int i = 1; i < 6;i++)
    {
    System.out.print("\u001b[" + (i+24) + ";25f");
    System.out.print(verticalChar);
    System.out.print("\u001b[" + (i+24) + ";55f");
    System.out.print(verticalChar);
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
  //  System.out.println(text.length());
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
      for(int i = 0; i < 3;i++)
      {
        if(i < party.size())
        {
        drawText("                        ",row,col);
        drawText(party.get(i).getName(),row,col);
        drawText("                        ",row+1,col);
        drawText("HP: " + colorByPercent(party.get(i)),row+1,col);
        drawText("                        ",row+2,col);
        drawText(party.get(i).getSpecialName() + ": " + party.get(i).getSpecial(),row+2,col);
        drawText("                        ",row+3,col);
        drawText("Atk Buff: " + party.get(i).getBuff(),row+3,col);
      }
      else
      {
        drawText("                        ",row,col);
        drawText("                        ",row+1,col);
        drawText("                        ",row+2,col);
        drawText("                        ",row+3,col);
      }
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
    //draw enemy party
    Game.drawParty(enemies,25);

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
    drawText("You " + gameResult, 14, 37);
    drawText("Game played for " + turncount + " turns.",15,28);
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
    int row = 6;
    Scanner in = new Scanner(System.in);
    boolean invalidTarget = true;
    int target = 0;
    int turncounter = 0;

    //Draw the window border

    //You can add parameters to draw screen!
    drawScreen(party,enemies);//initial state.

    //Main loop

    //display this prompt at the start of the game.

    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      row = 6;
      if(party.size() == 0 || enemies.size() == 0)
      {
        if(party.size() == 0)
        {
          gameResult = "lose";
        }
        else
        {
          gameResult = "win";
        }
        input = "quit";
      }

    if(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")))
    {

      for(int i = 0; i < party.size();i++)
      {

        for(int n = 0; n < enemies.size();n++)
        {
        if(enemies.get(n).getHP() <= 0)
        {
          enemies.remove(n);
          n--;
          Game.drawParty(enemies,25);
        }
        }
        if(party.get(i).getHP() <= 0)
        {
          party.remove(i);
          i--;
          Game.drawParty(party,1);
        }
        if(party.size() == 0 || enemies.size() <= 0)
        {
          if(party.size() == 0)
          {
            gameResult = "lose";
          }
          else
          {
            gameResult = "win";
          }
          input = "quit";
        }

        else if (!input.equals("quit")){
        target = 0;
        String prompter = "Type skill for "+party.get(i).getName()+": attack # / support # / special # / quit: ";
        drawText("                                                                                ",29,1);
        drawText(prompter,29,1);
        input = in.nextLine();
        String[] inputArr = input.split("\s+"); // Splits array. \s+ just splits on all whitespace no matter the amount.
        if(inputArr.length > 1)
        {
        target = Integer.parseInt(inputArr[1]) - 1;
        }

        Adventurer currentMember = party.get(i);
        if(input.startsWith("a"))
        {
          if(target < 0 || target > enemies.size())
          {
            target = (int) (Math.random()*enemies.size());
          }
          Game.TextBox(row, 1, 80, 2, currentMember.attack(enemies.get(target)));


          row+= 2;

        }
        else if(input.startsWith("sp"))
        {
          if(target < 0 || target > enemies.size())
          {
            target = (int) (Math.random()*enemies.size());
          }
          Game.TextBox(row,1, 80, 3,currentMember.specialAttack(enemies,target));
          row+=3;

        }
        else if(input.startsWith("su"))
        {

          if(target != -1)
          {
            if(target < -1 || target > party.size())
            {
              target = (int) (Math.random()*party.size());
            }
            Game.TextBox(row,1,80,2,currentMember.support(party.get(target)));
            row+= 2;
          }
          else
          {
            Game.TextBox(row,1,80,2,currentMember.support());
            row+= 2;

          }
        }
        else if((input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")))
        {
          input = "quit";
          gameResult = "quit";
          i+= 9207;
        }else{
          //Print try again
          i-= 1;}

        }
          Game.drawParty(party,1);
          //draw enemy party
          Game.drawParty(enemies,25);

      }




    }

// -----THIS STARTS TO THE
    if((! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))))
    {
      for(int i = 0; i < enemies.size();i++)
      {
        if(enemies.get(i).getHP() <= 0)
        {
          enemies.remove(i);
          i--;
        }
        else{
        String[] skillArr = new String[]{"a","su", "sp"};
        String skillUsed = skillArr[(int) (Math.random() * 2)];
        if(skillUsed.equals("a"))
        {
          Game.TextBox(row,1,80,2,enemies.get(i).attack(party.get((int) (Math.random()*party.size()))));
          row+=2;
        }
        if(skillUsed.equals("sp"))
        {
          Game.TextBox(row,1,80,3,enemies.get(i).specialAttack(party,(int) (Math.random()*party.size())));
                    row+=3;
        }
        if(skillUsed.equals("su"))
        {
          if((int) Math.random() * 2 == 1)
          {
            Game.TextBox(row,1,80,2,enemies.get(i).support(enemies.get((int) (Math.random()*enemies.size()))));

          }
          else
          {
            Game.TextBox(row,1,80,2,enemies.get(i).support());
          }
                    row+=2;
        }}
        Game.drawParty(party,1);
        //draw enemy party
        Game.drawParty(enemies,25);
      }


      if(party.size() == 0 || enemies.size() == 0)
      {
        if(party.size() == 0)
        {
          gameResult = "lose";
        }
        else
        {
          gameResult = "win";
        }
        input = "quit";
      }
    }


    turncounter++;
    if(!input.equals("quit"))
    {
    input = "sentencethatnobodywouldtype";
    }
    while(input.equals("sentencethatnobodywouldtype"))
    {
    drawText("                                                                                      ",29,1);
    drawText("Enter any key to continue: ",29,1);
    input = in.nextLine();
    }
    if(party.size() == 0 || enemies.size() == 0)
    {
      if(party.size() == 0)
      {
        gameResult = "lose";
      }
      else
      {
        gameResult = "win";
      }
      input = "quit";
    }


    drawScreen(party,enemies);

    }//end of main game loop

    turncount = turncounter;
    //After quit reset things:
    quit();
  }
}
