package com.pluralsight;

import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.*;

public class AccountingLedger {
    //create some variables to be used globally
    public static Scanner keyboard = new Scanner(System.in);//global scanner for use across methods
    //initialize account hashMap globally for easy method access
    public static HashMap<String, Account> transaction = new HashMap<String, Account>();
    //initialize arayList of accounts to use in sorting across methods
    public static ArrayList<Account> accountArrayList = new ArrayList<>(transaction.values());
    public static void main(String[] args) throws IOException {
        displayMenu();//entry point for method chain

    }//end main

    public static void displayMenu() throws IOException { //add deposit,make a payment, display the ledger,exit
        //declare local variables
        //create arrayList to use in sorting
        String choice;
        String description;
        String vendor;
        double amount = 0.0;
        //make file reader and writers
        FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv", true);
        FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        //write first line(header) to file
        String input;
        while ((input = bufferedReader.readLine()) == null) {//if reader reads empty, write that first line
            bufferedWriter.write("date|time|description|vendor|amount\n");

        }

        //display output ask for user input
        System.out.println();
        System.out.println("D) Add Deposit ");
        System.out.println("P) Make Payment (Debit) ");
        System.out.println("L) Ledger ");//call displayLedger() if selected
        System.out.println("X) Exit ");
        System.out.println();
        choice = keyboard.nextLine().trim();
        //menu decision structure
        if (choice.equalsIgnoreCase("D")) {//if user chooses deposit
            System.out.print("Please enter Invoice Name/Description:  ");
            description = keyboard.nextLine();
            System.out.print("Please enter the vendor: ");
            vendor = keyboard.nextLine();
            System.out.print("Please enter the amount: ");
            amount = keyboard.nextDouble();
            //add info to Account Object
            Account acc = new Account(description, vendor, amount);
            //add info to HashMap
            transaction.put(description, new Account(description, vendor, amount));
            //write to file from object
            bufferedWriter.write(acc.getDate() + "|" + acc.getTime() + "|" + acc.getDescription() + "|" + acc.getVendor() + "|" + acc.getAmount() + "\n");
            bufferedWriter.close();
            keyboard.nextLine();
            System.out.println("Deposit added to csv file!");
            System.out.println();
            //recursion back to start
            displayMenu();

        } else if (choice.equalsIgnoreCase("P")) {//if user chooses Make a payment
            System.out.print("Please enter Product Name/Description:  ");
            description = keyboard.nextLine();
            System.out.print("Please enter the vendor: ");
            vendor = keyboard.nextLine();
            System.out.print("Please enter the amount: ");
            amount = keyboard.nextDouble();
            amount *= -1;//make amount negative
            keyboard.nextLine();
            //add info to Account Object
            Account acc = new Account(description, vendor, amount);
            //add info to HashMap
            transaction.put(description, acc);
            //write to file from object
            bufferedWriter.write(acc.getDate() + "|" + acc.getTime() + "|" + acc.getDescription() + "|" + acc.getVendor() + "|" + acc.getAmount() + "\n");
            bufferedWriter.close();
            System.out.println("Payment added to csv file!");
            System.out.println();
            //recursion back to start
            displayMenu();
        } else if (choice.equalsIgnoreCase("L")) {// if user selects Ledger
            displayLedger();

        } else if (choice.equalsIgnoreCase("x")) {
            System.exit(0);
        }
    }//end displayMenu()

    public static void displayLedger() throws IOException {
        //declare local vars //compare
        //sort list in descending order based of the date


        String choice = "";
        String description;
        String vendor;
        double amount = 0.0;
        //make file reader and writers
        FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String input;
        while ((input = bufferedReader.readLine()) != null) {//while reader has next line, split to array of strings at "|"
            String[] line = input.split("\\|");
            //ignore first line
            if (!line[0].contains("date")) {//assign each thing in "|" to array
                String date = line[0];
                String time = line[1];
                String descriptionS = line[2];
                String vendorS = line[3];
                String amountS = line[4];
                double amountString = Double.parseDouble(amountS);
                //add each thing into hashmap of statements using text file dates/times
                transaction.put(descriptionS, new Account(LocalDate.parse(date), LocalTime.parse(time), descriptionS, vendorS, amountString));//using second constructor here
                accountArrayList = new ArrayList<>(transaction.values()); // add values from hash into arraylist
                Collections.sort(accountArrayList,(v1,v2)->v2.getDate().compareTo(v1.getDate()));//sort ArrayList in descending order by comparing the dates of each transaction
            }
        }
        //display output ask for user input
        System.out.println("A) All ");
        System.out.println("D) Deposits ");
        System.out.println("P) Payments ");
        System.out.println("R) Reports ");//call displayReports() if selected
        System.out.println();
        choice = keyboard.nextLine().trim();
        //method decision structure
        if (choice.equalsIgnoreCase("A")) {// if user selects All display all entries newest to oldest.
            //display all statements
            // Display all ledger entries here
            for (Account transaction : accountArrayList) {//loop through each transaction in arrayList. print all
                System.out.println(transaction.toString());
            }
            displayMenu();//display menu again
        } else if (choice.equalsIgnoreCase("D")) {//if user selects deposits display deposits
            //for each loop that checks if price > 0 then print those.
            for (Account transaction : accountArrayList) {
                if (transaction.getAmount() > 0) {
                    System.out.println(transaction.toString());//print all positive transactions
                }
            }
            displayMenu();//display menu again
        } else if (choice.equalsIgnoreCase("P")) {
            //for each loop that checks if price < 0 then print those
            for (Account transaction : accountArrayList) {
                if (transaction.getAmount() < 0) {
                    System.out.println(transaction.toString());
                }
            }
            displayMenu();//display menu again
        } else if (choice.equalsIgnoreCase("R")) {
            displayReports(); //calls reports method
        }

    }//end displayLedger()

