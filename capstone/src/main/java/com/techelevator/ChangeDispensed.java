package com.techelevator;

public class ChangeDispensed {

    private double currentMoney;
    private int nickel = 5;
    private int dime = 10;
    private int quarter = 25;
    private int nickelQty;
    private int dimeQty;
    private int quarterQty;
    private int numberOfCents;

    public ChangeDispensed() {
        currentMoney = 0.0;
    }
// populates the current money with input from user
    public void feedMoney(double moneyFed) {
        this.currentMoney += moneyFed;
    }
//  subtracts the price from the current money that the user has provided
    public void purchase(double price) {
        this.currentMoney -= price;

    }
//methods that evaluate change amount by quarters, dimes, and nickels
    public int quartersToReturn(int numberOfCents) {
        while (numberOfCents >= quarter) {
            quarterQty += 1;
            numberOfCents -= quarter;
        }
        this.numberOfCents = numberOfCents;
        return quarterQty;
    }

    public int dimesToReturn() {
        while (numberOfCents >= dime) {
            dimeQty += 1;
            numberOfCents -= dime;
        }
        return dimeQty;
    }

    public int nickelsToReturn() {
        while (numberOfCents >= nickel) {
            nickelQty += 1;
            numberOfCents -= nickel;
        }
        return nickelQty;
    }

    public int getNumberOfCents() {
        return numberOfCents;
    }
    public void setNumberOfCents(int cents){
        numberOfCents = cents;
    }


    public double getCurrentMoney() {
        return currentMoney;
    }
    public void setCurrentMoney(double moneys){
        currentMoney = moneys;
    }

}