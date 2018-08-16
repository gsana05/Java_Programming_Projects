package Banking;

import java.util.Scanner;

public class Transactions
{
    Scanner scan = new Scanner(System.in);
    public Transactions()
    {

    }

    public int transactionChoice()
    {
        boolean value = false;
        int choice = 0;

        while(!value)
        {
            System.out.println("Press 1 to deposit || Press 2 to withdraw || Press 3 to view balance || press 4 to exit");
            if(scan.hasNextInt())
            {
                System.out.println("Number has been entered");
                choice = scan.nextInt();
                value = true;
                if(choice == 1)
                {
                    System.out.println("DEPOSIT");
                }
                else if(choice == 2)
                {
                    System.out.println("WITHDRAW");
                }
                else if(choice == 3)
                {
                    System.out.println("VIEW BALANCE");
                }
                else if(choice == 4)
                {
                    System.out.println("EXIT PROGRAM");
                    System.exit(0);
                    value = true;
                }
                else
                {
                    System.out.println("Invalid number input");
                    value = false;
                }

            }
            else
            {
                System.out.println("Invalid input - no strings");
                value = false;
                scan.next();
            }
        }

        return choice;
    }

    public int deposit()
    {
        int depo = 0;
        boolean value = false;
        final int MinimumDepo = 1;
        final int MaximumDepo = 10000;


        while(!value)
        {
            depo = 0;

            System.out.println("How much would you like to deposit(Only whole numbers)? if you want to cancel enter any character ");
            if(scan.hasNextInt())
            {
                System.out.println("Number was entered");
                depo = scan.nextInt();

                if(depo < MinimumDepo)
                {
                    System.out.println("Sorry you entered " + depo + " And minimum deposit is : £" + MinimumDepo);
                }
                if(depo > MaximumDepo)
                {
                    System.out.println("Sorry you entered " + depo + " And maximum deposit is : £" + MaximumDepo);
                }
                if(depo > MinimumDepo && depo < MaximumDepo)
                {
                    System.out.println("Excellent your deposit of £" + depo + " is being processed");
                    value = true;
                }
            }
            else
            {
                System.out.println("Cancel deposit");
                System.out.println("Invalid input - no strings");
                value = true;
            }
        }

        return depo;
    }

    public int Withdraw(int currentBalance)
    {
        boolean value = false;
        int withdrawal = 0;
        final int MaxWithdrawal = 5000;
        final int MinWithdrawal = 5;

        while(!value)
        {
            withdrawal = 0;
            //System.out.println("Your current balance is : " + currentBalance);
            System.out.println("Please enter the amount you would like to withdraw(whole numbers) || enter any character to cancel withdrawal");
            if(scan.hasNextInt())
            {
                System.out.println("Whole number has been entered");
                withdrawal = scan.nextInt();

                if(withdrawal > currentBalance) // you can not withdraw more than you have
                {
                    System.out.println("Sorry you can NOT withdraw £" + withdrawal + " as you only have £" + currentBalance + " in your account");
                    System.out.println("INFO : Maximum withdrawal is £" + MaxWithdrawal);
                }

                // you have more money in your account than maximum withdrawal but you still can not withdraw more than maximum withdrawal
                else if(currentBalance > MaxWithdrawal && withdrawal > MaxWithdrawal)
                {
                    System.out.println("INFO : Maximum withdrawal is £" + MaxWithdrawal);
                }

                else if(withdrawal < MinWithdrawal) // you must withdraw a minimum of £5
                {
                    System.out.println("Sorry you can NOT withdraw £" + withdrawal + " as the minimum withdrawal is £" + MinWithdrawal);
                }

                else
                {
                    System.out.println("VALID WITHDRAWAL");
                    value = true;
                }
            }
            else
            {
                System.out.println("Invalid input - no strings - try again");
                value = true;
                scan.next(); // stops while loop being infinite when entering a string // stops double call on transactionChoice method
            }
        }

        return withdrawal;
    }
}
