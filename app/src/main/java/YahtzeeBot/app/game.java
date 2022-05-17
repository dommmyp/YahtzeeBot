package YahtzeeBot.app;

import java.util.Scanner;

public class game {
  public static String inputList = "123456abfslyc";
  
  
  public static void getRoll(Die[] roll){
    for(Die r : roll){
      r.roll();
    }
  }

  public static int getPoints(Roll dice, char input) {
    int[] roll = new int[5];
    for(int i = 0; i < roll.length; i++)
      roll[i] = dice.dice[i].val;
    int turnPoints = 0;
    switch (input) {
      case '1':
        for (int die : roll)
          if (die == 1)
            turnPoints += die;
        break;
      case '2':
        for (int die : roll)
          if (die == 2) 
            turnPoints += die;
        break;
      case '3':
        for (int die : roll) 
          if (die == 3)
            turnPoints += die;
        break;
      case '4':
        for (int die : roll)
          if (die == 4)
            turnPoints += die;
        break;
      case '5':
        for (int die : roll)
          if (die == 5)
            turnPoints += die;
        break;
      case '6':
        for (int die : roll)
          if (die == 6)
            turnPoints += die;
        break;
      case 'a':
        turnPoints = ScoreGetter.ThreeOfAKind(roll);
        break;
      case 'b':
        turnPoints = ScoreGetter.FourOfAKind(roll);
        break;
      case 'f':
        turnPoints = ScoreGetter.fullHouse(roll);
        break;
      case 's':
        turnPoints = ScoreGetter.smallStraight(roll);
        break;
      case 'l':
        turnPoints = ScoreGetter.largeStraight(roll);
        break;
      case 'y':
        turnPoints = ScoreGetter.yahtzee(roll);
        break;
      case 'c':
        for (int i = 0; i < roll.length; i++)
          turnPoints += roll[i];
        break;
      case 'q':
      System.exit(0);
      default:
        break;
    }
    return turnPoints;
  }
  
  
  public static void assignPoints(char input, Player p, int score){
    int index = inputList.indexOf(Character.toString(input));
    p.score[index] = score;
    p.open[index] = false;
  
    
  }
}
