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

  public void getKeepers(Roll roll, int rollNum){}

  public int getHand(Roll dice, int rollNum){
    return 0;
  }

  public int sumTop(){
    int sum = 0;
    for(int i = 0; i < 6; i++){
      sum += score[i];
    }
    return sum;
  }
  public int sumBottom(){
    int sum = 0;
    for(int i = 6; i < score.length; i++){
      sum += score[i];
    }
    return sum;
  }

  public int sumScores(){
    int sum = 0;
    for(int i = 0; i < score.length; i++){
      sum += score[i];
    }
    return sum;
  }

}
