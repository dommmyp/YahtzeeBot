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

  public static int getOptionInput(Player p, Scanner scnr, Roll roll){
    Expect e = new Expect();
    if(p.playerNum == 1){
      char input = ' ';
      // Printer.printOptions();
      System.out.println("Enter option:");
      while(true){
          input = scnr.nextLine().charAt(0);
          if(!p.open[game.inputList.indexOf(Character.toString(input))])
              System.out.println("already used, please try again");
          else
              break;
      }
      return game.inputList.indexOf(Character.toString(input));
    } else {
      return e.getExpect(roll, 3);

    }
  }

  public static void getRollInput(Roll roll, Player p, int turnNum, Scanner scnr){
    if(p.playerNum == 1){
      System.out.println("Enter indices of dice you would like to keep: ");
      String input = "x";
      input = scnr.nextLine();
      for(int j = 0; j < roll.dice.length; j++){
          if(input.contains(Integer.toString(j+1)))
              roll.dice[j].keep = true;
      }
    } else {


    }


  }

  public static int getPoints(Roll dice, int input) {
    int[] roll = new int[5];
    for(int i = 0; i < roll.length; i++)
      roll[i] = dice.dice[i].val;
    int turnPoints = 0;
    switch (input) {
      case 1:
        for (int die : roll)
          if (die == 1)
            turnPoints += die;
        break;
      case 2:
        for (int die : roll)
          if (die == 2) 
            turnPoints += die;
        break;
      case 3:
        for (int die : roll) 
          if (die == 3)
            turnPoints += die;
        break;
      case 4:
        for (int die : roll)
          if (die == 4)
            turnPoints += die;
        break;
      case 5:
        for (int die : roll)
          if (die == 5)
            turnPoints += die;
        break;
      case 6:
        for (int die : roll)
          if (die == 6)
            turnPoints += die;
        break;
      case 7:
        turnPoints = ScoreGetter.ThreeOfAKind(roll);
        break;
      case 8:
        turnPoints = ScoreGetter.FourOfAKind(roll);
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
