package BlackJackCardGame;

public class Card {
    private Suit suit; // referring to Suit enum
    private Value value; // referring to Value enum

    // constructor
    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    // OUTPUT
    @Override
    public String toString() {
        return "Card{" +
                "suit=" + this.suit.toString() + "-" +
                ", value=" + this.value.toString() +
                '}';
    }

    // gives access to the enum values(Value) - for cardValues method in DECK
    public Value getValue() {
        return value;
    }


}
