package YahtzeeBot.app.game;

import java.util.Scanner;

import YahtzeeBot.app.bot.*;

public class App {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Printer.printWelcome();
        // FileStuff.writeData("/Users/dominicparolin/Documents/Code/Projects/YahtzeeBot/app/src/main/resources/BaseExpects.txt", ForceHelper.expectedScores);
        double[] weights = FileStuff.readData("/Users/dominicparolin/Documents/Code/Projects/YahtzeeBot/app/src/main/resources/BaseExpects.txt");
        ForceHelper.expectedScores = weights;
        while(true){
            System.out.print(Printer.ANSI_GREEN+"Game type [SIM, PLAY, TEST]: ");
            String gameType = scnr.nextLine();
            if(gameType.toLowerCase().equals("test")){
                testGame(scnr);
                return;
            }
            if(gameType.toLowerCase().equals("sim")){
                System.out.println("Simulation selected");
                System.out.print("Number of players: ");
                int botNum = Integer.valueOf(scnr.nextLine());
                Player[] bots = new Player[botNum];


                System.out.print("Bot type [MAXVAL, EXPECT, FORCE, TOTAL]: ");
                String botInput = scnr.nextLine();
                switch(botInput.toLowerCase()){
                    case "maxval":
                        System.out.println("Random bot selected");
                        
                        break;
                    case "expect":
                        System.out.println("Expectation based bot selected");
                        
                        break;
                    case "force":
                        System.out.println("Brute Force bot selected");
                        for(int i = 0; i < bots.length; i++){
                            bots[i] = new ForceBot(i+1);
                        }
                        break;
                    case "total":
                        System.out.println("Fully optimized bot selected");
                        break;
                    default:
                        System.out.println("Invalid player type... Please enter again");
                        break;
                }
                simGame(bots);
                
                return;
            }
            if(gameType.toLowerCase().equals("quit")){
                System.out.println("Quitting program...");
                System.exit(0);
            }
            if(!gameType.toLowerCase().equals("play")){
                System.out.println("Invalid game type... Please enter again, (`quit` to exit progrgam)");
                continue;
            }
                
            break;
        }
        System.out.print("Number of players: ");
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
                    System.out.println("Invalid player type... Please enter again");
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
                
                game.assignPoints(input, p, points);
                Printer.printScore(p);
            }
            
            

        }
        for(Player p : players){
            Printer.printScore(p);
        }

    }

    public static void simGame(Player[] bots){
        

        int turnNum = 0;
        System.out.println();
        System.out.print("Turn 1.0");
        while (turnNum < 13) {
            turnNum++;
            for(Player p : bots){
                Roll roll = new Roll();
                for(int i = 0; i < 2; i ++){
                    roll.roll();
                    p.getKeepers(roll, i);
                }   
                roll.roll();
                int input = p.getHand(roll, 3);
                int points = game.getPoints(roll, input);
                game.assignPoints(input, p, points);
                System.out.print("\rTurn "+turnNum+"."+p.playerNum);
            }
        }
        System.out.println();
        double totalScore = 0;
        for(Player p : bots){
            totalScore += p.sumScores();
            
        }
        System.out.println("Average score of " + bots.length + " bots: " + (totalScore/bots.length));



    }


    public static void testGame(Scanner scnr){
        ForceBot fb = new ForceBot(0);
        while(true){
            System.out.print("Enter roll to test: ");
            String roll = scnr.nextLine();
            if(roll.toLowerCase().equals("quit")){
                System.exit(0);
            }
            Roll dice = new Roll();
            dice.dice[0].val = Character.getNumericValue(roll.charAt(0));
            dice.dice[1].val = Character.getNumericValue(roll.charAt(1));
            dice.dice[2].val = Character.getNumericValue(roll.charAt(2));
            dice.dice[3].val = Character.getNumericValue(roll.charAt(3));
            dice.dice[4].val = Character.getNumericValue(roll.charAt(4));
            fb.getKeepers(dice, 1);

        }
    }


}
