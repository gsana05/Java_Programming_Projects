package BlackJackCardGame;

import java.util.Scanner;

public class BlackJackMain
{
    public static void main (String args[])
    {
        System.out.println("Welcome to blackJack"); //welcome

        // OWN ARRAYLIST
        Deck playingDeck = new Deck(); //instantiate DECK CLASS with the name "PLAYING DECK" - (Making object of Deck class)
        playingDeck.createFullDeck(); // calling method in Deck class - creates NOT shuffled deck
        //System.out.println(playingDeck); // NOT shuffled deck displayed

        playingDeck.shuffle(); // calling method in DECK CLASS - Shuffles deck randomly
        //System.out.println(playingDeck); //shuffled deck displayed

        // OWN ARRAYLIST
        Deck playerDeck = new Deck(); //instantiate DECK CLASS with the name "PLAYER DECK" - (Making object of Deck class)

        // OWN ARRAYLIST
        Deck dealerDeck = new Deck(); //instantiate DECK CLASS with the name "DEALER DECK" - (Making object of Deck class)

        Scanner userInput = new Scanner(System.in); // take user input
        AccountBalance gameMoney = new AccountBalance(); //instantiate ACCOUNTBALANCE CLASS with the name "GAME MONEY" - (Making object of Deck class)

        double addToplayerMoney = 0.0; // variable to add deposit extra money after each hand
        double playerMoney = gameMoney.money(); // returning a value from (accountBalance)gameMoney class in method "MONEY"

        while(playerMoney > 0) // keeps looping util playerMoney is 0 OR player chooses to exit game "PLAY AGAIN CLASS"
        {
            playerMoney = playerMoney + addToplayerMoney; // updates player money double variable

            PlayerBets playerBetsss = new PlayerBets(playerMoney); //instantiate PLAYERBETS CLASS with the name "PLAYERBETSSS"-(Making object of Deck class)
            double playerBet = playerBetsss.bets(); // returning the playerBet value from (playerBet)playerbetsss class in method "BETS"

            boolean endRound = false; //

            for(int i = 0; i < 2; i++) // loops twice to distribute two cards to dealer and two cards to player
            {
                playerDeck.draw(playingDeck); // 1st card // 3rd card
                dealerDeck.draw(playingDeck); // 2nd card // 4th card
            }

            while(true) // loops forever until it hits "break;" statement which exits loop // important as we do not know the number of hits/draws
            {
                System.out.println("Your Hand : ");
                System.out.println(playerDeck.toString()); // displays cards
                System.out.println("Your hand is valued at : " + playerDeck.cardsValue()); // displays values of cards added together

                System.out.println("Dealer Hand : " + dealerDeck.getCard(0).toString() + " and [hidden"); // display first card of Dealer Hand

                // What does the player want to do?
                System.out.println("Would you like to (1)hit or (2)stand?");
                if(userInput.hasNextInt()) // checking int has been entered
                {
                    System.out.println("number was enter for hit or stand ");
                    int response = userInput.nextInt(); // if int entered put into int variable

                    // they hit
                    if(response == 1) { // compare variable with 1 (HIT), if true do this
                        System.out.println("HIT");
                        //userInput.next();
                        playerDeck.draw(playingDeck); // draws another card for player hand
                        System.out.println("You draw a: " + playerDeck.getCard(playerDeck.deckSize() -1 ).toString()); // displays newly drawn card

                        // bust - over 21
                        if(playerDeck.cardsValue() > 21) // if players busts whilst hitting - end hand player loses
                        {
                            System.out.println("BUST. Currently valued at " + playerDeck.cardsValue());
                            playerMoney -= playerBet; // subtracts playerBet from playerMoney
                            endRound = true; // ends loop
                            break; // end loop
                        }
                    }
                    else if(response == 2) // compare variable with 2 (STAND)
                    {
                        System.out.println("STAND");
                        break; // exit loop
                    }
                    else //invalid NUMBER input go back round loop
                    {
                        System.out.println("invalid number input - please try again ");
                    }
                }
                else //invalid STRING input go back round loop
                {
                    System.out.println("invalid input - no strings! please enter numbers 1 or 2");
                    userInput.next(); // stops this else looping forever and goes back to the top of loop
                    // next() - ends after whitespace and goes to the next word separated by whitespace
                }
            }

            //**** B L A C K J A C K ****//
            // if player does NOT hit OR Dealers two cards equal 21 DO THIS THEN
            // track number of player cards drawn plus the original two cards that are automatically dealt
            int numberOfPlayerCards = 0; // counting number of player cards
            for(int i = 0; i < playerDeck.deckSize(); i++) // looping through player hand of cards
            {
                numberOfPlayerCards++; // each card add 1 to variable
            }
            System.out.println("The number of player cards dealt " + numberOfPlayerCards); // display number of player cards

            // Does player have blackjack? Only enter if player has 2 cards that equal 21 if so BLACKJACK
            if((numberOfPlayerCards == 2) && (playerDeck.cardsValue() == 21))
            {
                System.out.println("PLAYER HAS BLACKJACK");
                System.out.println("Dealer Cards : " + playerDeck.toString()); // show dealer cards // dealer ONLY HAS 2 CARDS at this point
                if(dealerDeck.cardsValue() == 21) // does dealer have blackjack?
                {
                    // show dealer cards
                    System.out.println("Dealer Cards : " + dealerDeck.toString()); // display dealer hand
                    System.out.println("DEALER HAS BLACKJACK");
                }
                else if((playerDeck.cardsValue() == 21) && dealerDeck.cardsValue() == 21) // if they both have blackjack PUSH
                {
                    System.out.println("Player and Dealer BOTH have BLACKJACK - its a PUSH!");
                    endRound = true; // end round go back round loop
                }
                else // if player has blackjack and dealer does not have blackjack - PLAYER WINS
                {
                    System.out.println("PLayer Wins with BLACJACK ");
                    double bjPayout = playerBet + (playerBet / 2); // e.g bet £10 wins £25
                    playerMoney += bjPayout; // put money into playerMoney
                    endRound = true; // end round go back round loop
                }
            }
            // DEALER BLACK JACK
            // Dealer only has two cards at this point when showing and one hiding
            if((dealerDeck.cardsValue() == 21) && (numberOfPlayerCards >= 3)) // dealer has 2 cards = 21 and player has drawn 3 or more cards
            {
                // show dealer cards
                System.out.println("Dealer Cards : " + dealerDeck.toString()); // display dealer hand
                System.out.println("Dealer wins with BLACKJACK");
                playerMoney -= playerBet; // subtract money from playerMoney
                endRound = true; // end round go back round loop
            }
            //**** END OF B L A C K J A C K ****//
            ///////////////////////////////////////////////////////////////////
            // show dealer cards
            System.out.println("Dealer Cards : " + dealerDeck.toString()); // display dealer hand

            // this stage playerDeck total value has been confirmed and only one card of dealerDeck has been displayed
            // when dealer flips second card and if dealerDeck value is greater than playerDeck then DEALER WINS!!!

            // see if dealer has more points than player - once player decides to stand and dealer flips over second card
            if((dealerDeck.cardsValue() > playerDeck.cardsValue()) && !endRound) {
                System.out.println("Dealer beats you!");
                playerMoney -= playerBet;
                endRound = true;
            }

            // if dealerDeck value is not greater than playerDeck AND dealerDeck is less than 17 - dealerDeck needs to keep
            // hitting/drawing cards
            // dealer keeps drawing until hand value is a minimum of 17
            while((dealerDeck.cardsValue() < 17)&& !endRound) {
                dealerDeck.draw(playingDeck); // dealer draws card
                System.out.println("Dealer draws: " + dealerDeck.getCard(dealerDeck.deckSize() -1 ).toString()); // display card drawn by dealer
            }

            // display total value for dealer
            System.out.println("Dealer hand is valued at " + dealerDeck.cardsValue() + " Players hand valued at " + playerDeck.cardsValue());

            // This stage Both playerDeck and dealerDeck have both confirmed values
            // time to compare "playerDeck VS dealerDeck" values

            // determine if dealer bust
            if((dealerDeck.cardsValue() > 21) && !endRound) {
                System.out.println("Dealer busts, you win");
                playerMoney += playerBet; // add money
                endRound = true; // end loop round
            }

            //determine if push / draw
            if((playerDeck.cardsValue() == dealerDeck.cardsValue()) && !endRound)
            {
                System.out.println("PUSH");
                endRound = true; // end loop round
            }

            // if player hand value is greater than dealer hand value
            if((playerDeck.cardsValue() > dealerDeck.cardsValue()) && !endRound) {
                System.out.println("You WIN the hand");
                playerMoney += playerBet; // add money
                endRound = true; // end loop round
            }
            else if(!endRound) // dealer wins
            {
                System.out.println("YOU LOSE the hand");
                playerMoney -= playerBet; // subtract money
                endRound = true; // end loop round
            }

            // hand is complete - put player and dealer cards back into the deck
            playerDeck.moveAllToDeck(playingDeck);
            dealerDeck.moveAllToDeck(playingDeck);

            System.out.println("End of hand");

            // Do you want to play again
            PlayAgain playAgain = new PlayAgain(playerMoney); //instantiate PLAYAGAIN CLASS with the name "playAgain" - (Making object of Deck class)
            String playing = playAgain.play(); // String return value from plaAgain method "play"
            if(playing.equals("N")) // if the return value is N = EXIT GAME
            {
                System.out.println("EXITING GAME");
                //playerMoney = 0;
                return; // exit game
            }

            // Do you want to deposit anymore money
            //instantiate ACCOUNTBALANCE CLASS with the name "addMoreGameMoney" - (Making object of Deck class)
            // and pass in "playerMoney" into constructor so the value can be manipulated
            AccountBalance addMoreGameMoney = new AccountBalance(playerMoney);
            addToplayerMoney = addMoreGameMoney.money(); // putting the return value into variable called "addToplayerMoney" defined at top just before loop
            System.out.println("Add to player money " + addToplayerMoney); // displaying the amount of extra money you are depositing

        }
        System.out.println("You are out of money"); // when "playerMoney == 0" display this
    }
}
