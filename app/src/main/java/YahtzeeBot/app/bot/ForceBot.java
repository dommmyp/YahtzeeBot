package YahtzeeBot.app.bot;

import YahtzeeBot.app.game.*;

public class ForceBot extends Player{

  public ForceBot(int num){
    super(num);
  }

  public void getKeepers(Roll dice, int rollNum){
    boolean[] keepers = ForceHelper.getBestKeepers(dice.getNums(), rollNum, this);
    for(int j = 0; j < dice.dice.length; j++){
      if(keepers[j])
        dice.dice[j].keep = true;
    }
  }

  public int getHand(Roll dice, int rollNum) {
    int[] roll = new int[5];
    for(int i = 0; i < dice.dice.length; i++)
      roll[i] = dice.dice[i].val;
    double[] scores = new double[13];
      
    scores[0] = ScoreGetter.multiples(roll, 1);
    scores[1] = ScoreGetter.multiples(roll, 2);
    scores[2] = ScoreGetter.multiples(roll, 3);
    scores[3] = ScoreGetter.multiples(roll, 4);
    scores[4] = ScoreGetter.multiples(roll, 5);
    scores[5] = ScoreGetter.multiples(roll, 6);
    scores[6] = ScoreGetter.threeOfAKind(roll);
    scores[7] = ScoreGetter.fourOfAKind(roll);
    scores[8] = ScoreGetter.fullHouse(roll);
    scores[9] = ScoreGetter.smallStraight(roll);
    scores[10] = ScoreGetter.largeStraight(roll);
    scores[11] = ScoreGetter.yahtzee(roll);
    scores[12] = ScoreGetter.chance(roll);


    int best = 0;
    
    for(int i = 0; i<13; i++){
      if(!open[i])
        continue;
      System.out.println((i+1) + ": "+(scores[i]-ExpectBot.baseExpect[i]));
      if((scores[i]-ExpectBot.baseExpect[i])>(scores[best]-ExpectBot.baseExpect[best]))
        best = i;
    }
    return best;
  

  }

 

}
