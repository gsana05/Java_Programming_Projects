package BlackJackCardGame;

import java.util.Scanner;

public class AccountBalance
{
    private double deposit = 0;
    private double returnDeposit = 0;
    Scanner scan = new Scanner(System.in);
    Scanner scan1 = new Scanner(System.in);
    double maximumDeposit = 1000;
    double minimumDeposit = 1;
    boolean value = false;

    // Constructor overloading
    // constructor with no parameters
    public AccountBalance() {
    }

    // constructor with one parameter
    public AccountBalance(double returnDeposit) {
        this.returnDeposit = returnDeposit;
    }

    // method which returns a double value
    public double money()
    {
        while(!value) { // keep looping until this is true
            System.out.println("Current balance is £ " + returnDeposit);
            System.out.println("Would you like to make a deposit? (1)YES or (2)NO");
            if (scan.hasNextInt()) { // check an int has been entered - input Validation
                int input = scan.nextInt(); // put the user input from scan into variable
                if (input == 1) { // compare input to 1
                    System.out.println("How much would you like to deposit?");
                    if (scan1.hasNextDouble()) { // make sure a double has been entered
                        //System.out.println("correct number input");
                        value = true; // exits loop
                        deposit = scan1.nextDouble();
                        if (deposit > 1000) { // limit if enter more than 1000 - loop again
                            System.out.println("Sorry our maximum deposit limit is £ " + maximumDeposit);
                            value = false; // loop again
                            //scan1.next();
                        }
                        if (deposit < 1) { // limit if enter less than 1 - loop again
                            System.out.println("Sorry our minimum deposit limit is £ " + minimumDeposit);
                            value = false; // loop again
                            //scan1.next();
                        }
                        if(deposit > 0 && deposit < 1000) // if more than 0 or less than 100 - END LOOP
                        {
                            returnDeposit = deposit; // put deposit in returnDeposit
                            value = true; // end loop
                        }
                    } else { // no string input - validation
                        System.out.println("incorrect input - no strings try again");
                        scan1.next();
                    }
                } else if (input == 2) { // no deposit
                    System.out.println("ok no deposit");
                    returnDeposit = 0; // stops the pass value being returned and sends 0
                    break; // leave loop
                } else { // invalid input - validation
                    System.out.println("invalid number input - please enter 1 or 2");
                }
            } else { // invalid input - validation
                System.out.println("invalid input - please enter 1 or 2");
                value = false; // loop again
                scan.next(); // stops this else looping forever and goes back to the top of loop
                // next() - ends after whitespace and goes to the next word separated by whitespace
            }
        }
        return returnDeposit; // return value into main
    }
}

