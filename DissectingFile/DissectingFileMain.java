package DissectingFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DissectingFileMain
{
    public static void main(String args[])
    {
        // runs at O(1) // if index bucket collision occur, develops a linkedList(chaining) until 8 occurrences before converting to balance tree
        // When this happens bucket array resize to 64 to reduce the number of collisions
        // rehashing/ resize of map occurs when map is 75% full which then creates a new map double the size of previous map
        // and copies elements to the new map at O(n) time complexity - default capacity is 16 so resize is 12
        Map<String, Integer> map = new HashMap<>();

        System.out.println("Manipulating random text");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("ReportText")); // reads the file
            String line = reader.readLine(); // gets the first line of file
            //System.out.println(line); // displays first line of file

            System.out.println();
            while(line != null) // keeps looping until there are no more numbered lines left in file which is null
            {
                //System.out.println("Line = " + line); // prints out the line

                // make sure no splitting is done on empty lines
                if(!line.trim().equals("")) // trim() = removes all whitespace between words and if there is still whitespace do NOT do this!
                {
                    // there is an array for each line
                    String[] words = line.split(" "); // gets each line and splits them into single word elements and stores in array
                    for(String word : words) // loops through each word in String[] words array
                    {
                        if(word == null || word.trim().equals("")) // goes back to the top of loop if word is empty space
                        {
                            continue; // goes back to top of loop
                        }

                        String liveWord = word.toLowerCase(); // puts the word being used to lowercase in new variable

                        //System.out.println("The word = " + liveWord); // output of individual words

                        if(map.containsKey(liveWord)) // if the key(individual word) is already in the map - increment value by 1
                        {
                            map.put(word, map.get(liveWord) + 1); // increment value by 1
                        }
                        else
                        {
                            map.put(liveWord, 1); // if the key(individual word) is NOT in the map - add to map and set value to 1
                        }
                    }
                }

                line = reader.readLine(); // gets the next line in the file
            }

            //System.out.println(map);
            int mostUsedWord = 0;
            String theWord = null;
            int sumUniqueWords = 0;
            int totalWords = 0;

            for(String word : map.keySet()) // keySet() = gets all the keys in th Map // loops through all keys individually
            {
                sumUniqueWords++; // the map key holds each individual word and the value is the number of times it occurs // so each loop here is a unique word

                totalWords = totalWords + map.get(word); // adds all the values at each key to get total number of words

                //System.out.println( "Word : " + " [ " + word + " ] " + " occurred = " + map.get(word));
                Integer theValue = map.get(word); // puts the individual word being assessed in variable
                if(theValue > mostUsedWord) // if the words value Integer is greater than the number in mostUsedWord then DO THIS else do nothing
                {
                    mostUsedWord = theValue; // put integer value into mostUsedWord variable
                    theWord = word; // put String key into variable
                }
            }

            // display final output
            System.out.println(" [ " + theWord + " ] " + " is the most used word in the text with a total of " + " [ " + mostUsedWord + " ] ");
            System.out.println("Total number of words : " + totalWords);
            System.out.println("The number of unique words : " + sumUniqueWords);

        } catch(IOException e)
        {
            e.printStackTrace();
        }


    }
}
