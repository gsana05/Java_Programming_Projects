package CaesarCipher;

public class Decryption
{
    // encapsulation
    private String encryptedString;
    private String alphabet;
    private String shiftedAlphabet;

    // constructor which gets the encrypted message
    public Decryption(String encryptedString) {
        this.encryptedString = encryptedString; // holds encrypted string form main

        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //original alphabet
    }

    // method
    public void decryptionSingleKey()
    {
        System.out.println("decryption method with one key : " + encryptedString);

        int key = 0; // the key which value changes very loop
        String[] bruteForceArray = new String[26]; // 0 to 25

        //saves on memory as it keeps same memory location when changing value and give access to built in methods
        // toUpperCase - transforms all user input to upper case
        StringBuffer decrypt = new StringBuffer(encryptedString.toUpperCase());
        StringBuffer[] namesArray = new StringBuffer[26];


        for(int i = 0; i < bruteForceArray.length; i++) // loops 0 - 25 times = 26 times
        {
            key = i; // key to decrypt the encrypted code
            shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key); // shifted alphabet depending on key entry by user
            bruteForceArray[i] = shiftedAlphabet; // puts each shifted alphabet in the array
            //System.out.println(i + " array : " + bruteForceArray[i]); // display all key alphabets

            namesArray[i] = decrypt; // holds encryptedString - so each time the loops goes round it edits original encrypted string

            for(int j = 0; j < encryptedString.length(); j++) // loops through each char in String individually
            {
                char c = encryptedString.charAt(j); // getting the char at the index of "j" of the encrypted String
                int index = alphabet.indexOf(c); // finds the index of "c" in the original alphabet

                if(index != -1) // if its in the alphabet String - it may execute
                {
                    // bruteForceArray represents shifted alphabet - gets char in shiftedAlphabet using the index from original alphabet
                    char newChar = bruteForceArray[i].charAt(index); // getting char from shiftedArray(bruteForceArray[i]) using index from alphabet
                    namesArray[i].setCharAt(j, newChar); // sets the new char from shiftedArray(bruteForceArray[i]) in position of j
                }
            }

            System.out.println(i + " decryption : " + namesArray[i]); // output of the decrypted results
        }
    }

    // method to decrypt for 2 keys
    public void decryptionTwoKeys()
    {
        System.out.println("decryption method with two keys : " + encryptedString);

        int key = 0; // the key which value changes very loop
        int i = 0;
        String[] bruteForceArray1 = new String[26]; // 0 to 25
        String[] bruteForceArray2 = new String[26]; // 0 to 25

        //saves on memory as it keeps same memory location when changing value and give access to built in methods
        // toUpperCase - transforms all user input to upper case
        StringBuffer decrypt = new StringBuffer(encryptedString.toUpperCase());
        StringBuffer[] namesArray = new StringBuffer[26];


        for(i = 0; i < bruteForceArray1.length; i++) // loops 0 - 25 times = 26 times
        {
            key = i; // key to decrypt the encrypted code
            shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key); // shifted alphabet depending on key entry by user
            bruteForceArray1[i] = shiftedAlphabet; // puts each shifted alphabet in the array
            //System.out.println(i + " array : " + bruteForceArray[i]); // display all key alphabets
            bruteForceArray2[i] = shiftedAlphabet; // puts each shifted alphabet in the array

            namesArray[i] = decrypt; // holds encryptedString - so each time the loops goes round it edits original encrypted string

            for(int j = 0; j < encryptedString.length(); j++) // loops through each char in String individually
            {
                char c = encryptedString.charAt(j); // getting the char at the index of "j" of the encrypted String
                int index = alphabet.indexOf(c); // finds the index of "c" in the original alphabet

                if(index != -1) // if its in the alphabet String - it may execute
                {
                    if(i % 2 == 0) // even index do this
                    {
                        // bruteForceArray represents shifted alphabet - gets char in shiftedAlphabet using the index from original alphabet
                        char newChar = bruteForceArray1[i].charAt(index); // getting char from shiftedArray(bruteForceArray[i]) using index from alphabet
                        namesArray[i].setCharAt(j, newChar); // sets the new char from shiftedArray(bruteForceArray[i]) in position of j
                    }
                    else
                    {
                        // bruteForceArray represents shifted alphabet - gets char in shiftedAlphabet using the index from original alphabet
                        char newChar1 = bruteForceArray2[i].charAt(index); // getting char from shiftedArray(bruteForceArray[i]) using index from alphabet
                        namesArray[i].setCharAt(j, newChar1); // sets the new char from shiftedArray(bruteForceArray[i]) in position of j
                    }
                }
            }
            System.out.println("Only gets the even indexes correct and odd index incorrect at the moment - will solve this soon!");
            System.out.println(i + " decryption : " + namesArray[i]); // output of the decrypted results
        }
    }
}
