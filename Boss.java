import java.util.*;
public class Boss extends Adventurer{

  private int flames;
  private int maxFlames;
  //ALL PLACEHOLDER VALUES CHANGE IN THE FUTURE

  //Probably a dragon.

  public Boss(){
    super("Dragonicus", 36);
  }

  public Boss(int HP)
  {
    super("Dragonicus",HP);
  }

  public Boss(String name)
  {
    super(name,36);
  }

  public Boss(String name, int HP)
  {
    super(name,HP);
    flames = 0;
    maxFlames = 10;
  }

  public String getSpecialName(){
    return "Flames";
  }

  public int getSpecial(){
    return flames;
  }

  public int getSpecialMax(){
    return maxFlames;
  }

  public void setSpecial(int n){
    if(n <= getSpecialMax() && n > -1)
    {
      flames = n;
    }
    else if(n > getSpecialMax())
    {
      flames = getSpecialMax();
    }else if (n < 0)
    {
      flames = 0;
    }
  }

  public String attack(Adventurer other){
    int damage = (int)(Math.random()*6)+2;
    damage += this.getBuff();
    other.applyDamage(damage);
    restoreSpecial(2);
    return this + " slashed " + other.getName() + " with its claws for " + damage + "damage.";
  }

  public String support(Adventurer other){
    other.setBuff(other.getBuff() - 3);
    return this + " used its draconic power to lower " + other.getName() + "'s attack buff by 3";
  }

  public String support(){
    this.applyDamage(-5);
    return this + " healed itself for 5 damage.";
  }

  public String specialAttack(ArrayList<Adventurer> party, int index){
    // Will turn into AOE eventually. I'll get that figured out soon.
    int damageTotal = 12;
    int numtargets = party.size();
    while(index < party.size())
    {
    return this + " threw a fireball at " + party.get(index).getName() + " for " + damageTotal/numtargets + " damage." + "\n" + specialAttack(party,index+1);
    }
    return "";
  }

}
