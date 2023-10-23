package com.pluralsight;

public class Account {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;


    //constructor

    public Account(String date,String time,String description,String vendor,double amount){
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    //getters
    public String getDate(){
        return this.date;
    }
    public String getTime(){
        return this.time;
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

    public void setDate(String date){
        this.date = date;
    }
    public void setTime(String time){
        this.time = time;
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

}
