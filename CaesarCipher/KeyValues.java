package CaesarCipher;

import java.util.Scanner;

public class KeyValues
{
    private int encryptKey = 0;

    public int key()
    {
        boolean value1 = false;
        while(!value1)
        {
            Scanner scan2 = new Scanner(System.in); // take user input
            System.out.println("Please enter a key - between 0 to 25"); // output question
            if(scan2.hasNextInt()) // validation a number has been entered
            {
                encryptKey = scan2.nextInt(); // put user input into int variable
                if(encryptKey <= 25 && encryptKey >= 0 ) // validation the number is between 0 to 25
                {
                    System.out.println("Valid input");
                    value1 = true; // exit loop
                }
                else
                {
                    System.out.println("invalid number input - please enter a number between 0 to 25");
                }
            }
            else // loop again - no strings
            {
                System.out.println("No string inputs - please try again");
            }
        }
        return encryptKey;
    }
}
