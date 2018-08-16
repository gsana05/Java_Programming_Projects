package Banking;

import java.io.*;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CustomerInfo
{
    private Scanner scan = new Scanner(System.in);
    private Map<String, String> map;
    BufferedReader reader = new BufferedReader(new FileReader("BankCustomerInfo")); // reads the file
    private String accountBalance;
    //private Transactions obj = new Transactions();

    // constructor - reads file before object is created and put file info into MAP so append anyone else that signs up
    public CustomerInfo() throws IOException
    {
        map = new HashMap<>();
        String line = reader.readLine(); // gets the first line of file

        if(!line.isEmpty()) // if the line has words do this // boolean if false do this if true skip this
        {
            while(line != null)
            {
                //System.out.println(line);
                String id = line.substring(0, 5);
                //System.out.println("id number : " + id);

                String information = line.substring(6);
                //System.out.println("Information : " + information);
                map.put(id, information);

                line = reader.readLine();
            }
        }
        else
        {
            System.out.println("Currently nothing in the file");
        }
    }

    public int signUp()
    {
        SecureRandom random = new SecureRandom();

        System.out.println("Sign up darling");

        System.out.println("Please enter your first name");
        String firstName = scan.nextLine();

        System.out.println("Please enter your last name");
        String lastName = scan.nextLine();

        int randNum = random.nextInt(900) + 100;
        String randomNumber = String.valueOf(randNum);
        System.out.println("Random number : " + randomNumber);

        char firstLetterOfFirstName1  = firstName.charAt(0);
        System.out.println("First letter : " + firstLetterOfFirstName1);
        String firstLetterOfFirstName  = String.valueOf(firstLetterOfFirstName1); // most efficient // convert char to string

        char lastLetterOfLastName1 = lastName.charAt(lastName.length() - 1);
        System.out.println("Last letter : " + lastLetterOfLastName1);
        String lastLetterOfLastName = String.valueOf(lastLetterOfLastName1); // most efficient // convert char to string

        accountBalance = "0"; // whenever you sign up account balance is 0

        String userName = firstName + " " + lastName + " Account Balance : " + accountBalance;

        String ID = firstLetterOfFirstName + lastLetterOfLastName + randomNumber;

        System.out.println("Welcome " + firstName + lastName + ". Your ID : " + ID);

        map.put(ID, userName);

        writeToFile(map);

        return 1;
    }

    public boolean logIn()
    {
        boolean idValidate = true;
        System.out.println("Time to logIn");
        System.out.println("Please enter your ID : or enter L to exit");
        String id = scan.nextLine();

        if(id.equals("L"))
        {
            System.out.println("GOOD BYE");
            System.exit(0);
        }

        System.out.println("id entered : " + id);

        boolean validId = map.containsKey(id); // finds id(key value) in the map array
        System.out.println("map contains id : " + validId);

        if(validId) // if the id is found do this
        {
            for (Map.Entry kv : map.entrySet()) // entry is interface inside map interface
            {
                String key = String.valueOf(kv.getKey()); // converts object into a string which is then compared to string user input

                if(id.equals(key)) // user input matches id from file do this
                {
                    System.out.println();
                    System.out.println("USER DATA : ");
                    System.out.println("The Key : " + kv.getKey());
                    System.out.println("The Value : " + kv.getValue());

                    // FINDING THE ACCOUNT BALANCE IN THE STRING LINE ///////////////
                    String values = String.valueOf(kv.getValue());
                    System.out.println("VALUE : " + values);

                    System.out.println("length of chars : " + values.length());// remember NOT counting key!!

                    // number of words in line
                    String[] v = values.split("\\W+");
                    System.out.println("length words : " + v.length);
                    String CurrentBalancelastWord = v[v.length - 1];
                    System.out.println("last word in line : " + CurrentBalancelastWord);

                    System.out.println("Your current account balance : " + CurrentBalancelastWord);
                    System.out.println();
                    int currentBalance = Integer.parseInt(CurrentBalancelastWord);

                    // USER CHOICE OF TRANSACTION ///////////////////////////
                    boolean value = false;
                    int deposit = 0;

                    while(!value) // loops forever - exits program when user chooses to in transactionChoice method
                    {
                        // USER CHOICE TO DO A TRANSACTION
                        Transactions obj = new Transactions();
                        int choice = obj.transactionChoice();
                        System.out.println("RETURN INPUT : " + choice);

                        // DEPOSIT //////////////////////////////////////
                        if(choice == 1)
                        {
                            System.out.println("DEPOSIT");
                            deposit = obj.deposit();
                            if(deposit != 0.0) // if they cancel deposit at the last minute DO NOT DO THIS
                            {
                                int newBalance = currentBalance + deposit;
                                String upDatedBalance = String.valueOf(newBalance);
                                System.out.println("Your OLD account balance : " + CurrentBalancelastWord);
                                System.out.println("Your deposit was : " + deposit);
                                values = values.replace(CurrentBalancelastWord, upDatedBalance); // replace the last word in string with another
                                System.out.println("VALUE : NEW ACCOUNT BALANCE of " + values);

                                map.put(id, values);
                                writeToFile(map);

                                System.out.println("LOGGING YOU OUT");

                                value = true;
                            }
                            else
                            {
                                System.out.println("No changes needed as no deposit was made");
                            }
                        }
                        // WITHDRAW ////////////////////////////////////////////////////////////////////////////
                        else if(choice == 2)
                        {

                            System.out.println("WITHDRAW");
                            System.out.println("Your OLD account balance : " + CurrentBalancelastWord);

                            Transactions obj1 = new Transactions();
                            int withdraw = obj1.Withdraw(currentBalance);
                            System.out.println("Passes Validation - The amount to withdraw : " + withdraw);

                            // change the last word in map value
                            System.out.println("VALUES WITHDRAW : " + values);
                            int updatedBalanceAfterWithdraw = currentBalance - withdraw;
                            String updateBalance = String.valueOf(updatedBalanceAfterWithdraw);

                            values = values.replace(CurrentBalancelastWord, updateBalance); // replace the last word in string with another

                            map.put(id, values);
                            writeToFile(map);

                            System.out.println("NEW withdraw balance : " + updatedBalanceAfterWithdraw);

                            System.out.println("LOGGING YOU OUT");

                            value = true;


                        }
                        // VIEW BALANCE ///////////////////////////////////////////////////////////////////////
                        else if(choice == 3)
                        {
                            System.out.println("VIEW BALANCE");
                            System.out.println("VALUE : CURRENT BALANCE of " + values); // gets the String value in map

                        }
                    }
                }

                //// END OF DEPOSIT IN THE BANK /////////////////////////////////////////////////////////////
            }
        }

        if(!validId) // if the id is not found so false do this
        {
            System.out.println("Invalid ID");
            idValidate = false;
        }
        return idValidate;
    }

    public void AllBankAccounts()
    {
        String password ="123";
        System.out.println("Please enter the password(123) to access bank database");
        String input = scan.nextLine();

        if(input.equals(password))
        {
            System.out.println("Correct password");
            for (Map.Entry kv : map.entrySet()) // entry is interface inside map interface
            {
                // SHOWS EVERYONE IN THE FILE AND MAP
                System.out.println("The Key : " + kv.getKey());
                System.out.println("The Value : " + kv.getValue());
                System.out.println("Key and value : " + kv);
                System.out.println();
            }
        }
        else
        {
            System.out.println("Sorry incorrect password");
        }
    }

    public void writeToFile(Map<String, String> map)
    {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("BankCustomerInfo"));
            for (Map.Entry kv : map.entrySet()) // entry is interface inside map interface
            {
                // SHOWS EVERYONE IN THE FILE AND MAP
                /*
                System.out.println("The Key : " + kv.getKey());
                System.out.println("The Value : " + kv.getValue());
                System.out.println("Key and value : " + kv);
                System.out.println();
                */

                String key = String.valueOf(kv.getKey());
                String value = String.valueOf(kv.getValue());
                String data = key + " " + value;

                writer.append(data);
                writer.newLine();
            }
            writer.close();
        } catch (IOException E)
        {
            E.getStackTrace();
        }
    }
}
