/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author 13435
 * @modified by:ManpreetKaur:991680973 date 5th August,2023.
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

    
    public int getHandValue()throws IOException
    {
        int handValue=0;
        for (HandCard card: hand)
        {
          if( null == card.getValue() )  
          {
              System.out.println("Has no cards in hand.");
          } 
          else switch (card.getValue()) {
                case JACK:
                case QUEEN:
                case KING:
                    handValue = handValue + 10;
                    break;
                case ACE:
                    System.out.println(getName()+" have " + getHand() + "\n What value you need to use for Ace 1 or 11"  );
                    Scanner sc = new Scanner(System.in);
                    System.out.println("");
                    boolean redFlag= false;
                    try
                    {
                        do
                        {
                            int valueOfAce = sc.nextInt();
                            System.out.println("");
                            switch (valueOfAce)
                            {
                                case 1:
                                    handValue = handValue +1;
                                    redFlag= false;
                                    
                                    break;
                                case 11:
                                    handValue = handValue +11;
                                    redFlag= false;
                                    break;
                                default:
                                    System.out.println("You didn't enter the correct value. Please Enter the correct value");
                                     redFlag = true;
                                    break;
                            }
                        }while(redFlag);
                    }
                    catch(Exception ex)
                    {
                        System.out.println("InputMismatch Exception");
                    }       break;
                default:
                    // Handle numbered cards (2 to 10)
                    handValue = handValue + card.getValue().ordinal() +1;
                    break;
            }
        }
        return handValue;  
        
    }
  
    @Override
    public void play()
    {
       boolean redFlag=false;
       do
       {
           System.out.println(getName()+ " wants to hit or stand:") ;
           System.out.println("Enter H to hit and S to stand") ;
           Scanner sc = new Scanner(System.in);
           String c= sc.next();
           c= c.toUpperCase();
           switch(c)
           {
                case "H":
                System.out.println(getName()+" chooses to hit.");
                BlackjackGame game = new BlackjackGame();
                game.dealCard(this);
                System.out.println(getHand());
                System.out.println("");
                int count = game.declareWinner(this);
                if(count == -1)
                {
                  redFlag=false;  
                }
                 System.out.println("");
                break;
                
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
       }while(redFlag);
       
    }
   

     @Override
    public String toString()
    {
       return ("Player Name: " + getName() + "  Placed Bet: " +getBet()); 
    }

   
}
