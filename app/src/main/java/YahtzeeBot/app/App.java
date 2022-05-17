/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package YahtzeeBot.app;

import java.util.Scanner;
import java.util.*;
import java.lang.*;
import java.io.*;

public class App {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter number of players:");
        int playerCount = Integer.valueOf(scnr.nextLine());
        int turnNum = 0;

        Player[] players = new Player[playerCount];
        int playerNum = 0;
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(++playerNum);
        }

        while (turnNum < 13) {
            turnNum++;
            System.out.println("Turn number " + turnNum);
            for(Player p : players){
                Roll roll = new Roll();
                for(int i = 0; i < 2; i ++){
                    roll.roll();
                    System.out.println("\nRoll " + ( i + 1) );
                    Printer.printRoll(roll);
                    System.out.println("Enter indices of dice you would like to keep: ");
                    String input = "x";
                    input = scnr.nextLine();
                    for(int j = 0; j < roll.dice.length; j++){
                        if(input.contains(Integer.toString(j+1)))
                            roll.dice[j].keep = true;
                    }
                }   
                System.out.println("Roll 3");
                roll.roll();
                Printer.printRoll(roll);
                // Printer.printOptions();
                System.out.println("Enter option:");
                char input = ' ';
                while(true){
                    input = scnr.nextLine().charAt(0);
                    if(!p.open[game.inputList.indexOf(Character.toString(input))])
                        System.out.println("already used, please try again");
                    else
                        break;
                }
                int points = game.getPoints(roll, input);
                game.assignPoints(input, p, points);
                Printer.printScore(p);
            }
            

        }

    }
}
