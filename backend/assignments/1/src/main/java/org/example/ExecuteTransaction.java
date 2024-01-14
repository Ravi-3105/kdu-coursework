package org.example;


import com.fasterxml.jackson.databind.JsonNode;
import helper.Helper;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class ExecuteTransaction implements Runnable {
    JsonNode jsonNode;
    CountDownLatch latch;
    Detail detail;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ExecuteTransaction.class);
    public ExecuteTransaction() {
        logger.error("Testing");
    }

    public ExecuteTransaction(JsonNode jsonNode, CountDownLatch latch, Detail detail) {
        this.jsonNode = jsonNode;
        this.latch = latch;
        this.detail = detail;
    }

    @Override
    public void run() {
        Helper helper = new Helper();
        String type = jsonNode.get("type").asText();
        JsonNode data = jsonNode.get("data");
        String coinSymbol = data.get("coin").asText();
        Coins coins = helper.findCoinDeatils(coinSymbol, detail);
        if (type.equals("BUY")) {
            String wallet = data.get("wallet_address").asText();
            Long quantity = data.get("quantity").asLong();
            while (quantity > coins.getCirculatingSupply()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    logger.error("Interrupted");
                }
            }
            notify();
            logger.error("Coins bought: ".concat(coinSymbol));
            detail.setSupply(coinSymbol, coins.getCirculatingSupply() - quantity);
            detail.setBoughtValue(wallet, coinSymbol, quantity);
            detail.setProfitLoss(wallet, -coins.getPrice() * quantity);

        } else if (type.equals("SELL")) {
            String wallet = data.get("wallet_address").asText();
            Long quantity = data.get("quantity").asLong();
            Traders traders = helper.findTraderDetails(wallet, detail);
            while (quantity > traders.getPurse(coinSymbol)) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    logger.error("Interrupted");
                }
            }
            notify();
            logger.error("Coins Sold: ".concat(coinSymbol));
            detail.setSupply(coinSymbol, coins.getCirculatingSupply() - quantity);
            detail.setSoldValue(wallet, coinSymbol, quantity);
            detail.setProfitLoss(wallet, coins.getPrice() * quantity);

        }
        else if (type.equals("UPDATE_PRICE")) {

            logger.error("Coin Price updated : ".concat(coinSymbol));
            detail.setCoinPrice(coinSymbol, data.get("price").asDouble());

        }
        else if (type.equals("ADD_VOLUME")) {

            logger.error("Coin Volume increased : ".concat(coinSymbol));
            detail.setSupply(coinSymbol, coins.getCirculatingSupply() + data.get("volume").asLong());
        }
        latch.countDown();
    }
}
