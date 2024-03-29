/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author by:ManpreetKaur:991680973 date 5th August,2023.
 * @modified by: Kaitlin Saqui : 991723734 : date August 11th, 2023
 */
public class HandCard extends Card
{
      private Value value;
      private Suit suit;
      
      public HandCard(Value value , Suit suit )
      {
          this.value = value;
          this.suit = suit;
      }
    /**
     * @return the value of the card
     */
    public Value getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Value value) {
        this.value = value;
    }

    /**
     * @return the suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * @param suit the suit to set
     */
    public void setSuit(Suit suit) {
        this.suit = suit;
    }
    
    //public HandCard getHandCard()
    
    /**
     * Override the toString() method from parent class Card() to return the card's value and suit
     * @return card as a string
     */
     @Override
      public String toString()
      {
          return ("Card: Value: " +getValue() + ", Suit: " + getSuit());
      }

   
}
