package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Child class of abstract parent class Game
 * @author saquika June 2023
 */
public class BlackJackGame extends Game{ // child of Game class
    private GroupOfCards deck; 
    private GroupOfCards deckDiscard; 
    private UserPlayer player;
    private Dealer dealer;
    private Hand playerHolds;
    private Hand dealerHolds;
    Scanner input = new Scanner(System.in);

/**
 * Constructor that sets up the game and keeps track of the Players in the game
 * @param gameName 
 */  
public BlackJackGame(String gameName) {
    super(gameName);
// Create Player list , ask user for name, and tell user who they're facing
    ArrayList<Player> players = new ArrayList();
    playerHolds = new Hand();
    dealerHolds = new Hand();
    System.out.println("What is your username?");
    String userName = input.nextLine();
    player = new UserPlayer(userName, playerHolds);
    dealer = new Dealer("Dealer", dealerHolds); // assigns dealer object name Dealer
    System.out.println(player.getName() + " you will be against the " + dealer.getName());
    players.add(player);
    players.add(dealer);
    this.setPlayers(players); // add ArrayList of players into the Game
    player.setUserMoney(100); // sets money to user
    
    //Set up a shuffled deck of cards
    deckDiscard = new GroupOfCards();
    deck = new GroupOfCards();
    deck.generateFullDeck();
    deck.shuffle();
    
    }
/**
 * Overwritten method to start playing this game! Dealer starts by handing out the cards
 */ 
@Override
public void play() {
    System.out.println("\nLets play!");
    System.out.println(player.getName() + " currently has this amount of money: " + player.getUserMoney() +
    "\nHow much would you like to bet?");
    double userBet = input.nextDouble();
    player.userBets(userBet);
    System.out.println("You bet:" + userBet + ". You now have: "+ player.getUserMoney());
    System.out.println("Dealer hands out two cards to each player...");
    dealerHolds.pickCard(deck);
    dealerHolds.pickCard(deck);
    playerHolds.pickCard(deck);
    playerHolds.pickCard(deck);
    System.out.println(dealer.getFirstHand() + "\n" + player);
    
    // what if a Player picks a value of 21 in their hands right away
    if(dealer.isBlackJack()){
        System.out.println(dealer);
        
        if(player.isBlackJack()){ //if both players have BlackJack
            System.out.println("Both players have Black Jack! TIED.");
            player.setUserMoney(userBet + player.getUserMoney()); // give back bet to player
            System.out.println("You take home " + player.getUserMoney());
            playAgain();
        }
        
        else { // Only Dealer gets blackjack
            System.out.println("BLACK JACK! " + this.declareWinner(dealer));
            System.out.println("You take home " + player.getUserMoney());
            playAgain();
        }
    }
    else if (player.isBlackJack()){
        System.out.println("BLACK JACK! " + this.declareWinner(player));
        player.setUserMoney((userBet * 1.5) + player.getUserMoney()); //Player gets 1 1/2 of their bet back
        System.out.println("You take home " + player.getUserMoney());
        playAgain();
    }
    
    // If no BlackJack, ask player if they'd like to hit or stand
    player.hitOrStand(deck, deckDiscard);
    if (playerHolds.valueOfHand() > 21){
        System.out.println("You take home " + player.getUserMoney());
        playAgain();
    }
    else if (player.isBlackJack()){
        player.setUserMoney((userBet * 1.5) + player.getUserMoney());
        System.out.println("You take home " + player.getUserMoney());
        playAgain();
    }
    else{
        System.out.println("Dealers turn\n"); //Dealers turn
        do {
       if (dealerHolds.valueOfHand() < 17){
           System.out.println("Dealer picks up a card.");
           dealerHolds.pickCard(deck);
       }
       else{
           System.out.println("Dealer holds.");
           whoWon(userBet);
       }
       whoWon(userBet);
       }while(dealerHolds.valueOfHand() <17);
    }
    
    dealerHolds.emptyHand(deck);
    playerHolds.emptyHand(deck);
}
    

    /**
     * Method to determine the winner
     * @param userBet
     */ 
    public void whoWon(double userBet){
    if(dealerHolds.valueOfHand() > 21){
        System.out.println(dealer);
        System.out.println("Dealer BUSTS. " + this.declareWinner(player));
        player.setUserMoney((userBet * 1.5) + player.getUserMoney());
        System.out.println("You take home " + player.getUserMoney());
        playAgain();
    }
    else if(dealerHolds.valueOfHand() > playerHolds.valueOfHand()) {
        System.out.println(dealer);
        System.out.println(this.declareWinner(dealer) + " You lost!");
        System.out.println("You take home " + player.getUserMoney());
        playAgain();
    }
    else if(playerHolds.valueOfHand() > dealerHolds.valueOfHand()){
        System.out.println(dealer);
        System.out.println(this.declareWinner(player) + " YOU WON!");
        player.setUserMoney((userBet * 1.5) + player.getUserMoney());
        System.out.println("You take home " + player.getUserMoney());
        playAgain();
    }
    else{
        System.out.println(dealer);
        System.out.println("Dealer has a value of: "+ dealerHolds.valueOfHand() + "\n" + player.getName() +
                " has a value of: " + playerHolds.valueOfHand() + ". Therefore, its a TIE!");
        player.setUserMoney(userBet + player.getUserMoney()); // give bet back bet to player
        System.out.println("You take home " + player.getUserMoney());
        playAgain();
    }
    }

    @Override
    public String declareWinner(Player player) {
        return String.format("%s has won!", player.getName());
    }
    
    public void playAgain() {
        System.out.println("\nDo you want to play again? 1 for Yes, 2 for No");
        int userChoice = input.nextInt();
        switch (userChoice) {
            case 1:
                dealerHolds.emptyHand(deck);
                playerHolds.emptyHand(deck);
                play();
                break;
            case 2:
                System.out.println("Thanks for playing, goodbye!");
                System.exit(0); //End game if user does not want to play anymore
                break;
        }
    }
}
