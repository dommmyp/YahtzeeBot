package YahtzeeBot.app.bot;

import java.util.Arrays;

import YahtzeeBot.app.game.*;

public class Expect {

 
  public static final double[] baseExpect = {2 + 2.1 + 1/21*(35), 4 + 4.2 + 2/21*(35), 6 + 6.3 + 3/21*(35), 8 + 8.4 + 4/21*(35), 10 + 10.5 + 5/21*(35), 12 + 12.6 + 6/21*(35),  3.9, 7.53, 13.35, 5.31, 15.417, 7.8};

  public static int getExpect(int[] dice, int rollNum) {
    int[] roll = new int[5];
    for(int i = 0; i < dice.length; i++)
      roll[i] = dice[i];

    double[] expect = new double[13];
      
    expect[0] = expectNum(roll, 1, rollNum);
    expect[1] = expectNum(roll, 2, rollNum);
    expect[3] = expectNum(roll, 4, rollNum);
    expect[2] = expectNum(roll, 3, rollNum);
    expect[4] = expectNum(roll, 5, rollNum);
    expect[5] = expectNum(roll, 6, rollNum);
    expect[6] = expectMulti(roll, rollNum, 3);
    expect[7] = expectMulti(roll, rollNum, 4);
    expect[8] = expectFullHouse(roll, rollNum);
    expect[9] = expectStraight(roll, rollNum, 1);
    expect[10] = expectStraight(roll, rollNum, 2);
    expect[11] = expectYahtzee(roll, rollNum);

    int best = 0;
    
    for(int i = 0; i<12; i++){
      // System.out.println((i+1) + ": "+(expect[i]-baseExpect[i]));
      if((expect[i]-baseExpect[i])>(expect[best]-baseExpect[best]))
        best = i;
    }
    return best;
  }

  //3 and four of a kind
  public static double expectMulti(int[] roll, int rollNum, int amt){
    double[] expects = new double[6];
    double maxExpect=0;
    int maxIndex = -1;
    for(int i = 0; i<6; i++){
      expects[i] = calcMultiTurn(i+1, roll, rollNum, amt);
      if(expects[i]>maxExpect){
        maxExpect=expects[i];
        maxIndex=i;
      }
    }
    Keeper.multiNum=maxIndex+1;
    return maxExpect;
  }
  public static double calcMultiTurn(int num, int[] roll, int rollNum, int amt){
    double[] probs = {0,0,0,0,0,0};
    int[] numCounts = {0,0,0,0,0,0};
    for(int i = 0; i<5; i++){
      numCounts[roll[i]-1]++;
    }
    int numCount = numCounts[num-1];
    probs[numCount]=1;
    if(rollNum==1){
      probs = calcNumTurn(calcNumTurn(probs));
    }
    if(rollNum==2){
      probs = calcNumTurn(probs);
    }
    double dNum = num;
    if(amt==3)
      return probs[3]*(3*dNum+2*(21-dNum)/5)+probs[4]*(4*dNum+(21-dNum)/5)+probs[5]*5*dNum;
    if(amt==4)
      return probs[4]*(4*dNum+(21-dNum)/5)+probs[5]*5*dNum;
    return 0;
  }

  //small and large straights
  //fullhouse
  //array format = {1/1, 2/1, 2/2, 3/1, 3/2}
  public static double expectFullHouse(int[] roll, int rollNum){
    int[] numCounts = {0,0,0,0,0,0};
    for(int i = 0; i<5; i++){
      numCounts[roll[i]-1]++;
    }
    int primaryNum=0;
    int secondaryNum=0;

    for(int j = 0; j<5; j++){
      for(int i = 0; i<6; i++){
        if(numCounts[i]>j){
          primaryNum=i+1;
        }
        if(numCounts[i]==j&&i+1!=primaryNum){
          secondaryNum=i+1;
        }
      }
    }
    double[] probs = {0,0,0,0,0};
    //array format = {1/1, 2/1, 2/2, 3/1, 3/2}
    switch(numCounts[primaryNum-1]){
      case 1:
        if(numCounts[secondaryNum-1]==1)probs[0]=1;
        break;
      case 2:
        if(numCounts[secondaryNum-1]==1)probs[1]=1;
        if(numCounts[secondaryNum-1]==2)probs[2]=1;
        break;
      case 3:
      case 4:
      case 5:
        if(numCounts[secondaryNum-1]==1)probs[3]=1;
        if(numCounts[secondaryNum-1]==2)probs[4]=1;
        break;
      default:
        break;
    }
    if(rollNum==1){
      probs=calcFullHouseTurn(calcFullHouseTurn(probs));
    }
    if(rollNum==2){
      probs=calcFullHouseTurn(probs);
    }
    return probs[4]*25;
  }
  public static double[] calcFullHouseTurn(double[] probs){
    double[] newProbs = {0,0,0,0,0};
    newProbs[0] = probs[0]*0.093;//1,1

    newProbs[1] = probs[0]*0.463+probs[1]*0.278;//2,1

    newProbs[2] = probs[0]*0.231+probs[1]*0.278+probs[2]*0.667;//2,2
    
    newProbs[3] = probs[0]*0.154+probs[1]*0.37+probs[3]*0.833;//3,1

    newProbs[4] = probs[0]*0.058+probs[1]*0.093+probs[2]*0.333+probs[3]*0.167+probs[4];//3,2

    return newProbs;
  }

