public class Paladin extends Adventurer{
  int mana, manaMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Paladin(String name, int hp){
    super(name,hp);
    manaMax = 12;
    mana = manaMax/2;
  }


  public Paladin(String name){
    this(name,24);
  }

  public Paladin(){
    this("Bardon the Strong");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "mana";
  }

  public int getSpecial(){
    return mana;
  }

  public void setSpecial(int n){
    mana = n;
  }

  public int getSpecialMax(){
    return manaMax;
  }

  /*Deal 2-7 damage to opponent, restores 2 mana*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*6)+2;
    other.applyDamage(damage);
    restoreSpecial(2);
    return this + " struck "+ other + " and dealt "+ damage +
    " damage in the name of the kingdom.";
  }

  /*Deal 3-12 damage to opponent, only if mana is high enough.
  *Reduces mana by 8.
  */
  public String specialAttack(Adventurer other){

    //This becomes a revive. Don't ask how.
  /*  if(getSpecial() >= 8){
      setSpecial(getSpecial()-8);
      int damage = (int)(Math.random()*5+Math.random()*5)+3;
      other.applyDamage(damage);
      return this + " used their "+preferredLanguage+
      " skills to hack the matrix. "+
      " This glitched out "+other+" dealing "+ damage +" points of damage.";
    }else{
      return "Not enough mana to use the ultimate code. Instead "+attack(other);
    }
*/
  }
  /*Restores 5 special to other*/
  public String support(Adventurer other){
    return "Used a prayer on " + other +" and restores "
    + other.restoreSpecial(5)+" "+other.getSpecialName();
  }
  /*Restores 6 special and 1 hp to self.*/
  public String support(){
    int hp = 1;
    setHP(getHP()+hp);
    return this+" thinks about their noble caused and restores " + hp "HP";
  }
}
