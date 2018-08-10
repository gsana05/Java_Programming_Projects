package BlackJackCardGame;

import java.util.Scanner;

public class PlayerBets
{
    private double currentBalance;
    Scanner scan = new Scanner(System.in);
    boolean value = false;
    private double maximumBet = 500;
    private double minimumBet = 1;
    private double returnBet = 0;
    private double bet = 0;


    // constructor
    public PlayerBets(double playerMoney) {
        this.currentBalance = playerMoney; // getting number value from main - from AccountBalance method
    }

    // method
    public double bets()
    {
        while(!value)
        {
            System.out.println("You have  = £ " + currentBalance + " in your account."); // display money in current account
            System.out.println("How much would you like to bet?"); // asking question
            if(scan.hasNextDouble()) // Validation - checking a double has been entered
            {
                System.out.println("correct input");
                bet = scan.nextDouble(); // put input into variable
                value = true; // exit loop
                if(bet > maximumBet) // Maximum bet limit
                {
                    System.out.println("You can NOT bet £ " + bet + ", as our maximum bet is £ " + maximumBet);
                    value = false; // loop again
                }
                if(bet > currentBalance)  // can not bet more than you have in your account
                {
                    System.out.println("You can NOT bet £ " + bet + ", as you only have £ " + currentBalance);
                    value = false; // loop again
                }
                else if (bet < 1) // minimum limit
                {
                    System.out.println("you must bet a minimum of £ " + minimumBet);
                    value = false; // loop again
                }
                else // successful bet that meets the above requirements
                {
                    System.out.println("Success, your bet has been confirmed");
                    returnBet = bet; // put bet value into returnBet
                }
            }
            else // invalid input
            {
                System.out.println("invalid input - no strings");
                value = false;
                scan.next(); // stops this else looping forever and goes back to the top of loop
                // next() - ends after whitespace and goes to the next word separated by whitespace
            }
        }
        return returnBet; // returns the bet amount
    }
}

