import java.util.*;

public class Artificer extends Adventurer{
  private int scrap;
  private int scrapMax;

  //This is to be implemented later with the supportSelf() method
  private int numberOfConsecutiveSearches;

  //Constructors:
  public Artificer(String name, int hp){
    super(name, hp);
    scrap = 0;
    scrapMax = 10;
  }

  public Artificer(String name){
    //Placeholder values for default health for now
    this(name, 20);
  }

  public Artificer(int hp){
    //Placeholder name.
    this("Artificer", hp);
  }

  public Artificer(){
    this("Artificer", 20);
  }

  //Methods for special:
  public String getSpecialName(){
    return "Scrap";
  }

  public int getSpecial(){
    return scrap;
  }

  public int getSpecialMax(){
    return scrapMax;
  }

  public void setSpecial(int n){
    if(n > getSpecial()){
      scrapMax = n;
      scrap = n;
    }
    else{
      scrap = n;
    }
  }

  //Debug code for numberOfConsecutiveSearches
  public int getNumberOfConsecutiveSearches(){
    return numberOfConsecutiveSearches;
  }

  public void setNumberOfConsecutiveSearches(int n){
    numberOfConsecutiveSearches = n;
  }

  private void resetSearches(){
    numberOfConsecutiveSearches = 0;
  }

  //Just a basic attack with a hammer.
  public String attack(Adventurer other){
    //Placeholder damage, may be increased and decreased
    int damage = (int)((Math.random() * 4) + 1);

    other.applyDamage(damage);
    resetSearches();
    //Added adjectives to describe the swing.
    String[] possibleAdjectives = {"feebly", "weakly", "forcefully", "with all of his might"};
    return this.getName() + " swung his hammer " + possibleAdjectives[damage - 1] + " at " + other.getName() + " dealing " + damage + " damage, leaving " + other.getName() + " with only " + other.getHP() + "HP left";
  }

  //Searches for special and increases the special counter.
  public String support(){
    if(numberOfConsecutiveSearches > 3){
      return "There seems to be no scrap around. Choose a different action.";
    }
    else{
      int amountRestored = 3 - numberOfConsecutiveSearches;
      super.restoreSpecial(3 - numberOfConsecutiveSearches);
      numberOfConsecutiveSearches++;
      String[] adjectives = {"small", "medium", "large"};
      return this.getName() + " searched around the environment and found a " + adjectives[amountRestored - 1] + " amount of metal. Restored " + amountRestored + " scrap";
    }

  }

  //Takes special and turns it into damage buffs for friendly units.
  public String support(Adventurer other){
    if(getSpecial() > 1){
      int buffAmt = 0;
      if(getSpecial() >= 3)
      {
        buffAmt = 3;
        setSpecial(getSpecial()-3);
      }
      else{
        buffAmt = getSpecial();
        setSpecial(getSpecial() - buffAmt);
      }
      resetSearches();
      other.setBuff(other.getBuff() + buffAmt );
      return this.getName() + " used his scrap to sharpen his weapons, giving " + other.getName() + " a " + buffAmt + " attack damage buff";
    }
    else{
      return this.getName() + " seems not to have enough scrap to buff " + other.getName();
    }
  }

  //Deals more damage the more scrap(the special resource) you have, using all the special up in the process.
  public String specialAttack(ArrayList<Adventurer> targets, int index){
    if(scrap == 0){
      return this.getName() + " doesn't have any scrap to fire. Instead, he " + attack(targets.get(index));
    }
    else{
      Adventurer other = targets.get(index);
      int damage = 0;
      for(int i = 0; i < scrap; i++){
        damage += (Math.random() * 3) + 1;
      }
      other.applyDamage(damage);
      resetSearches();
      return this.getName() + "fired all of his scrap at " + other.getName() + "dealing " + damage + " damage, leaving " + other.getName() + " with only " + other.getHP() + "HP left";
    }
  }


}
