/* 
 * Class for method to read and write info from files
 * specifically info about weights for optimizing decisions
 */



package YahtzeeBot.app.game;


import java.io.*;
import java.util.Scanner;

import YahtzeeBot.app.bot.ForceBot;
import YahtzeeBot.app.bot.ForceHelper;

public class FileStuff {


  public static void writeData(String fileName, double[] contents){
    try {
      FileWriter w = new FileWriter(fileName);
      
      for(int i = 0; i < contents.length; i++){
        w.write(contents[i]+"\n");
      }
      w.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
  
  public static double[] readData(String fileName){
    try {
      File myObj = new File(fileName);
      Scanner myReader = new Scanner(myObj);
      double[] input = new double[16];
      int index = 0;
      while (index < 16) {
        String data = myReader.nextLine();
        input[index++] = Double.parseDouble(data);
      }
      myReader.close();
      return input;
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return null;
  }
}
