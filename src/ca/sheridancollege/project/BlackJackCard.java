package ca.sheridancollege.project;

/**
 * Child class of Card. A blackjack card calculates the 
 *
 * @author Kaitlin Saqui, August 2023
 */
public class BlackJackCard extends Card{
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
    public BlackJackCard (Suit userSuit, Value userValue){
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
    
    @Override
    public String toString() {
        return String.format("%s of %s", this.getValue(), this.getSuit() );
    }
    
}
