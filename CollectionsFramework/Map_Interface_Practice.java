package CollectionsFramework;

import java.util.*;

public class Map_Interface_Practice
{
    private Scanner scan = new Scanner(System.in);

    public void Map_Intro()
    {
        System.out.println("INTERFACE:- MAP - CLASS:- 'HASH MAP' & 'LINKED HASH MAP' & 'TREE HASH MAP '");
        System.out.println("MAP: KEY VALUE PAIRS\n" +
                " 1. DOES NOT ALLOW DUPLICATE KEYS BUT ALLOWS DUPLICATE VALUES\n" +
                " 2. PART OF COLLECTIONS FRAMEWORK BUT DOES NOT EXTENDS COLLECTIONS INTERFACE\n" +
                " 3. NOT SYNCHRONISED");

        System.out.println("Press any key to learn about HASH MAP");
        String in = scan.nextLine();

        HashMapPractice();
    }

    public void HashMapPractice()
    {
        System.out.println("HASH MAP implements map interface: DATA STRUCTURE:- HASH TABLE\n " +
                "  Node/Bucket/LinkedList = 4 pieces of info: 1.Key 2.hashcode 3.value 4.points to next node or null if none\n" +
                "  IMPORTANT: each node/bucket in hashTable creates its own linked list when hashing collision occur\n" +
                "   - after 8 collision known as 'TREEIFY THRESHOLD = 8' hashMap converts Link list into RED-BLACK TREE(Balanced tree)\n" +
                "  - if 'TREEIFY THRESHOLD = 8' is triggered then 'MIN-TREEIFY-CAPACITY = 64' is triggered which means hash table\n" +
                "  - resize the amount of nodes twice e.g 16 -> 32 -> 64\n" +
                "   - RED-BLACK TREE is faster as worse case: O(LOG N) --- LINK LIST worse case: O(N)\n" +
                " THE SAME KEY WILL ALWAYS GENERATE THE SAME HASHCODE AND THAT IS HOW IT GETS() ELEMENT WHEN SEARCHING\n" +
                " HASHCODE RESPONSIBILITY TO GENERATE BALANCED INDEX FOR KEY VALUE PAIRS TO ENSURE 'PUT' & 'GET' TIME COMPLEXITY O(1)");

        System.out.println();

        System.out.println(" 1. DEFAULT CAPACITY = 16 - LOAD FACTOR = 0.75 (12)-> DOUBLES CAPACITY TO 32 *KNOWN AS REHASHING O(N)\n" +
                " 2. DOES NOT MAINTAIN INSERTION ORDER(HASHING/HASHCODE) AND UNORDERED\n " +
                " 3. METHODS = GET() - PUT() - CONTAINS KEY() = O(1)\n" +
                " 4. HASH MAP(NOT SYNCHRONISED) CAN STORE ONE NULL VALUE AS KEY WHICH AUTOMATICALLY GET STORED INDEX 0 OF HASH TABLE\n" +
                " - HASH TABLE CAN NOT STORE ANY NULL VALUES(SYNCHRONISED)");

        System.out.println("Put() elements in - O(1) - NOT MAINTAINING INSERTION ORDER");
        Map<String, Integer> map = new HashMap<>();
        map.put("Gareth", 67);
        map.put("Peter", 45);
        map.put("Luke", 10);
        map.put(null, 78); // stores at 0 index of hashTable

        for(Map.Entry kv : map.entrySet())
        {
            System.out.println("The Key: " + kv.getKey() + " The Value: " + kv.getValue());
        }

        System.out.println("GET() 'PETER' in O(1) " + map.get("Peter"));

        System.out.println();
        System.out.println("Press any key to learn about LINK HASH MAP");
        String input = scan.nextLine();

        LinkedHashMapPractice();

    }

    public void LinkedHashMapPractice()
    {
        System.out.println("LINKED HASH MAP implements map interface: DATA STRUCTURE:- HASH TABLE AND DOUBLY LINKED LIST");
        System.out.println(" 1. MAINTAINS INSERTION ORDER BUT UNSORTED\n " +
                " - maintains insertion order because keys are being stored as doubly linked list" +
                " - causes linked hash map to be little slower than hash map\n" +
                " - linked hash map faster for iteration as all nodes have links to each other(doubly linked list)\n" +
                " 2. METHODS: PUT() - GET() - CONTAINS KEY() - NEXT()ITERATION = O(1)\n" +
                " 3. DEFAULT CAPACITY = 16 - LOAD FACTOR = 0.75 (12)-> DOUBLES CAPACITY TO 32 *KNOWN AS REHASHING O(N");

        System.out.println();

        System.out.println("Maintain insertion order");
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("Gareth", 45);
        map.put("Luke", 99);
        map.put("Drake", 33);

        /*
        for(Map.Entry kv : map.entrySet())
        {
            System.out.println("The Key " + kv.getKey() + " The Value: " + kv.getValue());
        }
        */

        // ITERATION - 'LINK HASH MAP' IS FASTER(doubly linked list - nodes link to each other) THAN ITERATION THROUGH 'HASH MAP'
        Set entrySet = map.entrySet();
        Iterator it = entrySet.iterator();

        System.out.println("LinkedHashMap entries : ");
        while(it.hasNext())
            System.out.println(it.next());


        System.out.println("Press any key to learn about TREE MAP");
        String in = scan.nextLine();

        TreeMapPractice();
        System.out.println();


            // this way of iteration allows object to be passed as parameters and see the output
        /*
        for (Map.Entry<String, Integer> entry : map.entrySet())
        {
            String key = entry.getKey();
            int value = entry.getValue();
            // now work with key and value...
            System.out.println("The Key: " + key + " The Value: " + value);
        }
        */


    }

    public void TreeMapPractice()
    {
        System.out.println("TREE MAP implements map, sortedMap, NavigableMap: DATA STRUCTURE:- RED-BLACK BINARY BALANCED TREE");
        System.out.println(" 1. SORTS DATA IN ASCENDING ORDER VIA NATURAL ORDERING(comparable interface)" +
                " 2. ALL METHODS GUARANTEED TO PERFORM O(LOG N) - slowest of all classes in map interface ");

        System.out.println("Sort in ascending order by keys");
        Map<Integer, String> map = new TreeMap<>();
        map.put(2, "John");
        map.put(3, "Luke");
        map.put(1, "Sam");

        Set entrySet = map.entrySet();
        Iterator it = entrySet.iterator();

        System.out.println("Tree Map entries : ");
        while(it.hasNext())
            System.out.println(it.next());
    }
}
