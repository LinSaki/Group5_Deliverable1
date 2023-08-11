/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author by:ManpreetKaur:991680973 date 5th August,2023
 * @modified by:Kaitlin Saqui :991723734 date 11th August,2023.
 */
public class BlackjackPlayer extends Player
{
   private ArrayList<HandCard> hand;
   private double bet;
   
   public BlackjackPlayer(String name, double bet)
   {
       super(name);
       hand = new ArrayList<>();
       this.bet = bet;
   }

    /**
     * @return the hand
     */
    public ArrayList<HandCard> getHand()
    {
        return hand;
    }
    
     /**
     * @return the bet
     */
    public double getBet() {
        return bet;
    }

    /**
     * @param bet the bet to set
     */
    public void setBet(double bet) {
        this.bet = bet;
    }

    
     /**
     * Method used to calculate the value of cards a Player has in their hand
     * Modified: automatically make the value of Ace 1 or 11 depending if the value of the hand is under/more than 10.
     * @return value of cards in Hand
     * @throws java.io.IOException
     */
    public int getHandValue()throws IOException{
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
  
    @Override
    public void play()
    {
       boolean redFlag=false;
       do
       {
           try {
               System.out.println(getName() + " turn: ");
               BlackjackGame game = new BlackjackGame();
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
           } catch (IOException ex) {
               Logger.getLogger(BlackjackPlayer.class.getName()).log(Level.SEVERE, null, ex);
           }
       }while(redFlag);
       
    }
    
      /**
     * Method used to empty hand
     * @param cards
     */
    public void emptyHand(GroupOfCards cards){
        hand.clear();
    }
   

     @Override
    public String toString()
    {
       return ("Player Name: " + getName() + "  Placed Bet: " +getBet()); 
    }

   
}
