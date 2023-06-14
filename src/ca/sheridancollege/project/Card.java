/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

/**
 * A class to be used as the base Card class for the project. Must be general enough to be instantiated for any Card
 * game. Students wishing to add to the code should remember to add themselves as a modifier.
 * Parent abstract class of BlackJackCard
 * @author dancye
 * @modified by Kaitlin Saqui, June 2023
 */
public abstract class Card {
    private Suit suit;
    private Value value;
    
    public enum Suit {
        SPADES, DIAMONDS, CLUBS, HEARTS
    }
    public enum Value {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }
    
       /**
     * 2 argument constructor
     * @param userSuit
     * @param userValue 
     */
    public Card (Suit userSuit, Value userValue){
        value = userValue;
        suit = userSuit;
    }

    /**
     * @return the suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * @param userSuit the suit to set
     */
    public void setSuit(Suit userSuit) {
        suit = userSuit;
    }

    /**
     * @return the value
     */
    public Value getValue() {
        return value;
    }

    /**
     * @param userValue the value to set
     */
    public void setValue(Value userValue) {
        value = userValue;
    }
    
        //default modifier for child classes

    /**
     * Students should implement this method for their specific children classes
     * overridden in the child class BlackJackCard
     * @return a String representation of a card. Could be an UNO card, a regular playing card etc.
     */
    @Override
    public abstract String toString();

}
