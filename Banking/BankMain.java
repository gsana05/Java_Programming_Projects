package Banking;

import java.io.IOException;

public class BankMain
{
    public static void main (String args[]) throws IOException
    {
        CustomerInfo obj = new CustomerInfo();
        inputValidation obj1 = new inputValidation();
        boolean value = false;

        while(!value) // loops forever until uses chooses to exit in entryValidation method
        {
            int input = obj1.entryValidation();

            System.out.println("input MAIN : " + input);

            if(input == 1)
            {
                System.out.println("LOGIN MAIN");
                boolean login = obj.logIn();

                while(!login) // if false (invalid input id input) asks for logIn id again
                {
                    login = obj.logIn();
                }
            }
            else if(input == 2)
            {
                System.out.println("SIGN-UP MAIN");

                int signup = obj.signUp();

                if(signup == 1) // if user has signed up successfully go straight into login option
                {
                    System.out.println("Signing up was successful - LOGIN");
                    boolean login = obj.logIn();

                    while(!login) // if false (invalid input id input) asks for logIn id again
                    {
                        login = obj.logIn();
                    }
                }
            }
            else if(input == 3)
            {
                System.out.println("BANK LOG-IN MAIN");
                // GET THE MAP TO DISPLAY ALL KEY VALUE PAIRS
                obj.AllBankAccounts();
            }
        }

        System.out.println("End of program from MAIN");
    }
}
