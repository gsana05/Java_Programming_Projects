package SynchBlocksWaitNotify;

import java.util.Scanner;

public class TicketHolder
{
    Scanner scan = new Scanner(System.in);
    public boolean love()
    {
        System.out.println("Please enter your name");
        String name = scan.nextLine();

        System.out.println("Your name : " + name);

        return true;
    }
}
