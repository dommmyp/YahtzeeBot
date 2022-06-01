package YahtzeeBot.app.bot;

public class Keeper {
  public static int multiNum = -1;

  public static String getKeepers(Bot bot, int index, int[] roll){
    switch (bot) {
      case RAND:
        break;
      case EXPECT:
        return getKeepersExpect(index, roll);
      case TOTAL:
        break;
      default:
        System.exit(0);
    }
    return null;
  }

  public static String getKeepersExpect(int index, int[] roll) {
    int[] rollCopy = roll;
    String returnval = "";
    switch (index) {
      case 0:
        System.out.println("1");
        returnval = getNumber(1, roll);
      case 1:
        System.out.println("2");
        returnval = getNumber(2, roll);
      case 2:
        System.out.println("3");
        returnval = getNumber(3, roll);
      case 3:
        System.out.println("4");
        returnval = getNumber(4, roll);
      case 4:
        System.out.println("5");
        returnval = getNumber(5, roll);
      case 5:
        System.out.println("6");
        returnval = getNumber(6, roll);
      case 6:
        System.out.println("y");
        returnval = getYahtzee(roll);
      case 7:
        System.out.println("f");
        returnval = getFullHouse(roll);
      case 8:
        System.out.println("a");
        returnval = getMulti(roll);
      case 9:
        System.out.println("b");
        returnval = getMulti(roll);
      case 10:
        System.out.println("s");
        returnval = getStraight(rollCopy);
      case 11:
        System.out.println("l");
        returnval = getStraight(rollCopy);
      default:
        break;
    }
    return returnval;
  }

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
    
    int consec = 2;
    int currNum = 2;
    String[] indices = new String[3];
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
  