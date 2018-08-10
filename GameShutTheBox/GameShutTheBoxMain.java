package GameShutTheBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameShutTheBoxMain
{
    public static void main (String args[]) throws IOException
    {
        ShutTheBoxMethods obj = new ShutTheBoxMethods();
        DiceRolls obj1 = new DiceRolls();
        boolean value = false;

        obj.displayBoxes(); // display the boxes the original boxes // all true (numbers all showing)


        while(!value)
        {
            //BufferedReader - Fast as it only reads sequence of characters unlike scanner which is slower because is parses input data
            // BufferReader is synchronised and scanner is NOT
            // InputStreamReader reads bytes and decodes them into characters
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            boolean value1 = false;

            System.out.println("Start of inner while loop******************");
            while(!value1)
            {
                int totalRandomNumber = obj1.roll(); // get the random numbers

                // check the number they draw can select boxes that haven't been picked otherwise game over!
                boolean movePossible = obj.checkForMove(totalRandomNumber); // check if player can make a move or not with given random number
                System.out.println("move possible returns : " + movePossible);
                if(!movePossible) // if FALSE is returned this means you can not make any moves - GAME OVER
                {
                    System.out.println("Sorry you have no number combinations that = " + " [ " + totalRandomNumber + " ] ");
                    obj.finalScore(); // returns player score - left over boxes added together
                    System.out.println("You are unable to make any moves - GAME OVER");
                    System.exit(0);
                }


                System.out.println("Please enter a number or a series of number that are equal to = " + " [ " + totalRandomNumber + " ] ");
                String input = reader.readLine();
                System.out.println("user input : " + input);


                // if value1 returns TRUE then this while loop is exited // as its invalid user input
                value1 = obj.validation(input, totalRandomNumber); // calling method and passing user input
                System.out.println("value1 : " + value1);

                if(!value1) // if value1 returns FALSE as in meets all input validation requirements
                {
                    System.out.println("Love it - passed input validations");
                    String[] inputArray = input.split("\\W+"); // string input into array

                    boolean checkBox = obj.boxChecking(inputArray); // returns true if number has not been selected before
                    System.out.println("checkBox MAIN : " + checkBox);

                    if(checkBox) // if its true - meaning it has not been pick before
                    {
                        for(int k = 0; k < inputArray.length; k++) // loops through the new string array developed by splitting string
                        {
                            //System.out.println("hellooo my g : " + inputArray[k]);
                            int number = Integer.parseInt(inputArray[k]); // convert elements from string array into Integers
                            boolean boxFlips = obj.flip(number); // calls method and passes Integer one by one
                            System.out.println("flipBox booleans========================= : " + boxFlips);

                            // if false is returned - numbers have already been selected end loop and start again
                        }
                    }
                    else
                    {
                        System.out.println("did not enter checkbox in main");
                    }

                }

                boolean winner = obj.checkForWin(); // checks if all boxes have been set to false if so player complete game
                if(winner)
                {
                    System.out.println("YOU WINNER - YOU SHUT ALL THE BOXES");
                    value1 = true; // exit this inner while loop
                    System.exit(0); // exits program
                    //value = true; // exit the main loop - game is finished you win!
                }
                else // players still has boxes to close
                {
                    // check the number they draw can select boxes that haven't been picked otherwise game over!

                    System.out.println("You still have boxes to shut before you become a winner");
                    obj.displayBoxes();
                }
            } // end of inner while loop
        } // end of main while loop

        System.out.println("End of game");

    }
}
