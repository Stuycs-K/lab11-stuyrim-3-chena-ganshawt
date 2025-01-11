public class Necromancer extends Adventurer{
  private int mana;
  private int manaMax;

  public Necromancer(String name, int hp){
    super(name, hp);
    mana = 4;
    manaMax = 20;
  }

  public Necromancer(String name){
    this(name, 15);
  }

  public Necromancer(int hp){
    this("Necromancer", hp);
  }

  public Necromancer(){
    this("Necromancer", 15);
  }

  public String getSpecialName(){
    return "mana";
  }

  public int getSpecial(){
    return mana;
  }

  public int getSpecialMax(){
    return manaMax;
  }

  public void setSpecial(int n){
    if(n > getSpecialMax()){
        manaMax = n;
        mana = n;
    }
    else{
      mana = n;
    }
  }

  public String attack(Adventurer other){
    return null;
  }

  public String support(Adventurer other){
    return null;
  }

  public String support(){
    return null;
  }

  public String specialAttack(Adventurer other){
    return null;
  }




}
