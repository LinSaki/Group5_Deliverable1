/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author by:ManpreetKaur:991680973 date 5th August,2023
 * @modified by:Kaitlin Saqui :991723734 date 11th August,2023.
 */
public class BlackjackGame extends Game
{
    
    final int BLACKJACK_VALUE=21;
    final int DEALER_MIN_VALUE=17;
    ArrayList<HandCard> deck = new ArrayList<>();
    
    
    // Constructor to get the name of the Game and call the constructor of the base class.        
    public BlackjackGame()  
    {
      super("BLACKJACK CARD GAME");
      initializeDeck();
    }
    
     /**
     * main method
     * @param args
     */   
    public static void main(String[] args)
    {
        BlackjackGame game = new BlackjackGame();
        Scanner sc = new Scanner (System.in);
        boolean redFlag = false;
        String c="";
        do
        {
        game.play();
        System.out.println("Do you want to play Blackjack Game again");
        System.out.println("Type y to continue and n to end the game");
        c = sc.next();
            switch (c) {
                case "y":
                case "Y":
                    redFlag = true;
                    
                    break;
                case "n":
                case "N":
                    System.out.println("Thank you!");
                    redFlag = false;
                    break;
            }
        }while(redFlag);
    }
    
    private void initializeDeck() 
    {
        for (Suit suit : Suit.values()) 
        {
            for (Value value : Value.values()) 
            {
                deck.add(new HandCard(value, suit));
            }
        }
        Collections.shuffle(deck);
   }

