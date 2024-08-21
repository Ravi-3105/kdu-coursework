package org.example;

import java.util.ArrayList;

public class Detail {
    ArrayList<Coins> listCoins = new ArrayList<>();
    ArrayList<Traders> listTraders = new ArrayList<>();

    public void addCoins(Coins coins) {
        listCoins.add(coins);
    }

    public void addTraders(Traders traders) {
        listTraders.add(traders);
    }

    public ArrayList<Coins> getListCoins() {
        return listCoins;
    }

    public ArrayList<Traders> getListTraders() {
        return listTraders;
    }

    synchronized public void setSupply(String symbol, long supply) {
        listCoins.stream()
                .filter(obj -> obj.getCoinSymbol().equals(symbol))
                .findFirst()
                .ifPresent(o -> o.setCirculatingSupply(supply));
    }

    synchronized public void setBoughtValue(String wallet, String coin, Long quantity) {
        listTraders.stream()
                .filter(obj -> obj.getWalletAddress().equals(wallet))
                .findFirst()
                .ifPresent(o -> o.setBought(coin, quantity));
    }

    synchronized public void setSoldValue(String wallet, String coin, Long quantity) {
        listTraders.stream()
                .filter(obj -> obj.getWalletAddress().equals(wallet))
                .findFirst()
                .ifPresent(o -> o.setSold(coin, quantity));
    }

    synchronized public void setProfitLoss(String wallet, double value) {
        listTraders.stream()
                .filter(obj -> obj.getWalletAddress().equals(wallet))
                .findFirst()
                .ifPresent(o -> o.setProfit(value));
    }

    synchronized public void setCoinPrice(String symbol, double price) {
        listCoins.stream()
                .filter(obj -> obj.getCoinSymbol().equals(symbol))
                .findFirst()
                .ifPresent(o -> o.setPrice(price));
    }
}

