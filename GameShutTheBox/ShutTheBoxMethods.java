package GameShutTheBox;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ShutTheBoxMethods
{
    private static final int MAXBOXES = 9;
    private boolean[] boxes;

    // Constructor
    public ShutTheBoxMethods()
    {
        boxes = new boolean[MAXBOXES + 1]; // setting boolean array length
        for(int i = 0; i < boxes.length; i++)
        {
            boxes[i] = true; // setting all boxes to true
        }
    }

    // Method
    public void displayBoxes()
    {
        System.out.println("         SHUT THE BOX        ");
        for(int i = 1; i < boxes.length; i++)
        {
            if(boxes[i]) // if the index is set to true
            {
                System.out.print(" [ " + i + " ] ");
            }
            else // if the index is set to false
            {
                System.out.print(" [ " + " X " + " ] ");
            }
        }
        System.out.println();
        System.out.println("***************************************************************");
    }

    public boolean boxChecking(String[] inputArray)
    {
        boolean value = false;

        for(int k = 0; k < inputArray.length; k++) // loops through the new string array developed by splitting string
        {
            int number = Integer.parseInt(inputArray[k]); // convert elements from string array into Integers
            if(boxes[number] == false) // false means the box has been selected before
            {
                System.out.println("try again - you have already selected box number " + number);
                value = false;
                break;
            }
            else // it has not been selected before
            {
                value = true;
            }
        }
        return value;
    }

    public boolean flip(int input) // change the selected boxes by user to false " X "
    {
        boolean flipBox = true; // if box changes from number to " X "
        boolean value = false;

        while(!value)
        {
            //System.out.println("flip method : " + input);

            if(boxes[input] == true) // true means the box has not been selected before - do this
            {
                if(boxes[input])
                {
                    System.out.println("box number : " + input + " has NOT BEEN PICKED before");
                    boxes[input] = false; // set box to false if it has not been selected before so it gets changed
                    value = true;
                }
            }
        }

        return flipBox;
    }

    public boolean validation(String userInput, int totalRandomNumbers)
    {
        boolean value = true; // true means leave while loop in MAIN
        int sum = 0;
        boolean value1 = false; // enter while loop in THIS METHOD
        ArrayList<Integer> list1 = new ArrayList<>();

        for(int i = 0; i < userInput.length(); i++)
        {
            char c = userInput.charAt(i);
            //System.out.println("char : " + c);

            if(Character.isLetter(c)) // checks if a letter has been entered
            {
                //System.out.println("char : " + c);
                System.out.println("try again - Sorry you entered a character - please enter a number");
                i = userInput.length();
                value = true; // true means leave while loop in MAIN - invalid input
                value1 = true; // does not enter while loop in THIS METHOD
                break;
            }

            if(Character.isDigit(c)) // checks if a number has been entered
            {
                //System.out.println("Integer : " + c);
                //int number = c - '0'; // char using ASCII TABLE to get integer values (48 to 57 in ascii are numbers 0 to 9)
                int charToInt = Character.getNumericValue(c); // convert character to integer

                // checking for duplicate number inputs which is invalid
                list1.add(charToInt);
                Set<Integer> s = new HashSet<>(); // hashSet finds duplicate values
                for(Integer userNumbers : list1)
                {
                    if(!s.add(userNumbers))
                    {
                        System.out.println("Try again - Sorry invalid you entered " + userNumbers + " more than once");
                        value = true; // true means leave while loop in MAIN - invalid input
                        value1 = true; // does not enter while loop in THIS METHOD
                        i = userInput.length();
                    }
                }



                sum = sum + charToInt; // sum of user number input all added up
                //value = false; // False means stay in while loop in main - all numbers have been entered
            }
        }


        while(!value1) // only gets here if user input is all numbers
        {
            System.out.println("sum of user input " + sum);
            if(sum == totalRandomNumbers) // valid input stay in while loop in MAIN
            {
                System.out.println("Excellent! your total input = " + sum + " MATCHES " + totalRandomNumbers + " dices rolls number");
                value = false; // False means stay in while loop in MAIN - all numbers have been entered that equal dice random number
                value1 = true; // exit THIS WHILE loop
            }

            if(sum != totalRandomNumbers)
            {
                System.out.println("Sorry! your total input = " + sum + " DOES NOT MATCH " + totalRandomNumbers + " dices rolls number - TRY AGAIN");
                value1 = true; // exit THIS WHILE loop
            }
        }

        return value;
    }

    public boolean checkForWin()
    {
        boolean finished = true;

        for(int i = 1; i < boxes.length; i++)
        {
            finished = finished && !boxes[i];  // if all boxes are false meaning they are all shut, YOU WIN
        }
        System.out.println("GRADE A*!!!");
        return finished;
    }

    public boolean checkForMove(int totalRandomNumber)
    {
        /*
        Looks for any combination of boxes that are set to true that equal totalRandomNumber
        as soon as it finds one valid combination end return true and continue with program
        otherwise return false meaning there is no possible move you can make
         */

        int origSum = totalRandomNumber ;
        int i;
        int tempValue;
        boolean result;

        if (totalRandomNumber == 0) { // BASE CASE // if box possibilities are free return true
            System.out.println("SUM IS == 0 ");
            return true;
        }

        // prevents out of bounds when dice rolls 10 or more - set totalRandomNumber to 9 as there are only 9 boxes
        if (totalRandomNumber >= MAXBOXES)  {
            totalRandomNumber = MAXBOXES;
        }

        for (i=totalRandomNumber; i>=1; i--)
        {
            System.out.println("for loop count CHECK FOR MOVE : ------------");
            if (boxes[i])
            { // if box is set to true then player can make a move
                tempValue = (origSum - i);
                System.out.println("origSum : " + origSum + " i : " + i);
                if (tempValue >= i)
                {
                    System.out.println("tempValue is greater than or equal to " + i);
                    return false; // you have NO MOVE POSSIBLE with the given randomNumber(sum)
                }

                result = checkForMove(tempValue); // recursion - function calling itself
                if (result)
                {
                    System.out.println("result IF ENTERED : " + result);
                    return true; // you CAN MAKE A MOVE with the given randomNumber(sum)
                }
            }
        }
        System.out.println("leave program the box you choose is gone already");
        return false; // you have NO MOVE POSSIBLE with the given randomNumber(sum)
    }

    public void finalScore()
    {
        int totalScore = 0;

        for(int i = 1; i < boxes.length; i++)
        {
            if(boxes[i]) // if boxes is true - meaning that the box has NOT been shut
            {
                System.out.println("box number " + i + " has a value of : " + i );
                totalScore = totalScore + i;
            }
        }
        System.out.println("YOUR TOTAL SCORE : : " + totalScore);
    }
}
