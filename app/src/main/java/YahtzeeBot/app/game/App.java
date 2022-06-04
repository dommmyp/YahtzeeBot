package YahtzeeBot.app.game;

import java.util.Scanner;

import YahtzeeBot.app.bot.*;

public class App {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Printer.printWelcome();
        System.out.print(Printer.ANSI_GREEN+"Number of players: ");
        int playerCount = Integer.valueOf(scnr.nextLine());


        Player[] players = new Player[playerCount];
                    
        for(int i = 0; i < playerCount; i++){
            System.out.print("Player " + (i+1) + " type [PERSON, MAXVAL, EXPECT, FORCE, TOTAL]: ");
            String botInput = scnr.nextLine();
            switch(botInput.toLowerCase()){
                case "person":
                    System.out.println("Person selected");
                    players[i] = new Person(i+1);
                    break;
                case "maxval":
                    System.out.println("Random bot selected");
                    players[i] = new MaxValBot(i+1);
                    break;
                case "expect":
                    System.out.println("Expectation based bot selected");
                    players[i] = new ExpectBot(i+1);
                    break;
                case "force":
                    System.out.println("Brute Force bot selected");
                    players[i] = new ForceBot(i+1);
                    break;
                case "total":
                    System.out.println("Fully optimized bot selected");
                    break;
                default:
                    System.out.println("Invalid bot type... Please enter again");
                    i--;
                    break;
            }
        }

        int turnNum = 0;

        System.out.println("\n\n");
        while (turnNum < 13) {
            turnNum++;
            System.out.println("Turn " + turnNum);
            for(Player p : players){
                System.out.print(Printer.colors[(p.playerNum+2) % 3]);
                Roll roll = new Roll();
                for(int i = 0; i < 2; i ++){
                    roll.roll();
                    System.out.println("\nRoll " + (i + 1) );
                    Printer.printRoll(roll);
                    p.getKeepers(roll, i);
                }   
                System.out.println("Roll 3");
                roll.roll();
                Printer.printRoll(roll);
                int input = p.getHand(roll, 3);
                
                int points = game.getPoints(roll, input);
                System.out.println(points);
                game.assignPoints(input, p, points);
                Printer.printScore(p);
            }
            

        }

    }
}
