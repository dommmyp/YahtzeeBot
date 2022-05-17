package YahtzeeBot.app.game;

public class Player {
  public int playerNum;
  public int[] score;
  public boolean[] open;

  
  public Player(int num){
    this.playerNum = num;
    this.score = new int[13];
    this.open = new boolean[13];
    for(int i = 0; i < 13; i++){
      this.score[i]=0;
      this.open[i] = true;
    }
  }

}
