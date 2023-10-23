package com.pluralsight;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.util.Date;
public class Account {


    private String description;
    private String vendor;
    private double amount;

    //local time / local date format setup
   private LocalDate today = LocalDate.now();
   private LocalTime currentTime = LocalTime.now();
   private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");

    //constructor
    //no need to pass in time or date as localdate.now() is called within constructor to initalize field(s)
    public Account(String description,String vendor,double amount){
        this.today = LocalDate.now();
        this.currentTime = LocalTime.now();
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    //getters
    public LocalDate getDate(){
        return this.today;
    }
    public String getTime(){
        return currentTime.format(fmt);
    }
    public String getDescription(){
        return this.description;
    }
    public String getVendor(){
        return this.vendor;
    }
    public double getAmount(){
        return this.amount;
    }

    //setters

    public void setDate(LocalDate date){
        this.today = date;
    }
    public void setTime(LocalTime time){
        this.currentTime = time;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setVendor(String vendor){
        this.vendor = vendor;
    }
    public void setAmount(double amount){
        this.amount = amount;
    }

    @Override
    public String toString() {
        return this.today + "|" + this.currentTime + "|" + this.description + "|" + this.vendor + "|" + this.amount;
    }
}
