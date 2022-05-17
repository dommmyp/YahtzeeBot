package YahtzeeBot.app;

public class Die {
  int val;
  boolean keep;

  public Die(){

  }

  public void roll(){
    this.val = (int)(Math.random()*6)+1;
  }
}
