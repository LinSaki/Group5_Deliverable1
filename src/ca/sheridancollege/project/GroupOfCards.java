/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import ca.sheridancollege.project.BlackJackCard.Suit;
import ca.sheridancollege.project.BlackJackCard.Value;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you might want to subclass this more than
 * once.
 *
 * @author dancye
 * @modified by Kaitlin Saqui, August 2023
 */
public class GroupOfCards {

    //The group of cards, stored in an ArrayList
    private ArrayList<BlackJackCard> cards;

    /**
     * 1-arg constructor
     *@param size
     */
    public GroupOfCards() {
        cards = new ArrayList<BlackJackCard>();
    }

    /**
     * A method that will get the group of cards as an ArrayList
     *
     * @return the group of cards.
     */
    public ArrayList<BlackJackCard> getCards() {
        return cards;
    }

    /**
     * A method that will shuffle the ArrayList of cards at random
     */    
    public void shuffle() {
        Collections.shuffle(cards, new Random());
    }
    
    /**
     * A method to create a full deck of 52 cards
     */
    public void generateFullDeck() {
        for (Suit aSuit : Suit.values()){
            for (Value aValue : Value.values()) {
                this.cards.add(new BlackJackCard(aSuit, aValue));
            }
        }
    }
    
    /**
     * @param card from BlackJackCard class
     */
    public void addCard(BlackJackCard card) {
        cards.add(card);
    }
    
     /**
     * Method for a Player to take a card from the existing deck
     * @return the BlackJackCard to Player
     */
    public BlackJackCard takingCardFromDeck() {
        BlackJackCard cardTaken = cards.get(0); // takes card at the top of the deck and saves a copy
        cards.remove(0); // remove the card copied from the GroupOfCards
        return cardTaken;
    }
    
     /**
     * Method to clear out deck of cards
     */
    public void emptyCards() {
        cards.clear();
    }

    /**
     * override toString()
     */
    @Override
    public String toString(){
        String groupCards ="";
        for(BlackJackCard aCard: cards) {
            groupCards += aCard + "\n";
        }
        return groupCards;
    }
}//end class
