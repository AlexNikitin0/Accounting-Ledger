package com.pluralsight;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;

public class AccountingLedger {
    //create some variables to be used globally
    Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {//call methods in main




    }//end main

    public static void displayMenu(){ //add deposit,make a payment, display the ledger,exit
        //display output ask for user input
        System.out.println("D) Add Deposit ");
        System.out.println("P) Make Payment (Debit) ");
        System.out.println("L) Ledger ");//call displayLedger() if selected
        System.out.println("X) Exit ");


    }//end displayMenu()

    public static void displayLedger(){
        //display output ask for user input
        System.out.println("A) All ");
        System.out.println("D) Deposits ");
        System.out.println("P) Payments ");
        System.out.println("R) Reports ");//call displayReports() if selected
    }

    public static void displayReports(){
        //display output ask for user input
        System.out.println("1) Month To Date ");
        System.out.println("2) Previous Month ");
        System.out.println("3) Year To Date ");
        System.out.println("4) Previous Year ");
        System.out.println("5) Search by Vendor ");
        System.out.println("H) Go back to home page ");
    }

}//end class


