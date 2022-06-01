package YahtzeeBot.app.bot;

import javax.xml.namespace.QName;

import YahtzeeBot.app.game.*;

public class MaxValBot extends Player{

  public MaxValBot(int num){
    super(num);
  }
  
  public void getKeepers(Roll dice, int index) {
    int[] roll = new int[5];
    for(int i = 0; i < dice.dice.length; i++)
      roll[i] = dice.dice[i].val;
    int[] rollCopy = roll;
    String returnval = "";
    switch (index) {
      case 0:
        System.out.println("1");
        returnval = Keeper.getNumber(1, roll);
      case 1:
        System.out.println("2");
        returnval = Keeper.getNumber(2, roll);
      case 2:
        System.out.println("3");
        returnval = Keeper.getNumber(3, roll);
      case 3:
        System.out.println("4");
        returnval = Keeper.getNumber(4, roll);
      case 4:
        System.out.println("5");
        returnval = Keeper.getNumber(5, roll);
      case 5:
        System.out.println("6");
        returnval = Keeper.getNumber(6, roll);
      case 6:
        System.out.println("y");
        returnval = Keeper.getYahtzee(roll);
      case 7:
        System.out.println("f");
        returnval = Keeper.getFullHouse(roll);
      case 8:
        System.out.println("a");
        returnval = Keeper.getMulti(roll);
      case 9:
        System.out.println("b");
        returnval = Keeper.getMulti(roll);
      case 10:
        System.out.println("s");
        returnval = Keeper.getStraight(rollCopy);
      case 11:
        System.out.println("l");
        returnval = Keeper.getStraight(rollCopy);
      default:
        break;
    }
    for(int j = 0; j < dice.dice.length; j++){
      if(returnval.contains(Integer.toString(j+1)))
          dice.dice[j].keep = true;
    }
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
    
    for(int i = 0; i<12; i++){
      if(!open[i])
        continue;
      System.out.println((i+1) + ": "+expect[i]);
      if(expect[i]>expect[best])
        best = i;
    }
    return best;
  }

}
