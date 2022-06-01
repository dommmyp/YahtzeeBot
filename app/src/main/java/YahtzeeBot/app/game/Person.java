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
      if(input.equals("quit"))
        System.exit(0);
      for(int j = 0; j < roll.dice.length; j++){
          if(input.contains(Integer.toString(j+1)))
              roll.dice[j].keep = true;
      }
  }

  public int getHand(Roll dice, int rollNum){
    Scanner scnr = new Scanner(System.in);
    char input = ' ';
    // Printer.printOptions();
    System.out.println("Enter option:");
    while(true){
        input = scnr.nextLine().charAt(0);
        if(!open[game.inputList.indexOf(Character.toString(input))])
            System.out.println("already used, please try again");
        else
            break;
    }
    return game.inputList.indexOf(Character.toString(input));

  }
}
