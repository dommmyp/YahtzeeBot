package YahtzeeBot.app;

public class Roll {
  Die[] dice;

  public Roll(){
    dice = new Die[5];
    for(int i = 0; i < dice.length; i++){
      dice[i] = new Die();
    }
  }

  public void roll(){
    for(Die d : dice)
      d.roll();
  }

}
