package YahtzeeBot.app;

public class game {
  

  public static void getRoll(Die[] roll){
    for(Die r : roll){
      r.roll();
    }
  }

}
