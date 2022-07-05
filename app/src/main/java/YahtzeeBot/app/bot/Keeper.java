/*
 * Class to determine which dice to keep depending
 * on which roll is being gone for
 * used for expectbot
 */

package YahtzeeBot.app.bot;

public class Keeper {
  public static int multiNum = -1;

  public static String getNumber(int num, int[] roll) {
    String keepers = "";
    for (int i = 0; i < 5; i++) {
      if (roll[i] == num) {
        keepers += Integer.toString(i+1);
      }
    }
    return keepers;
  }

  public static String getMulti(int[] roll) {
    return getNumber(multiNum, roll);
  }

  public static String getFullHouse(int[] roll) {
    int[] numCounts = { 0, 0, 0, 0, 0, 0 };
    String keepers = "";
    for (int i = 0; i < 5; i++) {
      numCounts[roll[i] - 1]++;
    }
    int primaryNum = 0;
    int secondaryNum = 0;

    for (int j = 0; j < 5; j++) {
      for (int i = 0; i < 6; i++) {
        if (numCounts[i] > j) {
          primaryNum = i + 1;
        }
        if (numCounts[i] == j && i + 1 != primaryNum) {
          secondaryNum = i + 1;
        }
      }
    }
    if (numCounts[primaryNum - 1] > 1) {
      for (int i = 0; i < 5; i++) {
        if (roll[i] == primaryNum) {
          keepers += Integer.toString(i+1);
        }
      }
    }
    if (numCounts[secondaryNum - 1] > 1) {
      for (int i = 0; i < 5; i++) {
        if (roll[i] == secondaryNum) {
          keepers += Integer.toString(i+1);
        }
      }
    }
    return keepers;
  }

  public static String getYahtzee(int[] roll) {
    int[] numCounts = { 0, 0, 0, 0, 0, 0 };
    String keepers = "";
    for (int i = 0; i < 5; i++) {
      numCounts[roll[i] - 1]++;
    }
    int highNum = 0;
    for (int j = 0; j < 6; j++) {
      for (int i = 0; i < 5; i++) {
        if (numCounts[i] == j) {
          highNum = i + 1;
        }
      }
    }
    for (int i = 0; i < 5; i++) {
      if (roll[i] == highNum) {
        keepers += Integer.toString(i+1);
      }
    }
    return keepers;
  }

  public static String getStraight(int[] roll){
    boolean[] c = {false, false, false, false, false, false};

    for(int i = 0; i < roll.length; i++)
      c[roll[i]-1] = true;
    
    int consec = 0;
    int currNum = 2;
    String[] indices = {"", "", ""};
    int stringIndex = 0;
    for(int i = 1; i <=6 ; i++){
      for(int j = 0; j < roll.length; j++){
        if(roll[j] == i){
          if(i - 1 == currNum | consec == 0){
            indices[stringIndex] += Integer.toString(j);
            consec++;
          } else{
            consec = 1;
            indices[++stringIndex] += Integer.toString(j);
          }
          currNum = i;
        }
      }
    }
    int longestIndex = 0;
    if(indices[1].length() > indices[longestIndex].length())
      longestIndex = 1;
      if(indices[2].length() > indices[longestIndex].length())
      longestIndex = 2;
    return indices[longestIndex];
  }
}
  