    public static void displayReports() throws IOException { // method displays report menu and runs all functions depending on user choice
        //read file again
        FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String input;
        while ((input = bufferedReader.readLine()) != null) {//while reader has next line, split to array of strings at "|"
            String[] line = input.split("\\|");

        }//end while
        //declare local vars
        int back;
        int choice;
        String goBack;
        LocalDate date = LocalDate.now();
        Month currentMonth = date.getMonth();//gets current month
        Month lastMonth = currentMonth.minus(1);
        Year currentYear = Year.from(date);//gets current year
        Year previousYear = currentYear.minus(Period.ofYears(1));
        String userVendorSearch;
        boolean tOrF = false;

        //display output ask for user input
        System.out.println("1) Month To Date ");
        System.out.println("2) Previous Month ");
        System.out.println("3) Year To Date ");
        System.out.println("4) Previous Year ");
        System.out.println("5) Search by Vendor ");
        System.out.println();
        choice = keyboard.nextInt();
        keyboard.nextLine();
        accountArrayList = new ArrayList<>(transaction.values()); // add values from hash into arraylist for sorting output
        Collections.sort(accountArrayList,(v1,v2)->v2.getDate().compareTo(v1.getDate()));//sort ArrayList in descending order by comparing the dates of each transaction
        //method decision structure
        if (choice == 1) {// user selects month to date display transactions in current month
            for (Account transaction : accountArrayList) {
                if (transaction.getDate().getMonth() == currentMonth) {//prints statements that match the current month
                    System.out.println(transaction.toString());

                }
            }
            //direct user back to reports or back home
            System.out.println("6) Back to Report page ");
            System.out.println("0) Back to Home page ");
            back = keyboard.nextInt();
            if (back == 6) {
                displayReports();
            } else if (back == 0) {
                keyboard.nextLine();
                displayMenu();
            }

        } else if (choice == 2) {//user selects previous month, display transactions in previous month
            for (Account transaction : accountArrayList) {
                if (transaction.getDate().getMonth() == lastMonth) {
                    System.out.println(transaction.toString());
                }
            }
            //direct user back to reports or back home
            System.out.println("6) Back to Report page ");
            System.out.println("0) Back to Home page ");
            System.out.println();
            back = keyboard.nextInt();
            if (back == 6) {
                displayReports();
            } else if (back == 0) {
                keyboard.nextLine();
                displayMenu();
            }
        }//end else if
        else if (choice == 3) {//user selects year to date display all transactions in current year.
            for (Account transaction : accountArrayList) {
                LocalDate transactionDate = transaction.getDate();
                Year transactionYear = Year.from(transactionDate);
                if (transactionYear.equals(currentYear)) {
                    System.out.println(transaction.toString());
                }//end inner if
            }//end for
            //direct user back to reports or back home
            System.out.println("6) Back to Report page ");
            System.out.println("0) Back to Home page ");
            System.out.println();
            back = keyboard.nextInt();
            if (back == 6) {
                displayReports();
            } else if (back == 0) {
                keyboard.nextLine();
                displayMenu();
            }
        } else if (choice == 4) {//user selects previous year display all transactions in previous year.
            for (Account transaction : accountArrayList) {
                LocalDate transactionDate = transaction.getDate();
                Year transactionYear = Year.from(transactionDate);
                if (transactionYear.equals(previousYear)) {
                    System.out.println(transaction.toString());
                }//end inner if
            }//end for
            //direct user back to reports or back home
            System.out.println("6) Back to Report page ");
            System.out.println("0) Back to Home page ");
            System.out.println();
            back = keyboard.nextInt();
            if (back == 6) {
                displayReports();
            } else if (back == 0) {
                keyboard.nextLine();
                displayMenu();
            }
        } else if (choice == 5) {// searches csv by "vendor" and displays transactions including that vendor.
            System.out.print("Enter the vendor you wish to search by: ");
            userVendorSearch = keyboard.nextLine().trim();
            System.out.println();
            //iterate through transaction list
            for (Account transaction : accountArrayList) {
                String transactionVendor = transaction.getVendor();//save vendor in hashmap as string
                if (transactionVendor.equalsIgnoreCase(userVendorSearch)) {//compare vendor to user typed vendor.
                    System.out.println(transaction.toString());//print matching
                    tOrF = true;//set bool as true if match found
                }
            }//end for
            if (!tOrF) {// if bool is false that means there was no matching vendor
                System.out.println("Vendor not found ");
                System.out.println();
                displayLedger();//default to ledger screen
            }

            //direct user back to reports or back home
            System.out.println("6) Back to Report page ");
            System.out.println("0) Back to Home page ");
            System.out.println();
            back = keyboard.nextInt();
            if (back == 6) {
                displayReports();
            } else if (back == 0) {
                keyboard.nextLine();
                displayMenu();
            }
        }
    }//end displayReports()
}//end class



