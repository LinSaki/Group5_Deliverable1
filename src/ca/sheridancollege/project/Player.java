/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 * Each player holds a unique set of cards every time they play a game.
 * A parent class of the Dealer() and UserPlayer()
 * @author dancye
 * @modified by Kaitlin Saqui, August 2023
 */
public abstract class Player {

    private String name; //the unique name for this player
    private Hand hand; //uniqe hand of cards a player holds

    /**
     * A 2-arg constructor that allows you to set the player's unique ID
     *
     * @param name the unique ID to assign to this player.
     * @param hand
     */
    public Player(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    /**
     * @return the player name
     */
    public String getName() {
        return name;
    }

    /**
     * Ensure that the playerID is unique
     *
     * @param userName the player name to set
     */
    public void setName(String userName) {
        name = userName;
    }
    
    /**
     * @return the hand
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * @param hand the hand to set
     */
    public void setHand(Hand hand) {
        this.hand = hand;
    }
   
    
    @Override
    public String toString(){
        return String.format("%s has this hand of cards:\n%sWith a value of: %d\n", this.name, this.hand,
                this.hand.valueOfHand());
    }

}
