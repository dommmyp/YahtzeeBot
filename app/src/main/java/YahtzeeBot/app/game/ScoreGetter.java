package YahtzeeBot.app.game;


/*
  Class to get the final score of a roll based on the selected option.

  Calculates for:
    Three of a kind,
    Four of a kind,
    Full house,
    Small straight,
    Large straight,
    yahtzee
*/
public class ScoreGetter {

  public static int multiples(int[] roll, int num){
    int total = 0;
    for (int die : roll) 
      if (die == num)
        total += die;
    return total;
  }

  public static int threeOfAKind(int[] roll){
    for(int i = 1; i<=6; i++){
      int rollTotal=0;
      int numCount=0;
      for(int j = 0; j<roll.length; j++){
        if(roll[j]==i)numCount++;
        rollTotal+=roll[j];
      }
      if(numCount>=3)return rollTotal;
    }
    return 0;
  }


  public static int fourOfAKind(int[] roll){
    for(int i = 1; i<=6; i++){
      int rollTotal=0;
      int numCount=0;
      for(int j = 0; j<roll.length; j++){
        if(roll[j]==i)numCount++;
        rollTotal+=roll[j];
      }
      if(numCount>=4)return rollTotal;
    }
    return 0;
  }

  public static int fullHouse(int[] roll){
    for(int i = 1; i<=6;i++){
      for(int j = 1; j<=6; j++){
        if(i==j)continue;
        int numTriple = 0;
        int numDouble = 0;
        for(int k = 0; k<roll.length; k++){
          if(roll[k]==i)numTriple++;
          if(roll[k]==j)numDouble++;
        }
        if(numDouble==2&&numTriple==3)return 25;
      }
    }
    return 0;
  }

  public static int smallStraight(int[] roll){
    boolean[] positions = {false, false, false, false, false, false};
    for(int i = 0; i<roll.length; i++){
      positions[roll[i]-1]=true;
    }
    if(positions[0]&&positions[1]&&positions[2]&&positions[3])return 30;
    if(positions[4]&&positions[1]&&positions[2]&&positions[3])return 30;
    if(positions[4]&&positions[5]&&positions[2]&&positions[3])return 30;
    return 0;
    
  }

  public static int largeStraight(int[] roll){
    boolean[] positions = {false, false, false, false, false, false};
    for(int i = 0; i<roll.length; i++){
      positions[roll[i]-1]=true;
    }
    if(positions[0]&&positions[1]&&positions[2]&&positions[3]&&positions[4])return 40;
    if(positions[5]&&positions[1]&&positions[2]&&positions[3]&&positions[4])return 40;
    return 0;
  }


  public static int yahtzee(int[] roll){
    for(int i = 1; i<=6; i++){
      int numCount=0;
      for(int j = 0; j<roll.length; j++){
        if(roll[j]==i)numCount++;
      }
      if(numCount==5)return 50;
    }
    return 0;
  }

  public static int chance(int[] roll){
    int total = 0;
    for(int die : roll){
      total += die;
    }
    return total;
  }
  
}
