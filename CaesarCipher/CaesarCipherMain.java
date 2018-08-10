package CaesarCipher;

import java.io.IOException;
import java.util.Scanner;

public class CaesarCipherMain
{
    public static void main(String args[]) throws IOException
    {
        // GETTING STRING INPUT FROM USER WITH VALIDATION
        boolean value = false;
        String message = " ";

        while(!value)
        {
            Scanner scan1 = new Scanner(System.in);
            System.out.println("Please enter your secret message to be encrypted");
            if(scan1.hasNextLine())
            {
                System.out.println("String has been entered ");
                message = scan1.nextLine().toUpperCase(); // puts user input in String variable
                for(int i = 0; i < message.length(); i++) // loops through each character in string
                {
                    char c = message.charAt(i); // finds character at each index
                    if(Character.isLetter(c) || c == ' ') // has to be a letter input // allow white spaces
                    {
                        value = true; // exit loop
                    }
                    else // if numbers are entered do this and loop again
                    {
                        System.out.println("No number inputs - only characters ");
                        value = false; // loop again
                        break; // exits inner for loop and goes to top of outer while loop
                    }
                }
            }
            else
            {
                System.out.println("no numbers");
            }
        }

        System.out.println("The message : " + message);

        // GETTING ENCRYPTION WITH ONE OR TWO KEY AND VALIDATION
        boolean value2 = false; // while loop
        int key1 = 0;
        int key2 = 0;
        KeyValues obj = new KeyValues(); // creating object
        String encryptedReturn = " ";
        boolean decryptOneorTwoKeys = false; // this is for decryption to know to decrypt a string that used two keys or just one key

        while(!value2)
        {
            System.out.println("Would you like to encrypt with 1 or 2 keys? enter 1 or 2 please");
            Scanner scan3 = new Scanner(System.in);
            if(scan3.hasNextInt()) // validation - checking an integer has been entered
            {
                System.out.println("You entered a number");
                int input = scan3.nextInt();
                if(input == 1) // 1 key encryption
                {
                    System.out.println("Only 1 key encryption");
                    key1 = obj.key(); // calling method and return int value
                    System.out.println(" 1 Key entered : " + key1);
                    Encryption encryption1 = new Encryption(key1); // passing key to constructor
                    encryptedReturn = encryption1.encrypt1Key(message); // calling method and passing in "String" and return value
                    //System.out.println("The return of the encrypted string : " + encryptedReturn);
                    // TESTING CAESAR ENCRYPTION - sending information to text file
                    encryption1.testCaesar(encryptedReturn, message); // calls method from encryption class
                    decryptOneorTwoKeys = false; // this is for decryption to know to decrypt a string that used one keys
                    value2 = true; // exit loop
                }
                else if (input == 2) // 2 key encryption
                {
                    System.out.println("Only 2 key encryption");
                    key1 = obj.key(); // calling method and return int value
                    key2 = obj.key(); // calling method and return int value
                    System.out.println("Two keys entered : key1 = " + key1 + " key2 = " + key2);
                    Encryption encryption2 = new Encryption(key1, key2); // passing keys to constructor
                    encryptedReturn = encryption2.encrypt2Key(message); // calling method and passing in "String" and return value
                    System.out.println("The return of the encrypted string : " + encryptedReturn);
                    // TESTING CAESAR ENCRYPTION - sending information to text file
                    encryption2.testCaesar(encryptedReturn, message); // calls method from encryption class
                    decryptOneorTwoKeys = true; // this is for decryption to know to decrypt a string that used two keys
                    value2 = true; // exit loop
                }
                else
                {
                    System.out.println("invalid number input");
                    value2 = false; // loop again
                }
            }
            else
            {
                System.out.println("invalid input - no strings"); // loop again
            }
        }

        //DECRYPTION **********
        boolean value3 = false; // while loop
        Decryption decryption_Obj = new Decryption(encryptedReturn); // creating object and passing encrypted String as params

        while(!value3)
        {
            Scanner scan4 = new Scanner(System.in); // allows user to enter input
            System.out.println("Would you like to decrypt the String : " + encryptedReturn + " enter Y(yes) or N(no)");
            if(scan4.hasNextLine()) // validation to make sure a string has been input
            {
                System.out.println("String has been entered ");
                String input = scan4.nextLine().toUpperCase();
                if(input.equals("Y"))
                {
                    System.out.println("Lets decrypt the message - using BRUTE FORCE");
                    // IF ONE KEY DO THIS
                    if(!decryptOneorTwoKeys) // this is set when user enters 1 or 2 to decrypt with key1 or both key1 & key2
                    {
                        decryption_Obj.decryptionSingleKey(); // calling method
                    }
                    // IF TWO KEYS DO THIS
                    if(decryptOneorTwoKeys) // this is set when user enters 1 or 2 to decrypt with key1 or both key1 & key2
                    {
                       decryption_Obj.decryptionTwoKeys();
                    }

                    value3 = true; // exits loop
                }
                if(input.equals("N"))
                {
                    System.out.println("Ok lets NOT decrypt message ");
                    value3 = true; // exits loop
                }
            }
            else
            {
                System.out.println("Invalid input - no numbers");
                value3 = true; // exit loop
            }
        }

        System.out.println("you reach the end of the program");
    }
}
