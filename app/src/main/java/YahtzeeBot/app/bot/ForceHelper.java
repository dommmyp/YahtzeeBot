package YahtzeeBot.app.bot;

import YahtzeeBot.app.game.ScoreGetter;

public class ForceHelper {

  public static double[] expectedScores = {2.1 + 1/21*(35), 4.2 + 2/21*(35), 6.3 + 3/21*(35), 8.4 + 4/21*(35), 10.5 + 5/21*(35), 12.6 + 6/21*(35),  14, 12, 10, 21, 28, 7.8, 26, 15.6, 15.6, 15.6 };

    public static boolean[] getBestKeepers(int[] roll, int rollNum, ForceBot fb) {
      boolean[] bestKeepers = { false, false, false, false, false };
      boolean[] tempKeepers = { false, false, false, false, false };
      double bestExpect = 0;
  
      for (int i = 0; i <= 1; i++) {
        if (i == 0)
          tempKeepers[0] = false;
        else
          tempKeepers[0] = true;
  
        for (int j = 0; j <= 1; j++) {
          if (j == 0)
            tempKeepers[1] = false;
          else
            tempKeepers[1] = true;
  
          for (int k = 0; k <= 1; k++) {
            if (k == 0)
              tempKeepers[2] = false;
            else
              tempKeepers[2] = true;
  
            for (int l = 0; l <= 1; l++) {
              if (l == 0)
                tempKeepers[3] = false;
              else
                tempKeepers[3] = true;
  
              for (int m = 0; m <= 1; m++) {
                if (m == 0)
                  tempKeepers[4] = false;
                else
                  tempKeepers[4] = true;
  
                double expect = simRoll(roll, tempKeepers, rollNum, fb);
                for(boolean x : tempKeepers){
                  System.out.print(x + " ");
                }
                System.out.println("\n"+expect);
                if (expect > bestExpect) {
                  bestExpect = expect;
                  bestKeepers = tempKeepers.clone();
                }
              }
            }
          }
        }
      }
      
      return bestKeepers;
    }
  
    public static double simRoll(int[] roll, boolean[] keepers, int rollNum, ForceBot fb) {
      Expect e = new Expect();
      double totalExpect = 0;
      int[] simRoll = roll.clone();
      for (int i = 1; i <= 6; i++) {
        if (!keepers[0])
          simRoll[0] = i;
  
        for (int j = 1; j <= 6; j++) {
          if (!keepers[1])
            simRoll[1] = j;
  
          for (int k = 1; k <= 6; k++) {
            if (!keepers[2])
              simRoll[2] = k;
  
            for (int l = 1; l <= 6; l++) {
              if (!keepers[3])
                simRoll[3] = l;
  
              for (int m = 1; m <= 6; m++) {
                if (!keepers[4])
                  simRoll[4] = m;
  
                totalExpect += getMaxDiff(simRoll, fb);
                
                
              }
            }
          }
        }
      }
      return totalExpect;
    }
  
    public static double getMaxDiff(int[] roll, ForceBot fb) {
      int target = 0;
      double maxDiff = -100;
      // aces to sixes
      for (int i = 1; i <= 6; i++) {
        if (ScoreGetter.multiples(roll, i) - expectedScores[i - 1] > maxDiff && fb.open[i-1]) {
          maxDiff = (ScoreGetter.multiples(roll, i) - expectedScores[i - 1]);
          target = i;
        }
      }
      if (ScoreGetter.threeOfAKind(roll) - expectedScores[6] > maxDiff && fb.open[6]) {
        maxDiff = ScoreGetter.threeOfAKind(roll) - expectedScores[6];
        target = 6;
      }
      if (ScoreGetter.fourOfAKind(roll) - expectedScores[7] > maxDiff && fb.open[7]) {
        maxDiff = ScoreGetter.fourOfAKind(roll) - expectedScores[7];
        target = 7;
      }
      if (ScoreGetter.fullHouse(roll) - expectedScores[8] > maxDiff && fb.open[8]) {
        maxDiff = ScoreGetter.fullHouse(roll) - expectedScores[8];
        target = 8;
      }
      if (ScoreGetter.smallStraight(roll) - expectedScores[9] > maxDiff && fb.open[9]) {
        maxDiff = ScoreGetter.smallStraight(roll) - expectedScores[9];
        target = 9;
      }
      if (ScoreGetter.largeStraight(roll) - expectedScores[10] > maxDiff && fb.open[10]) {
        maxDiff = ScoreGetter.largeStraight(roll) - expectedScores[10];
        target = 10;
      }
      if (ScoreGetter.yahtzee(roll) - expectedScores[11] > maxDiff && fb.open[11]) {
        maxDiff = ScoreGetter.yahtzee(roll) - expectedScores[11];
        target = 11;
      } 
      if(2*(ScoreGetter.yahtzee(roll)-expectedScores[13])>maxDiff && !fb.open[11]){
        maxDiff = 2*(ScoreGetter.yahtzee(roll) - expectedScores[11]);
        target = 11;
      }
      if (ScoreGetter.chance(roll) - expectedScores[12] > maxDiff && fb.open[12]) {
        maxDiff = ScoreGetter.chance(roll) - expectedScores[12];
        target = 12;
      }
  
      return maxDiff;
  
    }
}
