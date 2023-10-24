package com.pluralsight;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.util.Date;

public class AccountingLedger {
    //create some variables to be used globally


//end static


    public static Scanner keyboard = new Scanner(System.in);//global scanner for use across methods
    //initialize account hashMap globally for easy method access
    public static HashMap<String, Account> transaction = new HashMap<String, Account>();

    public static void main(String[] args) throws IOException {//call methods in main


        displayMenu();

    }//end main

    public static void displayMenu() throws IOException { //add deposit,make a payment, display the ledger,exit
        //declare local variables
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
        while ((input = bufferedReader.readLine()) == null) {
            bufferedWriter.write("date|time|description|vendor|amount\n");

        }


        //display output ask for user input
        System.out.println("D) Add Deposit ");
        System.out.println("P) Make Payment (Debit) ");
        System.out.println("L) Ledger ");//call displayLedger() if selected
        System.out.println("X) Exit ");
        choice = keyboard.nextLine().trim();
        //menu decision structure
        if (choice.equalsIgnoreCase("D")) {//if user chooses deposit
            System.out.println("Please enter Invoice Name/Description:  ");
            description = keyboard.nextLine();
            System.out.println("Please enter the vendor: ");
            vendor = keyboard.nextLine();
            System.out.println("Please enter the amount: ");
            amount = keyboard.nextDouble();
            //add info to Account Object
            Account acc = new Account(description, vendor, amount);
            //add info to HashMap
            transaction.put(description, new Account(description, vendor, amount));
            //write to file from object
            bufferedWriter.write(acc.getDate() + "|" + acc.getTime() + "|" + acc.getDescription() + "|" + acc.getVendor() + "|" + acc.getAmount() + "\n");
            bufferedWriter.close();

        } else if (choice.equalsIgnoreCase("P")) {//if user chooses Make a payment
            System.out.println("Please enter Product Name/Description:  ");
            description = keyboard.nextLine();
            System.out.println("Please enter the vendor: ");
            vendor = keyboard.nextLine();
            System.out.println("Please enter the amount: ");
            amount = keyboard.nextDouble();
            amount *= -1;
            //add info to Account Object
            Account acc = new Account(description, vendor, amount);
            //add info to HashMap
            transaction.put(description,acc);
            //write to file from object
            bufferedWriter.write(acc.getDate() + "|" + acc.getTime() + "|" + acc.getDescription() + "|" + acc.getVendor() + "|" + acc.getAmount() + "\n");
            bufferedWriter.close();
        } else if (choice.equalsIgnoreCase("L")) {// if user selects Ledger
            displayLedger();

        }
//        for (Account  trans : transaction.values()) {
//            System.out.printf(,
//                    trans.getDate(), trans.getTime(), trans.getDescription(), trans.getVendor(), trans.getAmount());
//        }


    }//end displayMenu()

    public static void displayLedger() throws IOException {
        //declare local vars
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
                transaction.put(descriptionS, new Account(LocalDate.parse(date),LocalTime.parse(time),descriptionS, vendorS, amountString));
            }
        }
            //display output ask for user input
            System.out.println("A) All ");
            System.out.println("D) Deposits ");
            System.out.println("P) Payments ");
            System.out.println("R) Reports ");//call displayReports() if selected
            choice = keyboard.nextLine().trim();
            //method decision structure
            if (choice.equalsIgnoreCase("A")) {// if user selects All display all entries newest to oldest.
                //display all statements
                // Display all ledger entries here
                for (Account transaction : transaction.values()) {
                    System.out.println(transaction.toString());
                }
            }
            else if(choice.equalsIgnoreCase("D")){//if user selects deposits display deposits
                //for each loop that checks if price > 0 then print those.
                for(Account transaction: transaction.values()){
                    if (transaction.getAmount() > 0){
                        System.out.println(transaction.toString());
                    }
                }
            }
            else if(choice.equalsIgnoreCase("P")){
                //for each loop that checks if price < 0 then print those
                for(Account transaction: transaction.values()){
                    if (transaction.getAmount() < 0){
                        System.out.println(transaction.toString());
                    }
                }

            }
            else if(choice.equalsIgnoreCase("R")){
                displayReports();
            }

    }//end displayLedger()
        public static void displayReports() throws IOException {
        //read file again
            FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String input;
            while ((input = bufferedReader.readLine()) != null) {//while reader has next line, split to array of strings at "|"
                String[] line = input.split("\\|");
                //split line further into just the date at index[0]
                String[] dates = line[0].split("\\-");


            }//end while
            //declare local vars
            int choice;
            LocalDate date = LocalDate.now();
            Month currentMonth = date.getMonth();
            //display output ask for user input
            System.out.println("1) Month To Date ");
            System.out.println("2) Previous Month ");
            System.out.println("3) Year To Date ");
            System.out.println("4) Previous Year ");
            System.out.println("5) Search by Vendor ");
            choice = keyboard.nextInt();
            //method decision structure
            if(choice==1){// user selects month to date display transactions in current month
                for(Account transaction: transaction.values()){
                    if (transaction.getDate().getMonth() == currentMonth){
                        System.out.println(transaction.toString());
                    }
                }

            }
            System.out.println("H) Go back to home page ");

        }//end displayReports()

    }//end class



