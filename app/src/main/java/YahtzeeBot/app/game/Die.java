package YahtzeeBot.app.game;

public class Die {
  public int val;
  boolean keep;

  public Die(){
    this.keep=false;
  }

  public void roll(){
    if(!keep)
      this.val = (int)(Math.random()*6)+1;
    this.keep = false;
  }
}
