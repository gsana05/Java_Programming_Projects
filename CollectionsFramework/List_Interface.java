package CollectionsFramework;

import java.util.*;

public class List_Interface
{
    private List<Student> list = new ArrayList<>();

    /*
    IDEAL: WHEN SEARCHING BY INDEX
    - default capacity is 10
    - resize array to 1.5 the size of previous array when array gets full known as rehashing
      * rehashing is O(n)
    - O(1) Adding elements at the end within capacity AND index searching
    - O(n) removing elements unless its at the end of array which is O(1)
    - AMORTIZED TIME COMPLEXITY - O(1)
    - Allow duplicate value
    - stable - maintain insertion order
    - NOT synchronised
     */


    public void ArrayListPractice(String name, int age)
    {
        System.out.println("");
        System.out.println("ARRAY LIST***********************************DATA STRUCTURE: ARRAY");
        System.out.println("IDEAL: WHEN SEARCHING BY INDEX AND ADDING AND REMOVING ELEMENTS AT THE END OF ARRAY ONLY");
        System.out.println("NOT IDEAL: WHEN ADDING AND REMOVING ELEMENTS FROM START OR MIDDLE O(N) (REASON: INDEX SHUFFLING)");
        System.out.println("List results - User defined object");
        //list.add(new Student(name, age));

        list.add(new Student(name, age));
        list.add(new Student("Dan", 23));
        list.add(new Student("Aaron", 88));
        list.add(new Student("Ben", 22));

        System.out.println("Unsorted Data");
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(i + " = " + "USER: " + list.get(i));
        }

        Collections.sort(list, new SortByStudentNAME()); // Collections.sort(MERGE SORT/TIM SORT) can sort: LIST - LINK LIST - QUEUE
        System.out.println();
        System.out.println("NAME SORTED DATA - using COMPARATOR");
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(i + " = " + "USER: " + list.get(i));
        }

        Collections.sort(list, new SortByStudentAGE());
        System.out.println();
        System.out.println("AGE SORTED DATA - using COMPARATOR");
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(i + " = " + "USER: " + list.get(i));
        }

        System.out.println("Searching for second element GET METHOD in O(1) FAST: " + list.get(2));
    }
}

class SortByStudentAGE implements Comparator<Student>
{
    @Override
    public int compare(Student a, Student b)
    {
        return a.age - b.age;
    }
}

class SortByStudentNAME implements Comparator<Student>
{
    // Used for sorting in ascending order of
    // roll name
    public int compare(Student a, Student b)
    {
        return a.name.compareTo(b.name);
    }
}

class Student
{
    String name;
    int age;

    // Constructor
    public Student(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    // Used to print student details in main()
    public String toString()
    {
        return this.name + " " + this.age;
    }
}
/// END OF ARRAY LIST //////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////START OF LINKED LIST /////////////////////////////

/*
Double linked list - each node/link/bucket points to the address of each node to the left and right of it
no default capacity just add and removes nodes when required
Higher memory overhead than array;ist because of nodes having to gold addresses to other nodes
 */

class LinkedListPractice
{
    private List<People> list = new LinkedList<>();
    private Scanner scan = new Scanner(System.in);
    private String name;
    private double weight;

    public LinkedListPractice(String name, double weight)
    {
        this.name = name;
        this.weight = weight;
    }

    public void linkedListPrac()
    {
        System.out.println("Are you ready to see user user defined object LinkedList in Action? || Press 'Y'");
        String input = scan.nextLine().toUpperCase();
        if(input.equals("Y"))
        {
            System.out.println("Let's start LinkedList");
        }
        else
        {
            System.out.println("Thank you - Good Bye");
            return; // goes back to main
        }

        System.out.println("");
        System.out.println("LINKED LIST********************************************DATA STRUCTURE: DOUBLY LINKED LIST");
        System.out.println("IDEAL: ADDING AND REMOVING ELEMENTS ANYWHERE IN THE NODES START, MIDDLE, END O(1)");
        System.out.println("NOT IDEAL: SLOW FOR SEARCHING ELEMENTS O(N)");

        list.add(new People(name, weight));
        list.add(new People("Shaw", 82.5));
        list.add(new People("Pogba", 80));
        list.add(new People("Young", 78));

        System.out.println();
        System.out.println("Original list");
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }

        System.out.println();
        list.add(2, new People("Kaka", 40));
        System.out.println("Adding 'KAKA 40' in the middle of nodes in O(1)");
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }

        System.out.println();
        list.remove(3);
        System.out.println("Removing 'POGBA 80.0' from the middle in O(1)");
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }

        System.out.println("Searching for second element GET METHOD in O(n) SLOW: " + list.get(2));
    }
}

class People
{
    private String name;
    private double weight;

    public People(String name, double weight)
    {
        this.name = name;
        this.weight = weight;
    }

    public String toString()
    {
        return this.name + " " + this.weight;
    }
}

