package YahtzeeBot.app.bot;

import javax.xml.namespace.QName;

import YahtzeeBot.app.game.*;

public class MaxValBot extends Player{

  public MaxValBot(int num){
    super(num);
  }
  
  public void getKeepers(Roll dice, int index) {
    // nothing to do completely random
  }
  public int getHand(Roll dice, int rollNum){
    int[] roll = new int[5];
    for(int i = 0; i < dice.dice.length; i++)
      roll[i] = dice.dice[i].val;

    double[] expect = new double[12];
      
    expect[0] = Expect.expectNum(roll, 1, rollNum);
    expect[1] = Expect.expectNum(roll, 2, rollNum);
    expect[3] = Expect.expectNum(roll, 4, rollNum);
    expect[2] = Expect.expectNum(roll, 3, rollNum);
    expect[4] = Expect.expectNum(roll, 5, rollNum);
    expect[5] = Expect.expectNum(roll, 6, rollNum);
    expect[6] = Expect.expectMulti(roll, rollNum, 3);
    expect[7] = Expect.expectMulti(roll, rollNum, 4);
    expect[8] = Expect.expectFullHouse(roll, rollNum);
    expect[9] = Expect.expectStraight(roll, rollNum, 1);
    expect[10] = Expect.expectStraight(roll, rollNum, 2);
    expect[11] = Expect.expectYahtzee(roll, rollNum);

    int best = 0;
    
    for(int i = 0; i<13; i++){
      if(!open[i])
        continue;
      if(expect[i]>expect[best])
        best = i;
    }
    return best;
  }

}
