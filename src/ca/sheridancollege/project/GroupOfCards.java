/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you might want to subclass this more than
 * once. The group of cards has a maximum size attribute which is flexible for reuse.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 * @modified by:ManpreetKaur:991680973 date 5th August,2023.
 */
public class GroupOfCards {

    //The group of cards, stored in an ArrayList
    private ArrayList<HandCard> cards;
    private int size;//the size of the grouping
    
    

    public GroupOfCards(int size) 
    {
        this.size = size;
        this.cards= new ArrayList<>();
        
    }

    /**
     * A method that will get the group of cards as an ArrayList
     *
     * @return the group of cards.
     */
    public ArrayList<HandCard> getCards() {
        return cards;
    }
    
    
    public ArrayList<HandCard> shuffle() 
    {
        Collections.shuffle(cards);
        return cards;
    }

    /**
     * @return the size of the group of cards
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the max size for the group of cards
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    
    public ArrayList<String> String()
    {
        ArrayList <String> stringRepresentationOfCards= new ArrayList<>();
        for (int i = 1; i <= this.size ; i ++)
        {
          for( HandCard card: cards)
          {
            stringRepresentationOfCards.add(card.toString());
          }
        }
        return stringRepresentationOfCards;
    }

}//end class