    /**
     *method to play the BlackjackGame. 
     */
    @Override
    public void play()
    {
        //Clear the list of platyers at the start of a new game:
        getPlayers().clear();
        try {
            System.out.println();
            System.out.println(getName() + "\n");
            Scanner sc = new Scanner (System.in);
            int numberOfPlayers=0;
            boolean flag;
            do
            {
                try
                {
                    flag=false;
                    System.out.println("Enter the number of Players for the game : ");
                    numberOfPlayers = sc.nextInt();
                }
                catch(Exception ex)
                {
                    flag=true;
                    System.out.println("Error: " +ex);
                    sc.nextLine();                    
                }
            }while(flag);
            
            
            sc.nextLine();
            for(int i = 0; i < numberOfPlayers; i++)
            {
                boolean redFlag;
                
                do
                {
                    try
                    {
                        redFlag=false;
                        
                        System.out.println("Enter the name of the "+ (i+1) + " Player");
                        String name = sc.nextLine();
                        System.out.println("Enter the bet for the "+ (i+1) + " Player");
                        double bet = Double.parseDouble(sc.nextLine());
                        
                        BlackjackPlayer  player = new BlackjackPlayer(name,bet);
                        getPlayers().add(player);
                        
                    }
                    catch(InputMismatchException ex)
                    {
                        redFlag=true;
                        System.out.println("Error: " +ex);
                        
                    }
                }while(redFlag);
                
               
            }
            
            System.out.println("");
            System.out.println("Total number of players: " +numberOfPlayers);
            System.out.println("");
            
            
            for(BlackjackPlayer player: getPlayers())
            {
                for(int i=1; i<=2 ;i++)
                {
                    dealCard(player);
                }
            }
            
            for(BlackjackPlayer player: getPlayers())
            {
                printCardsInHand(player );
            }
            dealerInitialCards();
            System.out.println("");
            
            if (hasEnoughPlayers() == true) {
                checkForPlayers();
            }
            
        }
        catch (IOException ex) 
        {
            Logger.getLogger(BlackjackGame.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
    
    /**
     *Method to add cards to the player's hand.
     * @param name
     */
    public void dealCard(BlackjackPlayer name)
    {
       if (!(deck.isEmpty())) 
        {
            name.getHand().add(deck.remove(0));
        }
         else 
         {
            System.out.println("No more cards in the deck.");
        }
     }
    
    
    
    /**
     *Method to print the cards in the player's hand
     * Modified: whenever the player's hand is shown, also show the value of their hand
     * @param name
     * @throws java.io.IOException
     */    
    public void printCardsInHand(BlackjackPlayer name ) throws IOException
    {
         System.out.println(name.getName() + " Hand Cards : ");
          for(HandCard card : name.getHand())
          {
             System.out.println(card.toString()); 
          }
          System.out.println("Value of Hand: "+ name.getHandValue());
          System.out.println();
    }
     
    /**
     *
     * @param player
     * @return 
     */
   
    @Override
    public int declareWinner(BlackjackPlayer player)
    {
            try
            { 
                if (isBust(player.getHandValue()))
                {
                    player.setBet(0);
                    
                    System.out.println(player.getName() + " BUSTS and loses the bet. Done for this Game!");
                    System.out.println(player.getName() + " total amount becomes: " + player.getBet() );
                    System.out.println();
                    return -1;
                }
                else if (player.getHandValue() == 21)
                {
                    System.out.println(player.getName() + " HAS BLACKJACK! Wins one and a half times the bet. Done for this Game!");
                    player.setBet((int) (1.5 * player.getBet()) );
                    System.out.println(player.getName() + " total amount becomes: " + player.getBet());
                     System.out.println("");
                    return -1;
                }
                else if (!player.getName().equals("Dealer")) {
                    player.play();
                }
            }
            catch (IOException ex)
            {
                Logger.getLogger(BlackjackGame.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
        }
  
    
    
    public void checkForPlayers() throws IOException {
    ArrayList<BlackjackPlayer> players = getPlayers(); // Copy the list to a local variable
    ArrayList<BlackjackPlayer> playersToRemove = new ArrayList<>(); //keep track of players needed to be removed
    int numPlayers = players.size();
    
    for (int i = 0; i < numPlayers; i++) 
    {
        BlackjackPlayer player = players.get(i);
        int value = 0;

        if (i == numPlayers - 1) 
        {
            dealerTurn();
        } 
        else 
        {
            value = declareWinner(player);
        }

        if (value == -1) {
            playersToRemove.add(player);
        }
    }
    // Remove the players marked for removal
    players.removeAll(playersToRemove);
}
    
    /**
     *method to print the dealer's initialCards.
     * @throws java.io.IOException
     */
    public void dealerInitialCards() throws IOException
    {
     BlackjackPlayer dealer = new BlackjackPlayer("Dealer",0);
     getPlayers().add(dealer);
      for(int i=1; i<=2; i++)
      {
          dealCard(dealer);
      }
      System.out.println("Dealer Hand Cards:");
      System.out.println(dealer.getHand().get(0).toString());
      System.out.println("Dealer's second Card is hidden.");   
    }
    
    public void dealerTurn() throws IOException
    {
        BlackjackPlayer dealer = null;

        for (BlackjackPlayer player : getPlayers()) 
        {
            if (player.getName().equals("Dealer")) {
                dealer = player;
                break; // Once found, exit the loop
            }
        }
        if (dealer != null) 
        {
            printCardsInHand(dealer);
            int handValue = dealer.getHandValue();
            
            // Keep having the dealer pull cards until hand is 17 or above
            while (isDealerBust(handValue) == -1) {
                System.out.println("Dealer handValue is equal to or less than 17. Dealer picks up a card...");
                dealCard(dealer);
                printCardsInHand(dealer);
                handValue = dealer.getHandValue();
            }
           
            switch (isDealerBust(handValue))
            {
                case 1:
                    System.out.println("The dealer's hand value is: " +handValue);
                    System.out.println("Every player with hand value higher than the dealer's, wins twice their bet!");
                    System.out.println("Others loses the bet");
                    System.out.println("");
                    for (BlackjackPlayer player : getPlayers())
                    {
                        if ((!(player.getName().equals("Dealer")))&& (!(isBust(player.getHandValue()))) )  {
                            if(player.getHandValue() > handValue && (!(declareWinner(player)==-1)) ) 
                            {
                                System.out.println(player.getName()+ " has hand Value greater than the dealer's hand value.So wins twice their bet!\n");
                                player.setBet(player.getBet()*2);

                            } 
                             else if (player.getHandValue() == handValue) {
                                System.out.println(player.getName() + " has the same hand value as the dealer. Gets back their bet.\n");
                                player.setBet(player.getBet());
                            } else if (player.getHandValue() < handValue && (!(declareWinner(player)==-1))) {
                                System.out.println(player.getName() + " has hand Value less than the dealer's hand value. Loses their bet!\n");
                                player.setBet(0);
                            }
                        }
                    }
                    break;
                case 0:
                     System.out.println("Dealer Busts every player in the game wins twice their bet.\n");
                    for (BlackjackPlayer player : getPlayers())
                    {
                        if ((!(declareWinner(player)==-1) && (!(player.getName().equals("Dealer")))) ){
                            player.setBet(player.getBet()*2);
                        }
                    }   
                    break;
//                case -1:
//                    System.out.println("Dealer handValue is equal to  or less than 16. Dealer picks up a card...");
//                    
//                        dealCard(dealer);
//                   
//                    dealerTurn();
//                break;
            }//ends switch case
            for (BlackjackPlayer player : getPlayers()){
            //Print every players hand except the Dealer
                if(!(player.getName().equals("Dealer")) ){
                    System.out.println(player.getName()+ " balance becomes: " + player.getBet());
                    System.out.println("");
                }
            }
                    
        }
    }

    
    
    /**
     *method to return 1 if the hand is greater than 16.
     * return -1 if the handValue is 16 or under.
     * return 0 if the dealer busts.
     * @param handValue
     * @return int 0, 1,-1.
     */
    public int isDealerBust(int handValue)
    {
        if(handValue > BLACKJACK_VALUE)
        {
           return 0; 
        }
        else if(handValue >= DEALER_MIN_VALUE)
        {
          return 1;  
        }
        else
        { 
            return -1;
        }
    }
     
    /**
     *Method to return true if there are players available in the game.
     * or otherwise return false.
     * @return Boolean true or false.
     */
     public boolean hasEnoughPlayers()
    {
        if(getPlayers().isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * method to check for bust.
     *@param handValue
     * @return Boolean true or false.
     */
    public boolean isBust(int handValue)
    {
        if(handValue > BLACKJACK_VALUE)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
}
