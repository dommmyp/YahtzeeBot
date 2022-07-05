/*
* Class to encompass the dice in a roll.
* roll function rolls eah die 
*/

package YahtzeeBot.app.game;

public class Roll {
  public Die[] dice;

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

  public int[] getNums(){
    int[] nums = new int[5];
    for(int i = 0; i < 5; i++){
      nums[i]=dice[i].val;
    }
    return nums;
  }

}
