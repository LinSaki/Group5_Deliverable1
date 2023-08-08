package ca.sheridancollege.project;


/**
 * Main class to run the game
 * @author Kaitlin Saqui, August 2023
 */
public class PlayGame {
    public static void  main(String[] args) {
       try{

           // Create the Game object Black Jack, and greet user
           System.out.println("Welcome, you are about to play Black Jack !" );
           BlackJackGame blackJack = new BlackJackGame("Black Jack");
       
           // start the game
           blackJack.play();

       }
       catch (Exception ex){
           System.out.println("\n" + ex.getMessage() + "\n");
       }
       
       
       
   } // ends main method
    
}
