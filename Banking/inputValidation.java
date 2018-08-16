package Banking;

import java.io.IOException;
import java.util.Scanner;

public class inputValidation
{

    public inputValidation()
    {

    }

    public int entryValidation() throws IOException
    {
        boolean numValue = false;
        int input = 0;

        while(!numValue)
        {
            Scanner scan = new Scanner(System.in);
            System.out.println("Press 1 to login || Press 2 to sign up || press 3 to view bank members || press 4 to exit program");
            if(scan.hasNextInt())
            {
                System.out.println("number has been entered");
                input = scan.nextInt();
                if(input == 1)
                {
                    System.out.println("You entered : " + input);
                    System.out.println("LOGIN - ENTER VALIDATION");
                    numValue = true;
                }
                else if(input == 2)
                {
                    System.out.println("You entered : " + input);
                    System.out.println("SIGN UP - ENTER VALIDATION");
                    numValue = true;
                }
                else if (input == 3)
                {
                    System.out.println("You entered : " + input);
                    System.out.println("ALL BANK ACCOUNTS");
                    numValue = true;
                }
                else if(input == 4)
                {
                    System.out.println("Leaving Bank. Thank You and Good Bye");
                    System.exit(0);
                }
                else
                {
                    System.out.println("Invalid number input");
                }
            }
            else
            {
                System.out.println("Invalid input - no strings");
            }
        }

        return input;
    }
}