  //yahtzee and individual numbers
  public static double expectYahtzee(int[] roll, int rollNum){
    int[] numCounts = {0,0,0,0,0,0};
    double yahtzeeExpect=0;
    for(int i = 0; i<5; i++){
      numCounts[roll[i]-1]++;
    }
    int highNum=0;
    for(int j = 0; j<6; j++){
      for(int i = 0; i<6; i++){
        if(numCounts[i]==j){
          highNum=i+1;
        }
      }
    }
    double[] probs = {0,0,0,0,0,0};
      probs[numCounts[highNum-1]]=1;
    if(rollNum==1){
      probs=calcNumTurn(probs);
      yahtzeeExpect=probs[0]*0.0001+probs[1]*0.0008+probs[2]*0.005+probs[3]*0.028+probs[4]*0.167+probs[5];
    }
    if(rollNum==2){

      yahtzeeExpect=probs[0]*0.0001+probs[1]*0.0008+probs[2]*0.005+probs[3]*0.028+probs[4]*0.167+probs[5];
    }
    if(rollNum==3){yahtzeeExpect=probs[5];}
    return yahtzeeExpect*50;
  }
  public static double expectNum(int[] roll, int num, int rollNum){
    int numCount = 0;
    for(int i = 0; i<5; i++){
      if(roll[i]==num)numCount++;
    }
    double[] probs = {0,0,0,0,0,0};
    probs[numCount]=1;
    if(rollNum==2){
      probs=calcNumTurn(probs);
    }
    
    if(rollNum==1){
      probs=calcNumTurn(calcNumTurn(probs));
    }
    double expect = 0;
    for(int i = 1; i<probs.length; i++){
      expect+=probs[i]*i;
    }
    return expect*num;
  }
  public static double[] calcNumTurn(double[] probs){
    double[] newProbs = new double[6];
    newProbs[0]=probs[0]*0.402;

    newProbs[1]=probs[0]*0.402+probs[1]*0.482;

    newProbs[2]=probs[0]*0.161+probs[1]*0.386+probs[2]*0.579;

    newProbs[3]=probs[0]*0.032+probs[1]*0.116+probs[2]*0.347+probs[3]*0.694;

    newProbs[4]=probs[0]*0.003+probs[1]*0.015+probs[2]*0.069+probs[3]*0.278+probs[4]*0.833;

    newProbs[5]=probs[0]*0.0001+probs[1]*0.0008+probs[2]*0.0046+probs[3]*0.028+probs[4]*0.167+probs[5];

    return newProbs;

  }

  

  public static double expectStraight(int[] roll, int rollNum, int length){
    double straightAmt = 0;
    int count = 0;
    int[] tempRoll = roll;
      int[] tempList = new int[5];
        tempList=Arrays.copyOf(roll, roll.length);
      
      while(count++<1000){
        tempList=Arrays.copyOf(roll, roll.length);
        int[] newRoll = new int[5];
        // if(rollNum==1){
        //   newRoll=Keeper.simSmallStraight(tempList);
        //   newRoll=Keeper.simSmallStraight(newRoll);
        // }
        // if(rollNum==2){
        //   newRoll=Keeper.simSmallStraight(tempList);
        // }
        // if(rollNum==3){
          newRoll=tempList;
        // }
        if(length==1)
          straightAmt+=ScoreGetter.smallStraight(newRoll);
        if(length==2)
          straightAmt+=ScoreGetter.largeStraight(newRoll);
      }
      roll=tempRoll;
      return straightAmt/1000;
  }

  public static void main(String[] args){
    int[] roll = {3,2,3,1,2};
    expectStraight(roll, 1,1);
  }


  public static int[] getLongestRun(int[] keepers){
    int longestIndex = -1;
    int length = 1;
    for(int i = 0; i<5; i++){
      for(int j = 0; j<5; j++){
        if(keepers[i]-keepers[j]==1){
          int tempLength = 1 + getLongestRun(keepers, j);
          if(tempLength>length){
            length=tempLength;
            longestIndex=i;
          }
        }
      }
    }
    int[] returnValue = {longestIndex, length};
    return returnValue;
  }

  public static int getLongestRun(int[] keepers, int index){
    int[] keep = {1,2,4,5,6};
      for(int j = 0; j<5; j++){
        if(keep[index]-keep[j]==1){
          return getLongestRun(keepers, j);
        }
      }
      return 1;
  }
}
  
  
