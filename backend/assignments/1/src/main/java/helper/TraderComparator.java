package helper;

import org.example.Traders;

import java.util.Comparator;

public class TraderComparator implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {

        return (int) Math.ceil(((Traders) o2).getProfit() - ((Traders) o1).getProfit());
    }
}
