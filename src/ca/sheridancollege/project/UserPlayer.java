package ca.sheridancollege.project;

import java.util.Scanner;

/**
 * Child class of Player, a UserPlayer decides how much to bet and whether they will hit or stand
 * @author saquika
 */
public class UserPlayer extends Player{
    // data members
    private double userMoney = 100; // default amount of money at start of the game
    
    Scanner input = new Scanner(System.in);
    
    public UserPlayer(String name, Hand hand) {
        super(name, hand);
    }

    /**
     * @return the userMoney
     */
    public double getUserMoney() {
        return userMoney;
    }

    /**
     * @param userMoney the userMoney to set
     */
    public void setUserMoney(double userMoney) {
        this.userMoney = userMoney;
    }
    
    /**
     * method for when UserPlayer bets money
     * @param bet 
     */
    public void userBets(double bet) {
        if (bet > userMoney){
            throw new IllegalArgumentException("You can not bet more than what you have in your hand, try again!");
        }
        else{
            userMoney -= bet;
        }
    }

     /**
     * method for when UserPlayer wants to hit or stand based on the values of their card
     * @param deck
     * @param deckDiscard
     */
    public void hitOrStand(GroupOfCards deck, GroupOfCards deckDiscard) {
        int cardsInHand =2;
        boolean isHit = true;
        while (isHit) {
            System.out.println("Enter 1 to Hit, or 2 to Stand:");
            int userReply = input.nextInt();
            input.nextLine();//flush stream
            switch (userReply) {
                case 1:
                    cardsInHand++;
                    this.getHand().pickCard(deck);
                    System.out.println("You picked a: " + this.getHand().grabCard(cardsInHand-1) + "\n");
                    if(this.getHand().valueOfHand() >21){
                        System.out.println("BUST. Total in hand is valued at: " + this.getHand().valueOfHand());
                        isHit=false;
                        break;
                    }
                    else if(this.isBlackJack()){
                        System.out.println(this.toString() + "\nBLACK JACK! You won" ); // if you get 21
                        isHit=false;
                    }
                    else {
                        System.out.println(this.toString()); // print new hand of cards
                    }   break;
                case 2:
                    System.out.println("HOLD.");
                    isHit=false;
                    break;
                default:
                    System.out.println("Invalid input, try again");
                    break;
            }
        }
    }
    

    
    
}
