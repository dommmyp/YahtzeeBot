package YahtzeeBot.app.bot;

import YahtzeeBot.app.game.ScoreGetter;
import java.util.ArrayList;

public class ForceHelper {

  public static double[] expMultipliers;
  public static double[] expIntercepts;

  public static boolean[] getBestKeepers(int[] roll, int rollNum, ForceBot fb) {
    boolean[] bestKeepers = { false, false, false, false, false };
    boolean[] tempKeepers = { false, false, false, false, false };
    ArrayList<String> done = new ArrayList<String>();
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
              
              if (checkRepeats(tempKeepers, roll, done))
                continue;
              double expect = simRoll(roll, tempKeepers, rollNum, fb);
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

  public static boolean checkRepeats(boolean[] keepers, int[] roll, ArrayList<String> done) {
    String r = "";
    for (int i = 1; i <= 6; i++) {
      for (int j = 0; j < 5; j++) {
        if (roll[j] == i) {
          if (keepers[j])
            r += Integer.toString(roll[j]);
        }
      }
    }
    if (r.equals("")){
      return false;
    }
    for (String x : done) {
      if (r.equals(x)) {
        return true;
      }
    }
    done.add(r);
    return false;
  }

  public static double simRoll(int[] roll, boolean[] keepers, int rollNum, ForceBot fb) {
    Expect e = new Expect();
    double totalExpect = 0;
    int[] simRoll = roll.clone();
    double count = 0;
    for (int i = 1; i <= (keepers[0]? 1 : 6); i++) {
      if (!keepers[0])
        simRoll[0] = i;

      for (int j = 1; j <= (keepers[1]? 1 : 6); j++) {
        if (!keepers[1])
          simRoll[1] = j;

        for (int k = 1; k <= (keepers[2]? 1 : 6); k++) {
          if (!keepers[2])
            simRoll[2] = k;

          for (int l = 1; l <= (keepers[3]? 1 : 6); l++) {
            if (!keepers[3])
              simRoll[3] = l;

            for (int m = 1; m <= (keepers[4]? 1 : 6); m++) {
              if (!keepers[4])
                simRoll[4] = m;
              count++;
              totalExpect += getMaxDiff(simRoll, fb);
            }
          }
        }
      }
    }
    return totalExpect/count;
  }

  public static double getMaxDiff(int[] roll, ForceBot fb) {
    int target = 0;
    double maxDiff = -100;

    for (int i = 1; i <= 6; i++) {
      if (expMultipliers[i]*(ScoreGetter.multiples(roll, i) - expIntercepts[i - 1]) > maxDiff && fb.open[i - 1]) {
        maxDiff = (ScoreGetter.multiples(roll, i) - expIntercepts[i - 1]);
      }
    }
    if (expMultipliers[6]*(ScoreGetter.threeOfAKind(roll) - expIntercepts[6]) > maxDiff && fb.open[6]) {
      maxDiff = ScoreGetter.threeOfAKind(roll) - expIntercepts[6];
    }
    if (expMultipliers[7]*(ScoreGetter.fourOfAKind(roll) - expIntercepts[7]) > maxDiff && fb.open[7]) {
      maxDiff = ScoreGetter.fourOfAKind(roll) - expIntercepts[7];
    }
    if (expMultipliers[8]*(ScoreGetter.fullHouse(roll) - expIntercepts[8]) > maxDiff && fb.open[8]) {
      maxDiff = ScoreGetter.fullHouse(roll) - expIntercepts[8];
    }
    if (expMultipliers[9]*(ScoreGetter.smallStraight(roll) - expIntercepts[9]) > maxDiff && fb.open[9]) {
      maxDiff = ScoreGetter.smallStraight(roll) - expIntercepts[9];
    }
    if (expMultipliers[10]*(ScoreGetter.largeStraight(roll) - expIntercepts[10]) > maxDiff && fb.open[10]) {
      maxDiff = ScoreGetter.largeStraight(roll) - expIntercepts[10];
    }
    if (expMultipliers[11]*(ScoreGetter.yahtzee(roll) - expIntercepts[11]) > maxDiff && fb.open[11]) {
      maxDiff = ScoreGetter.yahtzee(roll) - expIntercepts[11];
    }
    if (expMultipliers[13]*(ScoreGetter.yahtzee(roll) - expIntercepts[13]) > maxDiff && !fb.open[11]) {
      maxDiff = 2 * (ScoreGetter.yahtzee(roll) - expIntercepts[11]);
    }
    if (expMultipliers[12]*(ScoreGetter.chance(roll) - expIntercepts[12]) > maxDiff && fb.open[12]) {
      maxDiff = ScoreGetter.chance(roll) - expIntercepts[12];
    }

    return maxDiff;

  }
}
