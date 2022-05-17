package YahtzeeBot.app.bot;

public class Keeper {
  public static int multiNum = -1;

  public static String getKeepers(int index, int[] roll) {
    int[] rollCopy = roll;
    String returnVal = "";
    switch (index) {
      case 0:
        System.out.println("1");
        returnVal = getNumber(1, roll);
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
    for(int i = 0; i<5; i++){
      if(!returnval[i]){
        returnval[i]=false;
      }
    }
    return null;
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

  public static int[] simSmallStraight(int[] roll) {
    int[] keepers = {-5,-5,-5,-5,-5};
    boolean[] keep = { false, false, false, false, false };
    boolean[] used = {false, false, false, false, false};
    int[] rollCopy = roll;
    for (int j = 1; j <= 5; j += 2) {
      for (int i = 0; i < 5; i++) {
        if (roll[i] == j)
          checkIndex(rollCopy, i, keepers, keep, used);
      }
    }
    for (int i = 0; i < 5; i++) {
      
      if (keepers[i] == -5) {
        keepers[i] = (int) (Math.random() * 6) + 1;
      }
    }
    return keepers;

  }

  public static boolean[] getStraight(int[] roll) {
    int[] keepers = {-5,-5,-5,-5,-5};
    boolean[] keep = { false, false, false, false, false };
    boolean[] used = {false, false, false, false, false};
    int[] rollCopy = roll;
    for (int j = 1; j <= 5; j += 2) {
      for (int i = 0; i < 5; i++) {
        if (roll[i] == j)
          checkIndex(rollCopy, i, keepers, keep, used);
      }
    }
    //checkExtra(roll, keepers);
    return keep;
  }

  public static void getLargeStraight(int[] roll) {

  }

  public static int[] checkIndex(int[] roll, int index, int[] keepers, boolean[] keep, boolean[] used) {
    checkBigger(roll, index, keepers, keep, used);
    checkSmaller(roll, index, keepers, keep, used);
    
    for (int i = 0; i < 5; i++) {
      if (keepers[index] == roll[i])
        used[i]=true;
    }
    return keepers;
  }

  public static void checkBigger(int[] roll, int index, int[] keepers, boolean[] keep, boolean[] used) {
    for (int i = 0; i < 5; i++) {
      if (i != index) {
        if (roll[i] - roll[index] == 1&&!used[i]&&!used[index]) {
          keepers[index] = roll[index];
          keepers[i] = roll[i];
          keep[i] = true;
          keep[index] = true;
          used[i]=true;
          used[index]=true;

          int temp = roll[i];
          checkBigger(roll, i, keepers, keep, used);
          for (int j = 0; j < 5; j++) {
            if (roll[j] == temp) {
              keep[j] = false;
              used[j]=true;
            }
          }
        }
      }
    }


  }

  public static void checkSmaller(int[] roll, int index, int[] keepers, boolean[] keep, boolean[] used) {
    for (int i = 0; i < 5; i++) {
      if (i != index) {
        if (roll[i] - roll[index] == -1&&!used[i]&&used[index]) {
          keepers[index] = roll[index];
          keepers[i] = roll[i];
          keep[index] = true;
          keep[i] = true;
          used[i]=true;
          used[index]=true;

          int temp = roll[i];
          checkSmaller(roll, i, keepers, keep, used);
          for (int j = 0; j < 5; j++) {
            if (roll[j] == temp){
              keep[j] = false;
              used[j]=true;
            }
          }
        }
      }
    }
  }

  public static void checkExtra(int[] roll, int[] keepers, boolean[] keep) {
    for (int i = 0; i < 5; i++) {
      switch (keepers[i]) {
        case 1:
          for (int j = 0; j < 5; j++) {
            if (keepers[j] == 2) {
              // Check for fours
              for (int k = 0; k < 5; k++) {
                if (roll[k] == 4) {
                  keepers[k] = 4;
                  keep[k] = false;
                }
              }
            }
          }
          break;
        case 2:
          for (int j = 0; j < 5; j++) {
            if (keepers[j] == 3) {
              for (int k = 0; k < 5; k++) {
                if (roll[k] == 5) {
                  keepers[k] = 5;
                  keep[k] = false;
                }
              }
            }
          }
          break;
        case 3:
          for (int j = 0; j < 5; j++) {
            if (keepers[j] == 4) {
              for (int k = 0; k < 5; k++) {
                if (roll[k] == 6) {
                  keepers[k] = 6;
                  keep[k]=false;
                  break;
                }
                if (roll[k] == 1) {
                  keepers[k] = 1;
                  keep[k]=false;
                }
              }
            }
          }
          break;
        case 4:
          for (int j = 0; j < 5; j++) {
            if (keepers[j] == 5) {
              for (int k = 0; k < 5; k++) {
                if (roll[k] == 2) {
                  keepers[k] = 2;
                  keep[k]=false;
                }
              }
            }
          }
          break;
        case 5:
          for (int j = 0; j < 5; j++) {
            if (keepers[j] == 6) {
              for (int k = 0; k < 5; k++) {
                if (roll[k] == 3) {
                  keepers[k] = 3;
                keep[k]=false;
                }
              }
            }
          }
          break;
        default:
          break;
      }

    }
  }

}
  