package CollectionsFramework;

import java.util.*;

public class Set_Interface_Practice
{
    private int idNumber;
    private Scanner scan = new Scanner(System.in);

    public Set_Interface_Practice(int idNumber)
    {
        this.idNumber = idNumber;
    }

    public void SetPractice()
    {
        System.out.println("INTERFACE: 'SET' \nCLASSES:- 'HASH SET - fastest' 'LINKED HASH SET 2nd fastest' 'TREE SET 3rd fastest'\n" +
                " NO DUPLICATE VALUES!!!");
        System.out.println("Press any key to learn 'HASHSET' ");
        String next = scan.nextLine();

        HashSetPractice();
    }

    public void HashSetPractice()
    {
        System.out.println("HASH SET *************************************************************************");
        System.out.println("DEFAULT CAPACITY = 16 - LOAD FACTOR = 0.75 (12)-> DOUBLES CAPACITY TO 32");
        System.out.println("HASH SET INTERNALLY USES HASH MAP *** DATA STRUCTURE: HASH TABLE -  " +
                "1*** PASSES THE VALUES THROUGH HASHING ALGORITHM TO DETERMINE WHERE TO STORE/POSITION EACH VALUE \n" +
                "THE SAME VALUE WILL GENERATE THE SAME HASH CODE AND THAT IS HOW IT RECOGNISES DUPLICATE VALUES");

        System.out.println("*********************DOES NOT MAINTAIN INSERTION ORDER**********************");

        System.out.println("HASH SET: 2*** DOES NOT MAINTAIN INSERTION ORDER(NOT INDEX BASED) \n DOES NOT ALLOW DUPLICATE VALUES\n" +
                "ALLOWS ONE NULL VALUE\n HETEROGENEOUS(can take in different types - int, string, boolean ect..) " +
                "\n IMPLEMENTS SERIALIZABLE & CLONEABLE BUT NOT RANDOM ACCESS  ");

        System.out.println("HASH SETl 3*** ADD & CONTAINS & REMOVE = O(1)");
        System.out.println("IDEAL: SEARCH FOR ELEMENTS - O(1)");

        Set<String> set = new HashSet<String>();
        set.add("Gareth");
        set.add("Paul");
        set.add("Ryan");
        set.add("Luke");

        // Iterating over hash set items
        //System.out.println(set);
        // Iterating over hash set items
        System.out.println("Iterating over list: NOT MAINTAINING INSERTION ORDER");
        Iterator i = set.iterator();
        while (i.hasNext())
            System.out.println(i.next());

        set.add("Ronaldo");
        set.remove("Ryan");
        System.out.println("Iterating over list: ADDING NEW ELEMENT 'RONALDO' AND REMOVING 'RYAN' IN O(1)");
        Iterator k = set.iterator();
        while (k.hasNext())
            System.out.println(k.next());

        System.out.println("Searching for element 'Paul' in O(1) ");
        boolean value = set.contains("Paul");
        System.out.println("Found 'Paul' ? " + value);

        System.out.println("Press any key to learn about LINK HASH SET");
        String in = scan.nextLine();

        LinkedHashSetPractice();
    }


    public void LinkedHashSetPractice()
    {
        System.out.println("LINKED HASH SET: DATA STRUCTURE - HASH TABLE & DOUBLY LINKED LIST*********************************");
        System.out.println("DEFAULT CAPACITY = 11 - LOAD FACTOR = 0.75 - NOT IMPORTANT AS IT RESIZE ACCORDINGLY TO ADDS AND REMOVES AUTO");
        System.out.println("****************MAINTAINS INSERTION ORDER******************");
        System.out.println("LINK LIST ADDS NEW NODE TO THE HEAD OF LIST WHERE PREVIOUS NODE IS NOT REFERENCED TO ANY NODE YET" +
                " - Which keeps insertion order");
        System.out.println("LINKED HASH SET INTERNALLY USES LINKED HASH MAP ");
        System.out.println(" ADD & CONTAINS & REMOVE = O(1)");
        System.out.println("***MAINTAINS INSERTION ORDER***" +
                " BECAUSE IT USES DOUBLY LINKED LIST " +
                " WHICH TAKES UP MORE MEMORY, " +
                "WHICH MAKES PERFORMANCE SLOWER THAN HASH SET");
        LinkedHashSet<LinkHashSetPartner> set = new LinkedHashSet<>();
        set.add(new LinkHashSetPartner("Gareth", 34));
        set.add(new LinkHashSetPartner("John", 99));
        set.add(new LinkHashSetPartner("Drake", 88));


        Iterator i = set.iterator();
        while(i.hasNext())
            System.out.println(i.next());

        System.out.println("adding 'MESSI' to the end of LinkHashSet");
        set.add(new LinkHashSetPartner("Messi", 55));
        System.out.println();

        Iterator k = set.iterator();
        while(k.hasNext())
            System.out.println(k.next());

        System.out.println("Press any key to learn about TREE SET");
        scan.nextLine();

        TreeSetPractice();
    }

    public void TreeSetPractice()
    {
        System.out.println("TREE SET: DATA STRUCTURE:- RED-BLACK TREE***************************************************");
        System.out.println("DEFAULT CAPACITY: NONE - GROWS AUTOMATICALLY ACCORDINGLY TO ADDS AND REMOVES");
        System.out.println("GUARANTEED TO PERFORM ALL METHODS IN O(LOG N)");
        System.out.println("SORT ELEMENTS IN 'NATURAL ORDERING'(ASCENDING ORDER) - INTERFACE: COMPARABLE- CLASS: COMPARETO()");
        System.out.println("RED-BLACK TREE: Self balancing binary tree to store data - lower values to the left and higher to the right\n" +
                " Applies 'ROTATIONS' FOR INSERTION AND DELETION");

        Set<Integer> set = new TreeSet<>();
        set.add(idNumber);
        set.add(12);
        set.add(23);
        set.add(3);

        System.out.println(set);
    }
}

class LinkHashSetPartner
{
    private String name;
    private int age;

    public LinkHashSetPartner(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "LinkHashSetPartner{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

