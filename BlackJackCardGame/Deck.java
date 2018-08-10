package BlackJackCardGame;

import java.security.SecureRandom;
import java.util.ArrayList;

public class Deck {
    //instance
    // arrayList to store cards for: each object has own arrayList = 1. playingDeck 2. playerDeck 3.dealerDeck
    private ArrayList<Card> cards;

    // constructor
    public Deck() {
        this.cards = new ArrayList<Card>(); // putting arrayList into this.cards
    }

    // method - to create deck of cards
    public void createFullDeck()
    {
        // generate acards // values() is a built in method from JVM or compiler NOT part enum or object!!
        for(Suit cardSuit : Suit.values()) { // loop through Suit enum
            for(Value cardValue : Value.values()) { // loop through the Value enum
                //add a new Cards to the mix
                this.cards.add(new Card(cardSuit, cardValue)); // using constructor to add Cards to arrayList
            }
        }
    }

    // shuffles deck
    public void shuffle() {
        ArrayList<Card> tmpDeck = new ArrayList<Card>(); // temp Array to store shuffled cards
        SecureRandom random = new SecureRandom(); // more hard to break code = Random 2^48 AND SecureRandom 2^128 attempts
        int randomCardIndex = 0; // to move the card to the index
        int originalSize = this.cards.size(); // originalSize = 0 to 51 = 52

        for(int i = 0; i < originalSize; i++)
        {
            //generate random Cards index
            randomCardIndex = random.nextInt((this.cards.size() - 1) + 1); // random numbers from 52 to 1
            //System.out.println(i + " = "+ randomCardIndex);
            tmpDeck.add(this.cards.get(randomCardIndex)); // adding Cards from random index into tmpDeck array
            //System.out.println(" hey : " + this.cards.get(randomCardIndex));
            // remove from original deck
            this.cards.remove(randomCardIndex);// removing Cards at the randomCardIndex from original deck
        }
        this.cards = tmpDeck; // putting the shuffled temp array back into original deck
    }

    @Override
    public String toString() {
        String cardListOutput = " ";
        //int i = 0;
        for(Card aCard : this.cards) { // for all the cards in arrayList
            cardListOutput += "\n" + "-" + aCard.toString(); // each Cards goes into cardListOutput // aCard.toString() = goes into Cards toString
            //i++;
        }
        return cardListOutput; // String that is sent with info into CARD CLASS toString
    }

    public void removeCard(int i) // remove card - in DRAW METHOD
    {
        this.cards.remove(i);
    }

    public Card getCard(int i) // gets a card at a specified index
    {
        return this.cards.get(i);
    }

    public void addCard(Card addCard) // add a card to the specified arrayList (playing, player or dealer) in DRAW METHOD
    {
        this.cards.add(addCard);
    }

    // draws from the deck
    // taking in a Deck (playing, player or dealer) and adding card from top of playingDeck and removing it from playing deck so the same card is
    // nt added again
    public void draw(Deck comingFrom)
    {
        this.cards.add(comingFrom.getCard(0)); // adding card
        comingFrom.removeCard(0); // remove card from the deck it is coming from
    }

    public int deckSize() // gets the number of cards within a Deck (playing, player or dealer)
    {
        return this.cards.size();
    }

    public void moveAllToDeck(Deck moveTo) //
    {
        int thisDeckSize = this.cards.size();

        // put cards into moveTo Deck
        for(int i = 0; i < thisDeckSize; i++) {
            moveTo.addCard(this.getCard(i));
        }

        for(int i = 0; i < thisDeckSize; i++)
        {
            this.removeCard(0);
        }

    }

    // retunrs total value of acrds
    public int cardsValue()
    {
        int totalValue = 0; // tracking value of cards
        int aces = 0; // tracking number of aces

        for(Card acard : this.cards) // loop through Card hand and add up the values
        {
            switch(acard.getValue()) { // getValue is in CARD CLASS = gives access tot he enum called Value
                case TWO: totalValue += 2; break;
                case THREE: totalValue += 3; break;
                case FOUR: totalValue += 4; break;
                case FIVE: totalValue += 5; break;
                case SIX: totalValue += 6; break;
                case SEVEN: totalValue += 7; break;
                case EIGHT: totalValue += 8; break;
                case NINE: totalValue += 9; break;
                case TEN: totalValue += 10; break;
                case JACK: totalValue += 10; break;
                case QUEEN: totalValue += 10; break;
                case KING: totalValue += 10; break;
                case ACE: totalValue += 1; break;
            }
        }
        for(int i = 0; i < aces; i++) // track number of aces and if over or under a certain value manipulate ace value
        {
            if(totalValue > 10) {
                totalValue += 1;
            }
            else {
                totalValue += 11;
            }
        }
        return totalValue;
    }
}
