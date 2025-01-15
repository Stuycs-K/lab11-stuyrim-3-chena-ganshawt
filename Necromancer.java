import java.util.*;

public class Necromancer extends Adventurer{
  private int souls;
  private int soulsMax;

  public Necromancer(String name, int hp){
    super(name, hp);
    souls = 1;
    soulsMax = 6;
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
    return "Souls";
  }

  public int getSpecial(){
    return souls;
  }

  public int getSpecialMax(){
    return soulsMax;
  }

  public void setSpecial(int n){
    if(n > getSpecialMax()){
        soulsMax = n;
        souls = n;
    }
    else{
      souls = n;
    }
  }

  public String attack(Adventurer other){
    this.applyDamage(3);
    int damage = (int)((Math.random() * 3) + 3);
    other.applyDamage(damage);
    restoreSpecial(1);
    return this.getName() + " sacrificed 3 health to deal " + damage + " damage to " + other.getName() + " leaving " + this.getName() + " with " + this.getHP() + " hp and " + other.getName() + " with " + other.getHP() + "hp.";
  }

  public String support(Adventurer other){
    other.applyDamage(2);
    other.restoreSpecial(2);
    return other.getName() + " had 2 hp turned into 2 " + other.getSpecialName() + ". They now have " + other.getHP() + " health left.";
  }

  public String support(){
    this.applyDamage(2);
    this.restoreSpecial(2);
    return this.getName() + " had 2 hp turned into 2 " + this.getSpecialName() + ". They now have " + this.getHP() + " health left.";
  }

  public String specialAttack(ArrayList<Adventurer> party, int index){
    if(souls >= 2)
    {
    souls -= 2;
    int damageTotal = 12;
    int numtargets = party.size();
    while(index + 1 <= party.size())
    {
    party.get(index).applyDamage(damageTotal/numtargets);
    return this + " sent a skeleton towards " + party.get(index).getName() + " to deal " + damageTotal/numtargets + " damage." + "\n" + specialAttack(party,index+1);
    }
  }
    if(souls < 2 && index < party.size())
    {return "Not enough souls.";}
    return "";
  }




}
