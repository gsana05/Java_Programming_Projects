package GameShutTheBox;

import java.security.SecureRandom;

public class DiceRolls
{
    private SecureRandom rand;

    public DiceRolls()
    {
        rand = new SecureRandom();
    }

    public int roll()
    {
        int rand1 = rand.nextInt(6) + 1;
        int rand2 = rand.nextInt(6) + 1;
        System.out.println("random 1 = " + rand1 + " *** random 2 = " + rand2);

        return rand1 + rand2;
    }
}
