package YahtzeeBot.app.game;

import java.util.Scanner;
import YahtzeeBot.app.bot.*;

public class game {
  public static String inputList = "123456abfslyc";
  
  
  public static void getRoll(Die[] roll){
    for(Die r : roll){
      r.roll();
    }
  }

  public static int getPoints(Roll dice, int input) {
    int[] roll = new int[5];
    for(int i = 0; i < roll.length; i++)
      roll[i] = dice.dice[i].val;
    int turnPoints = 0;
    switch (input+1) {
      case 1:
        turnPoints = ScoreGetter.multiples(roll, 1);
        break;
      case 2:
        turnPoints = ScoreGetter.multiples(roll, 2);
        break;
      case 3:
        turnPoints = ScoreGetter.multiples(roll, 3);
        break;
      case 4:
        turnPoints = ScoreGetter.multiples(roll, 4);
        break;
      case 5:
        turnPoints = ScoreGetter.multiples(roll, 5);
        break;
      case 6:
        turnPoints = ScoreGetter.multiples(roll, 6);
        break;
      case 7:
        turnPoints = ScoreGetter.threeOfAKind(roll);
        break;
      case 8:
        turnPoints = ScoreGetter.fourOfAKind(roll);
        break;
      case 9:
        turnPoints = ScoreGetter.fullHouse(roll);
        break;
      case 10:
        turnPoints = ScoreGetter.smallStraight(roll);
        break;
      case 11:
        turnPoints = ScoreGetter.largeStraight(roll);
        break;
      case 12:
        turnPoints = ScoreGetter.yahtzee(roll);
        break;
      case 13:
        for (int i = 0; i < roll.length; i++)
          turnPoints += roll[i];
        break;
      case 14:
        System.exit(0);
      default:
        break;
    }
    return turnPoints;
  }
  
  
  public static void assignPoints(int index, Player p, int score){
    p.score[index] = score;
    p.open[index] = false;
  
  }
}
