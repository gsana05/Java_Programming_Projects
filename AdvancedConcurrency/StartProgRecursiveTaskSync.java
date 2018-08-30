package AdvancedConcurrency;

import java.util.Scanner;
import java.util.concurrent.RecursiveTask;

// Synchronous - blocking - meaning can NOT execute any more code in main until this has returned = limited scalability
class StartProgRecursiveTaskSync extends RecursiveTask<Integer>
{
    Scanner scan = new Scanner(System.in);

    @Override
    protected Integer compute() {
        System.out.println("Override compute method");
        int userInput = stageOne();
        return userInput;
    }

    public int stageOne()
    {
        boolean value = false;
        int convertInput = 0;

        while(!value)
        {
            System.out.println("Press 1 for program rules || Press 2 to Start Program || Press 3 to exit program ");
            if(scan.hasNextLine())
            {
                System.out.println("String entered");
                String input = scan.nextLine();

                if (input.equals("1"))
                {
                    programRules();
                }
                else if (input.equals("2"))
                {
                    System.out.println("Let's start program - returning value from compute via RecursiveTask");
                    convertInput = Integer.parseInt(input);
                    value = true;
                }
                else if (input.equals("3"))
                {
                    System.out.println("Thank you for coming. Good bye.");
                    convertInput = Integer.parseInt(input);
                    value = true;
                }
                else
                {
                    System.out.println("Invalid input - please try again");
                }
            }
        }
        return convertInput;
    }

    public void programRules()
    {
        System.out.println("1. Always try your best");
        System.out.println("2. Find the positive in everything");
    }
}


