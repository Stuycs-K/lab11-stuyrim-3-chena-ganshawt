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
    return "souls";
  }

  public int getSpecial(){
    return souls;
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
    this.applyDamage(3);
    int damage = (int)((Math.random() * 3) + 3);
    other.applyDamage(damage);
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
    return this.getName() " had 2 hp turned into 2" + this.getSpecialName() + ". They now have " + this.getHP() + " health left";
  }

  public String specialAttack(Adventurer other){
    return "This a group targeted attack, provide a group of adventures not just one.";
  }




}
