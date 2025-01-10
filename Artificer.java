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

  //Just a basic attack with a hammer.
  public String attack(Adventurer other){
    //Placeholder damage, may be increased and decreased
    int damage = (int)((Math.random() * 4) + 1);

    other.applyDamage(damage);
    //Added adjectives to describe the swing.
    String[] possibleAdjectives = {"feebly", "weakly", "forcefully", "with all of his might"};
    return this.getName() + "swung his hammer " + possibleAdjectives[damage - 1] + " at " + other.getName() + "dealing " + damage + " damage, leaving " + other.getName() + " with only " + other.getHP() + "HP left";
  }

  //Searches for special and increases the special counter.
  public String support(){
    return null;
  }

  //Takes special and turns it into damage buffs for friendly units.
  public String support(Adventurer other){
    //Working on adding compatability for changes in strength and damage buffs.
    return null;
  }

  //Deals more damage the more scrap(the special resource) you have, using all the special up in the process.
  public String specialAttack(Adventurer other){
    int damage = 0;
    for(int i = 0; i < scrap; i++){
      damage += (Math.random() * 3) + 1;
    }
    other.applyDamage(damage);
    return this.getName() + "fired all of his scrap at " + other.getName() + "dealing " + damage + " damage, leaving " + other.getName() + " with only " + other.getHP() + "HP left";
  }


}
