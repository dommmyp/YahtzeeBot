package YahtzeeBot.app;

public class Player {
  int playerNum;
  int[] score;
  boolean[] openRolls;

  
  public Player(int num){
    this.playerNum = num;
    score = new int[13];
    openRolls = new boolean[13];
    for(int i = 0; i < 13; i++){
      score[i]=0;
      openRolls[i] = true;
    }
  }

}
