package CaesarCipher;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Encryption
{
    // private - is encapsulation, which means these fields/variables can only be accessed in this class
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;

    //CONSTRUCTOR OVERLOADING
    // Constructor - which takes a key number. The parameters = can be used anywhere in the class
    // every time instance of this class is called - the constructor is activated and initializes  variables
    public Encryption(int key)
    {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //original alphabet
        shiftedAlphabet1 = alphabet.substring(key) + alphabet.substring(0, key); // shifted alphabet depending on key entry by user
    }

    public Encryption(int key1, int key2)
    {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //original alphabet
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1); // shifted alphabet depending on key entry by user
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2); // shifted alphabet depending on key entry by user
    }

    // method to encrypt with 1 keys
    public String encrypt1Key(String message) // getting String from main // the parameters can only accessed within this method
    {
        System.out.println("Original alphabet in encrypt method : " + alphabet);
        System.out.println("Shiftedd alphabet in encrypt method : " + shiftedAlphabet1);
        System.out.println("The Message : " + message);
        StringBuffer stringBuffer = new StringBuffer(message.toUpperCase()); // StringBuffer - used for memory efficiency and built in methods

        for(int i = 0; i < stringBuffer.length(); i++) // loop round the length of the String message
        {
            char c = stringBuffer.charAt(i); // get character at the i index
            int index = alphabet.indexOf(c); // get the index of c character in the original alphabet

            if(index != -1) // if not -1 means that it is a valid character specified in alphabet variable/field
            {
                char newChar = shiftedAlphabet1.charAt(index); // gets the index of original alphabet and finds that character in shifted alphabet
                stringBuffer.setCharAt(i, newChar); // change the original character from string to new character from shifted alphabet
            }
        }
        //System.out.println("Encrypted output : " + stringBuffer); // display decrypted alphabet
        return stringBuffer.toString();
    }

    // method to encrpt with 2 keys // even = shiftedAlphabet1 *** odd = shiftedAlphabet2
    public String encrypt2Key(String message)
    {
        System.out.println("Original alphabets in encrypt method : " + alphabet);
        System.out.println("Shifted alphabet 1 in encrypt method : " + shiftedAlphabet1);
        System.out.println("Shifted alphabet 2 in encrypt method : " + shiftedAlphabet2);
        System.out.println("The Message : " + message);
        StringBuffer sb = new StringBuffer(message.toUpperCase());

        for(int i = 0; i < sb.length(); i++) // loop through all characters in string
        {
            char c = sb.charAt(i); // get character at i index
            int index = alphabet.indexOf(c); // get index of character "c" in original alphabet

            if(index != -1) // character has been found
            {
                if(i % 2 == 0) // if the index is even number = SHIFTED-ALPHABET-1
                {
                    char newChar = shiftedAlphabet1.charAt(index); // gets the index of original alphabet and finds that character in shifted alphabet
                    sb.setCharAt(i, newChar); // change the original character from string to new character from SHIFTED-ALPHABET-1
                }
                else // if the index is an odd number = SHIFTED-ALPHABET-2
                {
                    char newChar1 = shiftedAlphabet2.charAt(index); // gets the index of original alphabet and finds that character in shifted alphabet
                    sb.setCharAt(i, newChar1); // change the original character from string to new character from SHIFTED-ALPHABET-2
                }
            }
        }
        //System.out.println("TWO KEYS ENCRYPT ---------- " + sb);
        return sb.toString();
    }

    // method to test encryption has worked successfully // IOException
    public void testCaesar(String encrypt, String preEncry) throws IOException
    {
        // attach a file to FileWriter
        FileWriter caesarTextFile =new FileWriter("caesarCipherText");
        BufferedWriter fw = new BufferedWriter(caesarTextFile);

        // read character wise from string and write
        // into FileWriter
        for (int i = 0; i < preEncry.length(); i++) // loop through original input and put each character into the file
        {
            fw.write(preEncry.charAt(i)); // adds to file character by character
        }

        fw.newLine(); // new line

        for (int i = 0; i < encrypt.length(); i++) // loop through encrypted string and put each character into the file
        {
            fw.write(encrypt.charAt(i)); // adds to file character by character
        }

        fw.close(); // close the file
    }
}
