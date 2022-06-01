package YahtzeeBot.app.game;

import java.util.Scanner;

import YahtzeeBot.app.bot.*;

public class App {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Printer.printWelcome();

        System.out.print(Printer.ANSI_GREEN+"Number of players: ");
        int playerCount = Integer.valueOf(scnr.nextLine());

        Bot b = null;
        if(playerCount > 1){
            System.out.print("Bot type (RAND, EXPECT, TOTAL): ");
            String botInput = scnr.nextLine();
            switch(botInput.toLowerCase()){
                case "rand":
                    b = Bot.RAND;
                    System.out.println("Random bot selected");
                    break;
                case "expect":
                    b = Bot.EXPECT;
                    System.out.println("Expectation based bot selected");
                    break;
                case "total":
                    b = Bot.TOTAL;
                    System.out.println("Fully optimized bot selected");
                    break;
                default:
                    System.out.println("Invalid bot type... Quitting Program");
                    System.exit(0);
                    break;
            }
        }

        int turnNum = 0;

        Player[] players = new Player[playerCount];
        int playerNum = 0;
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(++playerNum);
        }

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
                    
                    game.getRollInput(roll, p, i+1, scnr);
                }   
                System.out.println("Roll 3");
                roll.roll();
                Printer.printRoll(roll);
                int input = game.getOptionInput(p, scnr, roll);
                
                int points = game.getPoints(roll, input);
                System.out.println(points);
                game.assignPoints(input, p, points);
                Printer.printScore(p);
            }
            

        }

    }
}
