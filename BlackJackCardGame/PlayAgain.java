package BlackJackCardGame;

import java.util.Scanner;

public class PlayAgain
{
    private double playMoney;
    Scanner scan = new Scanner(System.in);
    private String play = " ";

    public PlayAgain(double playMoney) {
        this.playMoney = playMoney;
    }

    public String play()
    {
        int k = 0;

        while(k != 1 )
        {
            System.out.println("Do you want to play again - Y or N ");
            String name = scan.nextLine();
            name = name.toUpperCase(); // if user enters lowercase 'n' or 'y' converts to uppercase

            if(name.equals("Y")) // if y play again
            {
                System.out.println("Start game");
                play = "Y";
                k = 1;
            }
            else if (name.equals("N")) // if n exit game
            {
                System.out.println("End game");
                System.out.println("you are leaving with £ " + playMoney);
                play = "N";
                k = 1;
            }
            else
            {
                System.out.println("The name you entered was " + name);
                System.out.println("Incorrect input - please enter Y or N");
            }
        }
        return play; // return y or n
    }
}

