package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * Each Player's (UserPlayer and Dealer) unique hand of cards
 * @author saquika
 */
public class Hand {
    private ArrayList<BlackJackCard> hand;
  
     /**
     * no-arg constructor with and empty hand of cards
     */
    public Hand(){
        hand = new ArrayList<BlackJackCard>(); // create empty hand of cards
    }
    
     /**
     * Method used to call method from GroupOfCards to pick a card from that deck into their Hand
     * @param cards
     */
    public void pickCard(GroupOfCards cards){
        hand.add(cards.takingCardFromDeck());
    }
    
     /**
     * Method used to grab any card in the deck
     * @param i for index
     * @return grab a card from the deck
     */
    public BlackJackCard grabCard(int i){
        return hand.get(i);
    }
    
     /**
     * Method used to empty hand
     * @param cards
     */
    public void emptyHand(GroupOfCards cards){
        hand.clear();
    }
    
     /**
     * Method used to calculate the value of cards a Player has in their hand
     * @return value of cards in Hand
     */
    public int valueOfHand(){
        int totalValue = 0;
        int aceCount = 0; // cannot have  more than 4 aces in a hand
        for(BlackJackCard aCard: hand){
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
    public String toString(){
        String cardInHand ="";
        for(BlackJackCard aCard: hand) {
            cardInHand += aCard + "\n";
        }
        return cardInHand;
    }
}
