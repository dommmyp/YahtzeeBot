package YahtzeeBot.app;

public class Printer {
  
  public static void printScore(Player p){
    for(int x : p.score){
      System.out.println(x);
    }


  }

  public static void printRoll(Die[] roll){
    for(Die d : roll){
      System.out.print(d.val+" * ");
    }
    System.out.println();
  }
}
