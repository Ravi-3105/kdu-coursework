package org.example;

public class Coins {
    int rank;
    String name;
    String symbol;
    double price;
    long circulatingSupply;
    public Coins(int rank, String name, String symbol, double price, long circulatingSupply){
        this.rank = rank;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.circulatingSupply =circulatingSupply;
    }

    public void setCirculatingSupply(long circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCoinName() {
        return name;
    }

    public String getCoinSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public long getCirculatingSupply() {
        return circulatingSupply;
    }
}
