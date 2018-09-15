package CollectionsFramework;

import java.util.*;

public class Queue_Dequeue_Interface
{
    private String name;
    private char gender;
    private Scanner scan = new Scanner(System.in);

    public Queue_Dequeue_Interface(String name,char gender)
    {
        this.name = name;
        this.gender = gender;
    }

    public void QueuePractice()
    {
        System.out.println("INTERFACE - QUEUE: FIRST IN AND FIRST OUT BASIS");
        System.out.println("QUEUE CLASSES: PRIORITY QUEUE AND LINKED LIST");
        System.out.println("IMPORTANT: INSERTION('ENQUEUE') AT THE TAIL/REAR && DELETION('DEQUEUE') AT THE HEAD/FRONT OF QUEUE = O(1)");
        System.out.println("IDEAL: SHARED RESOURCE (PRINTER IN AN OFFICE) CAN ONLY HANDLE ONE REQUEST AT A TIME" +
                "SO REQUESTS GET QUEUED UP - THE REQUEST THAT COMES FIRST GETS SERVED FIRST");
        System.out.println("Name: " + name + " gender: " + gender);

        System.out.println("Press any key to learn CLASS: PRIORITY QUEUE");
        String input = scan.nextLine().toUpperCase();
        PriorityQueuePractice();
    }

    public void PriorityQueuePractice()
    {
        System.out.println("PRIORITY QUEUE ******************************** DATA STRUCTURE: PRIORITY HEAP(tree based)");
        System.out.println("Before elements go into the queue they are put in 'NATURAL ORDERING' (ascending order) - Comparable");
        System.out.println("PRIORITY QUEUE IS UNBOUNDED - ADD AS MANY ELEMENTS AS YOU LIKE AND IT GROWS " +
                "DEFAULT CAPACITY OF 11 HOWEVER YOU DO NEED TO THINK ABOUT THAT AS IT IS TREE BASED UNBOUNDED");
        System.out.println("NOT THREAD SAFE");
        Queue<String> priorityQueue = new PriorityQueue<>();

        System.out.println("Before queue: ");

        priorityQueue.offer(name);
        System.out.println(name);

        String user1 = "James";
        System.out.println(user1);
        priorityQueue.offer(user1);

        String user2 = "Adam";
        System.out.println(user2);
        priorityQueue.offer(user2);

        System.out.println("First(peek) in queue = " + priorityQueue.peek());

        System.out.println("Stored in queue in 'natural ordering' ascending order and shown by the way the elements are removed");
        while(!priorityQueue.isEmpty())
        {
            System.out.println(priorityQueue.remove());
        }

        System.out.println();
        System.out.println("Press any key to view CLASS: LINKED LIST");
        scan.nextLine();

        LinkedListPractice();
    }

    public void LinkedListPractice()
    {
        System.out.println("QUEUE LINKED LIST ************************************ DATA STRUCTURE: ARRAY");
        System.out.println("IDEAL: MAINTAIN INSERTION ORDER (STABLE)");
        System.out.println(" - NO INDEX(NO GET METHOD) - YOU CAN ONLY ADD ELEMENTS AT THE REAR OF QUEUE NORMAL LINKED LIST CAN ADD AND REMOVE ANYWHERE" +
                " - YOU CAN REMOVE ELEMENTS AT FRONT OF QUEUE - STABLE - CAN ADD UNLIMITED NUMBER OF VALUES ");
        System.out.println("ADD/OFFER - POLL/REMOVE = O(log n) \nPEAK/GET QUEUE FRONT = 0(1) \nCONTAINS = O(n)");

        Queue<Character> queueLinkList = new LinkedList<>();
        System.out.println("BEFORE GOING INTO QUEUE LINKED LIST");
        char num1 = gender;
        System.out.println(num1);
        queueLinkList.add(num1);

        char num2 = 'Z';
        System.out.println(num2);
        queueLinkList.add(num2);

        char num3 = 'C';
        System.out.println(num3);
        queueLinkList.add(num3);


        System.out.println("using poll to remove(dequeue) 'M' from front of queue");
        queueLinkList.poll();

        System.out.println("using add/offer(enqueue) 'K' at the rear of queue");
        queueLinkList.offer('K');

        System.out.println("PEEK = Getting thr front of queue: " + queueLinkList.peek());

        System.out.println("The order the queue deals with requests");
        while(!queueLinkList.isEmpty())
        {
            System.out.println(queueLinkList.remove());
        }

        System.out.println("Press any key to learn about INTERFACE: DEQUEUE");
        String input = scan.nextLine();
        DequeuePractice();
    }

    public void DequeuePractice()
    {
        System.out.println("INTERFACE - DEQUEUE: CLASS: ARRAY DEQUEUE");
        System.out.println("DATA STRUCTURE: DOUBLY LINKED LIST");
        System.out.println("ArrayDequeue do not allow random access and arrayLists do allow random access because of index");
        System.out.println("Memory efficient - arrayDequeue O(1) Faster than linked list O(n) for accessing elements");
        System.out.println("DEQUEUE: DOUBLE ENDED QUEUE WHICH ALLOWS FOR INSERTION ANF DELETION AT BOTH ENDS REAR AND FRONT");
        System.out.println(" - NO CAPACITY RESTRICTIONS - MAINTAINS INSERTION ORDER");
        System.out.println(" * ADD/OFFER * PEEK * POLL = O(1) \nCONTAINS/SEARCHING = O(n)");

        System.out.println("Press any key to see ARRAY DEQUEUE IN ACTION");
        String input = scan.nextLine();

        Deque<String> deq = new ArrayDeque<>();
        deq.offer(name);
        String team1 = "Liverpool";
        deq.offer(team1);

        String team2 = "Chelsea";
        deq.offer(team2);

        String team3 = "Manchester";
        deq.offer(team3);

        System.out.println("ORIGINAL ARRAY DEQUEUE OF REMOVING");
        while(!deq.isEmpty())
        {
            System.out.println(deq.poll());
        }

        System.out.println("ADDING STRINGS BACK TO DEQUEUE");
        deq.offer(name);
        deq.offer(team1);
        deq.offer(team2);
        deq.offer(team3);

        System.out.println("Removing element at front 'DAVID' 'pollFirst' ");
        deq.pollFirst();

        System.out.println("Removing element at rear 'Manchester' 'pollLast' ");
        deq.pollLast();

        System.out.println("Adding/offering at front 'Ronaldo' 'offerFirst' ");
        deq.offerFirst("Ronaldo");

        System.out.println("Adding/offering at front 'Messi' 'offerLast' ");
        deq.offerLast("Messi");


        while(!deq.isEmpty())
        {
            System.out.println(deq.poll());
        }
    }
}
