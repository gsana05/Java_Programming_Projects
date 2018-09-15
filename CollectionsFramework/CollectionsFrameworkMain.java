package CollectionsFramework;

import java.util.Scanner;

public class CollectionsFrameworkMain
{
    public static void main(String args[])
    {
        // WHAT COLLECTION DO YOU WANT TO SEE IN ACTION
        boolean choiceValue = false;
        while(!choiceValue)
        {
            Scanner scanChoice = new Scanner(System.in);
            System.out.println("What collection do you want to activate");
            System.out.println("Press 1 LIST || Press 2 QUEUE/DEQUEUE || Press 3 SET || Press 4 MAP || Press ANY other key to Exit Program");
            String choice = scanChoice.nextLine();

            // LIST INTERFACE: - ARRAY LIST - LINKED LIST
            if(choice.equals("1"))
            {
                System.out.println("LIST INTERFACE");
                List_Interface list_interfaceONE = new List_Interface();
                list_interfaceONE.ArrayListPractice("Santa", 65); // change to name and age for user input

                LinkedListPractice linkedListPractice = new LinkedListPractice("Lee", 67.5);
                linkedListPractice.linkedListPrac();
            }
            // QUEUE INTERFACE: - PRIORITY QUEUE *** DEQUEUE INTERFACE: - ARRAY DEQUEUE
            else if(choice.equals("2"))
            {
                System.out.println("QUEUE INTERFACE AND DEQUEUE INTERFACE");
                Queue_Dequeue_Interface queue_dequeue_interface = new Queue_Dequeue_Interface("David", 'M');
                queue_dequeue_interface.QueuePractice();

            }
            else if(choice.equals("3"))
            {
                System.out.println("SET INTERFACE");
                Set_Interface_Practice set_interface_practice = new Set_Interface_Practice(184);
                set_interface_practice.SetPractice();
            }
            else if(choice.equals("4"))
            {
                System.out.println("MAP INTERFACE");
                Map_Interface_Practice map_interface_practice = new Map_Interface_Practice();
                map_interface_practice.Map_Intro();
            }
            else
            {
                System.out.println("Thank you - Good Bye");
                choiceValue = true;
            }
        }


        System.out.println("End of program");

    }
}
