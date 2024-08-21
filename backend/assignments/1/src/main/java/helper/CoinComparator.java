package helper;

import org.example.Coins;

import java.util.Comparator;

public class CoinComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {

        return (int)Math.ceil(((Coins)o2).getPrice() - ((Coins)o1).getPrice());
    }
}
