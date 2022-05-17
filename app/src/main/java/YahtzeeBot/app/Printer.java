package YahtzeeBot.app;

public class Printer {

  public static void printScore(Player p) {
    for(int i = 0; i < game.inputList.length(); i++)
      System.out.print(game.inputList.charAt(i)+" ");

    System.out.println();

    for (int x : p.score)
      System.out.print(x + " ");

    System.out.println();
  }

  public static void printRoll(Roll roll) {
    for (int i = 0; i < roll.dice.length; i++) {
      System.out.print(roll.dice[i].val + " ");
    }
    System.out.println("\n");
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
}
