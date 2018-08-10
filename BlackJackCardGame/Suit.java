package BlackJackCardGame;

/*
ENUMS: In java enums are objects. Are mainly used to hold constants but can hold methods, constructors
in java when an enum is create the compiler creates a class:
every enum class extends Enum which is abstract and has a range of built in methods.
it also implements Comparable and serializable = this means we can store the state of an object of enum

Can NOT extend classes I create but can implement interfaces
Can be create inside of a class

class Suit extends Enum {
Static final Suit CLUB = new Suit();
Static final Suit DIAMOND = new Suit();
Static final Suit SPADE = new Suit();
Static final Suit HEART = new Suit();
}

In C and C++ enum is NOT a class

*/

public enum Suit {
    CLUB, DIAMOND, SPADE, HEART
}
