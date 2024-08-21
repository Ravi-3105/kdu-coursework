package helper;

import org.example.Traders;

import java.util.Comparator;

public class TraderAscComparator implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {

        return (int) Math.ceil(((Traders) o1).getProfit() - ((Traders) o2).getProfit());
    }
}
