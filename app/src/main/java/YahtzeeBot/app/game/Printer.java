package YahtzeeBot.app.game;

/*
  Class for printing output to user
*/

public class Printer {

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_WHITE = "\u001B[37m";
  public static final String[] colors = {ANSI_PURPLE, ANSI_BLUE, ANSI_CYAN, ANSI_GREEN};


  public static void printScore(Player p){
    System.out.println("Upper Section");
    System.out.printf("| %s%13s%s |%3s|\n", !p.open[0] ? ANSI_WHITE : colors[(p.playerNum+2)%3], "Aces",colors[(p.playerNum+2)%3], p.score[0]);
    System.out.printf("| %s%13s%s |%3s|\n", !p.open[1] ? ANSI_WHITE : colors[(p.playerNum+2)%3], "Twos", colors[(p.playerNum+2)%3], p.score[1]);
    System.out.printf("| %s%13s%s |%3s|\n", !p.open[2] ? ANSI_WHITE : colors[(p.playerNum+2)%3], "Threes", colors[(p.playerNum+2)%3], p.score[2]);
    System.out.printf("| %s%13s%s |%3s|\n", !p.open[3] ? ANSI_WHITE : colors[(p.playerNum+2)%3], "Fours", colors[(p.playerNum+2)%3], p.score[3]);
    System.out.printf("| %s%13s%s |%3s|\n", !p.open[4] ? ANSI_WHITE : colors[(p.playerNum+2)%3], "Fives", colors[(p.playerNum+2)%3], p.score[4]);
    System.out.printf("| %s%13s%s |%3s|\n", !p.open[5] ? ANSI_WHITE : colors[(p.playerNum+2)%3], "Sixes", colors[(p.playerNum+2)%3], p.score[5]);
    System.out.printf("|Upper Section Total|%3s|\n", p.sumTop());
    System.out.printf("|%s%15s%s|%3s|\n", !p.open[6] ? ANSI_WHITE : colors[(p.playerNum+2)%3], "Three of a Kind", colors[(p.playerNum+2)%3], p.score[6]);
    System.out.printf("|%s%15s%s|%3s|\n", !p.open[7] ? ANSI_WHITE : colors[(p.playerNum+2)%3], "Four of a Kind", colors[(p.playerNum+2)%3], p.score[7]);
    System.out.printf("| %s%13s%s |%3s|\n", !p.open[8] ? ANSI_WHITE : colors[(p.playerNum+2)%3], "Full House", colors[(p.playerNum+2)%3], p.score[8]);
    System.out.printf("| %s%13s%s |%3s|\n", !p.open[9] ? ANSI_WHITE : colors[(p.playerNum+2)%3], "Sm Straight", colors[(p.playerNum+2)%3], p.score[9]);
    System.out.printf("| %s%13s%s |%3s|\n", !p.open[10] ? ANSI_WHITE : colors[(p.playerNum+2)%3], "Lg Straight", colors[(p.playerNum+2)%3], p.score[10]);
    System.out.printf("| %s%13s%s |%3s|\n", !p.open[11] ? ANSI_WHITE : colors[(p.playerNum+2)%3], "Yahtzee", colors[(p.playerNum+2)%3], p.score[11]);
    System.out.printf("| %s%13s%s |%3s|\n", !p.open[12] ? ANSI_WHITE : colors[(p.playerNum+2)%3], "Chance", colors[(p.playerNum+2)%3], p.score[12]);
    System.out.printf("|Lower Section Total|%3s|\n", p.sumBottom());
    System.out.printf("| Total|%3s|\n", p.sumScores());

  }


  public static void printOptions() {
    System.out.println("Enter category to use points for\n" +
        "1s - 1\n" +
        "2s - 2\n" +
        "3s - 3\n" +
        "4s - 4\n" +
        "5s - 5\n" +
        "6s - 6\n" +
        "3oak - a\n" +
        "4oak - b\n" +
        "full house - f\n" +
        "small straight - s\n" +
        "large straight - l\n" +
        "Yahtzee - y\n" +
        "chance - c\n" +
        "enter choice:");
  }

