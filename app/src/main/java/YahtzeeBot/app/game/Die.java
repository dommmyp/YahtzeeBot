/*
 * Class for each die in a roll
 * includes the value of the die, if the die is selected to be kept
 * and a function to roll the die
 */


package YahtzeeBot.app.game;

public class Die {
  public int val;
  public boolean keep;

  public Die(){
    this.keep=false;
  }

  public void roll(){
    if(!keep)
      this.val = (int)(Math.random()*6)+1;
    this.keep = false;
  }
}
