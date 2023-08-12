/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author by:ManpreetKaur:991680973 date 5th August,2023.
 * @modified by:Kaitlin Saqui : 991723734 : date 11th August,2023.
 */
public class BlackjackPlayer extends Player
{
   private ArrayList<HandCard> hand;
   private double bet;
   private double playerMoney=0; //keeps track of how much the player takes home at the end of a game
   
   /**
    * 2-arg constructor of Blackjack Player
    * @param name
    * @param bet 
    */
   public BlackjackPlayer(String name, double bet)
   {
       super(name);
       hand = new ArrayList<>();
       this.bet = bet;
   }

    /**
     * gives the cards in a players hand
     * @return the hand
     */
    public ArrayList<HandCard> getHand()
    {
        return hand;
    }
    
     /**
     * returns players original bet
     * @return the bet
     */
    public double getBet() {
        return bet;
    }

    /**
     * the bet to set
     * @param bet
     */
    public void setBet(double bet) {
        this.bet = bet;
    }

    /**
     * Get the amount of money the player gets to take home
     * @return 
     */
    public double getPlayerMoney(){
        return playerMoney;
    }
    
    /**
     * Set the amount of money the player gets to take amount
     * @param amountWon 
     */
    public void setPlayerMoney(double amountWon){
        this.playerMoney = amountWon;
    }
    
    /**
     * calculates the total value of the player's hand
     * @return value of cards in a player's hand
     * @throws IOException 
     */
    public int getHandValue()throws IOException
    {
        int totalValue = 0;
        int aceCount = 0; // cannot have  more than 4 aces in a hand
        for(HandCard aCard: hand){
            switch(aCard.getValue()){ //assign int values to each card
                case TWO: totalValue += 2;
                    break;
                case THREE: totalValue += 3;
                    break;
                case FOUR: totalValue += 4;
                    break;
                case FIVE: totalValue += 5;
                    break;
                case SIX: totalValue += 6;
                    break;
                case SEVEN: totalValue +=7;
                    break;
                case EIGHT: totalValue += 8;
                    break;
                case NINE: totalValue += 9;
                    break;
                case TEN: totalValue += 10;
                    break;
                case JACK: totalValue += 10;
                    break;
                case QUEEN: totalValue += 10;
                    break;
                case KING: totalValue +=10;
                    break;
                case ACE:
                    aceCount++;
                    for(int i=0;i<aceCount;i++){ // cannot have more than 4 aces
                        if(totalValue > 10){
                            totalValue+= 1;
                        }
                        else{
                            totalValue+= 11;
                        }
                    }
                    break;
            }
        }
        return totalValue;
    }
    
    /**
     * method to let the player choose to Hit or Stand until they Bust, win Blackjack or Stand
     * @param game
     * @throws IOException 
     */
    public void play(BlackjackGame game)throws IOException
    {
       boolean redFlag=false;
       do
       {
           try {
               System.out.println(getName() + " turn: ");
               
               game.printCardsInHand(this);
               System.out.println(getName()+ " wants to hit or stand:") ;
               System.out.println("Enter H to hit and S to stand") ;
               Scanner sc = new Scanner(System.in);
               String c= sc.next();
               c= c.toUpperCase();
               switch(c)
               {
                   case "H":
                       System.out.println(getName()+" chooses to hit.");
                       game.dealCard(this);
                       game.printCardsInHand(this);
                       System.out.println("");
                       int count = game.declareWinner(this);
                       if(count == -1)
                       {
                           redFlag=false;
                       }
                       System.out.println("");
                       break;
                   //Once a player stands, they are not allowed to play the rest of the round
                   case "S":
                       System.out.println(getName()+" chooses to Stand.");
                       System.out.println("");
                       redFlag=false;
                       break;

                   default:
                       System.out.println("Invalid choice. Choose again. Please try again");
                       System.out.println("");
                       redFlag= true;
                       break;
               }
           } 
           catch (Exception ex) 
           {
               Logger.getLogger(BlackjackPlayer.class.getName()).log(Level.SEVERE, null, ex);
           }
       }while(redFlag);

    }
   
    /**
     * Method used to empty hand of the player
     * @param cards
     */
    public void emptyHand(GroupOfCards cards){
        hand.clear();
    }

    /**
     * Override the toString from parent class Player()
     * @return player details
     */
     @Override
    public String toString()
    {
       return ("Player Name: " + getName() + "  Placed Bet: " +getBet()); 
    }

   
}
