package ca.sheridancollege.project;

public class BlackJackCard extends Card{
    
    public BlackJackCard(Suit userSuit, Value userValue) {
        super(userSuit, userValue);
        
    }
    
    @Override
    public String toString() {
        return String.format("%s of %s", this.getValue(), this.getSuit() );
    }
    
}
