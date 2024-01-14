package org.example;

import java.util.HashMap;

public class Traders {
    String firstName;
    String lastName;
    String phone;
    String walletAddress;
    double profit;
    HashMap<String, Long> purse;

    public Traders(String firstName, String lastName, String phone, String walletAddress, HashMap<String, Long> purse,double profit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.walletAddress = walletAddress;
        this.purse = purse;
        this.profit =profit;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setBought(String coin, Long quantity) {
        purse.put(coin,purse.getOrDefault(coin,0L)+quantity);
    }

    public void setSold(String coin, Long quantity) {
        purse.put(coin,purse.getOrDefault(coin,0L)- quantity);
    }

    public void setProfit(double profit) {
        this.profit += profit;
    }

    public double getProfit() {
        return profit;
    }

    public Long getPurse(String coin) {
        return purse.getOrDefault(coin,0L);
    }

    public HashMap<String, Long> getPurseMap() {
        return purse;
    }

    public String getFirstName() {
        return firstName;
    }
}
