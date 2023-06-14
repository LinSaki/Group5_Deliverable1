package ca.sheridancollege.project;
/**
 * Child class of Player, a Dealer hands out the cards and is against the UserPlayer
 * @author saquika
 */
public class Dealer extends Player{

    public Dealer(String name, Hand hand) {
        super(name, hand);
    }
    
    public String getFirstHand(){
        return String.format("\n%s has this hand of cards:\n%s\nWith one card face down.\n", super.getName(),
                super.getHand().grabCard(0));
    }
}