  public static void printRollNums(Roll roll) {
    for (int i = 0; i < roll.dice.length; i++) {
      System.out.print(roll.dice[i].val + " ");
    }
    System.out.println("\n");
  }


  public static void printRoll(Roll roll){

    for (int i = 0; i < 5; i++) {
      System.out.print(" __________  ");
    }
    System.out.println();
    for (int i = 0; i < 5; i++) {
      System.out.print("|          | ");
    }
    System.out.println();
    // top row
    for (int i = 0; i < 5; i++) {
      if(roll.dice[i].val == 6 | roll.dice[i].val == 5 | roll.dice[i].val == 4)
        System.out.print("| ()    () | ");
      else if(roll.dice[i].val == 3 | roll.dice[i].val == 2)
        System.out.print("| ()       | ");
      else
        System.out.print("|          | ");
    }
    System.out.println();
    // middle row
    for (int i = 0; i < 5; i++) {
      if(roll.dice[i].val == 5 | roll.dice[i].val == 3 | roll.dice[i].val == 1)
        System.out.print("|    ()    | ");
      else if(roll.dice[i].val == 6)
        System.out.print("| ()    () | ");
      else
        System.out.print("|          | ");
    }
    System.out.println();
    // bottom row
    for (int i = 0; i < 5; i++) {
      if(roll.dice[i].val == 6 | roll.dice[i].val == 5 | roll.dice[i].val == 4)
        System.out.print("| ()    () | ");
      else if(roll.dice[i].val == 3 | roll.dice[i].val == 2)
        System.out.print("|       () | ");
      else
        System.out.print("|          | ");
    }
    System.out.println();
    for (int i = 0; i < 5; i++) {
      System.out.print("|__________| ");
    }
    System.out.println("\n");


  }

  public static String dice = 
    "\t       .-------.    ______\n"+
    "\t      /   o   /|   /\\     \\\n"+
    "\t     /_______/o|  /o \\  o  \\\n"+
    "\t     | o     | | /   o\\_____\\\n"+
    "\t     |   o   |o/ \\o   /o    /\n"+
    "\t     |     o |/   \\ o/  o  /\n"+
    "\t     '-------'     \\/____o/";

  public static String welcome = 
    "__   __  ___   _   _  _____  ______ _____  _____ \n"+
    "\\ \\ / / / _ \\ | | | ||_   _||___  /|  ___||  ___|\n"+
    " \\ V / / /_\\ \\| |_| |  | |     / / | |__  | |__  \n"+
    "  \\ /  |  _  ||  _  |  | |    / /  |  __| |  __| \n"+
    "  | |  | | | || | | |  | |  ./ /___| |___ | |___ \n"+
    "  \\_/  \\_| |_/\\_| |_/  \\_/  \\_____/\\____/ \\____/ ";

  public static void printWelcome(){
    int depth = 0;
    int height = 0;
    for(int i = 0; i < welcome.length(); i++){
      depth++;
      if(welcome.charAt(i)=='\n'){
        depth=0;
        height++;
      }
      int color = 0;
      if(depth+2*height < 20)
        color = 0;
      else if(depth+2*height < 35)
        color = 1;
      else if(depth+2*height < 50)
        color = 2;
      else 
        color = 3;
      System.out.print(colors[color]+welcome.charAt(i)+ANSI_RESET);
    }
    System.out.println("\n");
    depth = 0;
    height = 0;
    for(int i = 0; i < dice.length(); i++){
      depth++;
      if(dice.charAt(i)=='\n'){
        depth=0;
        height++;
      }
      int color = 0;
      if(depth+2*height < 15)
        color = 1;
      else if(depth+2*height < 27)
        color = 2;
      else 
        color = 3;
      System.out.print(colors[color]+dice.charAt(i)+ANSI_RESET);
    }
    System.out.println(ANSI_GREEN+"\n\n\t\tDominic Parolin\n\n\n"+ANSI_RESET);

  }
}
