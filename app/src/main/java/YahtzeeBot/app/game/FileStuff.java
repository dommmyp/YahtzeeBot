package YahtzeeBot.app.game;


import java.io.*;
import java.util.Scanner;

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
      while (myReader.hasNextLine()) {
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
