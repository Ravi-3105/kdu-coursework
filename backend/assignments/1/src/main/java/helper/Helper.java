package helper;

import org.example.Coins;
import org.example.Detail;
import org.example.Traders;

import java.util.HashMap;

public class Helper {
    synchronized public Coins findCoinDeatils(String symbol, Detail detail) {
        Coins found  = new Coins(-1,"","",0.0,0);
        for (Coins coin : detail.getListCoins()) {
            if (coin.getCoinSymbol().equals(symbol)) {
                found = coin;
            }
        }
        return found;
    }

    synchronized public Traders findTraderDetails(String wallet, Detail detail) {
        Traders trade = new Traders("","","","",new HashMap<String,Long>(),0.0);
        for (Traders traders : detail.getListTraders()) {
            if (traders.getWalletAddress().equals(wallet)) {
                trade = traders;
            }
        }
        return trade;
    }

    /*
    Unused Helper funcitons::

    public void topNCoins(Detail detail, int n) {
        Collections.sort(detail.getListCoins(), new CoinComparator());
        for (Coins coin : detail.getListCoins()) {
            if (n == 0) break;
            Logging.print(coin.getCoinSymbol());
            n--;
        }
    }

    public void portfolio(Detail detail, String firstName) {

        for (Traders traders : detail.getListTraders()) {
            if (traders.getFirstName().equals(firstName)) {
                Logging.print("Trader found : ".concat(firstName));
                for (Map.Entry<String, Long> purse : traders.getPurseMap().entrySet()) {
                    Logging.print("Coin : ".concat(purse.getKey()).concat(" Quantity: ".concat(String.valueOf(purse.getValue()))));
                }
                break;
            }
        }
    }

    public void traderProfit(Detail detail, String firstName) {
        for (Traders traders : detail.getListTraders()) {
            if (traders.getFirstName().equals(firstName)) {
                Logging.print("Trader found : ".concat(firstName));
                Logging.print("Profit/Loss : ".concat(String.valueOf(traders.getProfit())));
            }
        }
    }

    public void topBot5Traders(Detail detail) {
        int n = 5;
        Collections.sort(detail.getListTraders(), new TraderComparator());
        for (Coins coin : detail.getListCoins()) {
            if (n == 0) break;
            Logging.print(coin.getCoinSymbol());
            n--;
        }
        Collections.sort(detail.getListTraders(), new TraderAscComparator());
        n = 5;
        for (Coins coin : detail.getListCoins()) {
            if (n == 0) break;
            Logging.print(coin.getCoinSymbol());
            n--;
        }
    }
     */

}

