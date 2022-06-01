public class zz {
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String[] colors = {ANSI_PURPLE, ANSI_BLUE, ANSI_CYAN, ANSI_GREEN};
  
  public static void main(String[] args){
    System.out.println(ANSI_RED+"colored"+ANSI_RESET);

    String dice = 
    "\t       .-------.    ______\n"+
    "\t      /   o   /|   /\\     \\\n"+
    "\t     /_______/o|  /o \\  o  \\\n"+
    "\t     | o     | | /   o\\_____\\\n"+
    "\t     |   o   |o/ \\o   /o    /\n"+
    "\t     |     o |/   \\ o/  o  /\n"+
    "\t     '-------'     \\/____o/";

    String welcome = 
    "__   __  ___   _   _  _____  ______ _____  _____ \n"+
    "\\ \\ / / / _ \\ | | | ||_   _||___  /|  ___||  ___|\n"+
    " \\ V / / /_\\ \\| |_| |  | |     / / | |__  | |__  \n"+
    "  \\ /  |  _  ||  _  |  | |    / /  |  __| |  __| \n"+
    "  | |  | | | || | | |  | |  ./ /___| |___ | |___ \n"+
    "  \\_/  \\_| |_/\\_| |_/  \\_/  \\_____/\\____/ \\____/ ";
                                                 
                                                 


                                                                                                                          

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
    System.out.println();
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
    System.out.println();
  }

}
