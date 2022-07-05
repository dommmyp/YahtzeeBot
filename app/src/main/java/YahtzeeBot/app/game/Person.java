/*
 * Class to represent a human player
 * functions get command line input for all decisions
 */

package YahtzeeBot.app.game;

import java.util.Scanner;

public class Person extends Player{
  
  public Person(int num){
    super(num);
  }

  public void getKeepers(Roll roll, int rollNum){
    Scanner scnr = new Scanner(System.in);
    System.out.println("Enter indices of dice you would like to keep: ");
      String input = "x";
      input = scnr.nextLine();
      if(input.equals("quit")){
        System.out.println("Quitting Program...");
        System.exit(0);
      }
      for(int j = 0; j < roll.dice.length; j++){
          if(input.contains(Integer.toString(j+1)))
              roll.dice[j].keep = true;
      }
  }

  public int getHand(Roll dice, int rollNum){
    Scanner scnr = new Scanner(System.in);
    String input;
    // Printer.printOptions();
    while(true){
      System.out.println("Enter option:");
      input = scnr.next();
      if(!input.equals("")){
          if(input.equals("quit")){
            System.out.println("Quitting program...");
            System.exit(0);
          }
          if(game.inputList.indexOf(Character.toString(input.charAt(0)))<0){
            System.out.println("Invalid input, Please enter valid input or `quit` to exit program");
            continue;  
          }
          if(!open[game.inputList.indexOf(Character.toString(input.charAt(0)))])
            System.out.println("already used, please try again");
          else
            break;
        } else{
          System.out.println("Please enter a selection or `quit` to exit program");
        }
    }
    return game.inputList.indexOf(Character.toString(input.charAt(0)));

  }
}
