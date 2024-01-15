package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Main {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ExecuteTransaction.class);

    static Detail detail = new Detail();

    public static ArrayList<String[]> parseCSV(Path path) {
        ArrayList<String[]> csvData = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(path.toString()))) {
            reader.readNext();
            List<String[]> records = reader.readAll();
            csvData.addAll(records);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
        return csvData;
    }

    public static JsonNode parseJsonFile(String file) {
        JsonNode jsonNode = null;
        try {

            ObjectMapper mapper = new ObjectMapper();
            File from = new File(file);
            jsonNode = mapper.readTree(from);
        } catch (Exception e) {
            logger.error(String.valueOf(e));
        }
        return jsonNode;
    }


    public static void main(String[] args) throws Exception {
        ArrayList<String[]> coins = parseCSV(Path.of("src/main/resources/coins.csv"));
        for (String[] row : coins) {
            NumberFormat nf = NumberFormat.getInstance();
            double number = nf.parse(row[4]).doubleValue();
            Coins obj = new Coins(Integer.parseInt(row[1]), row[2], row[3], number, Long.parseLong(row[5]));
            detail.addCoins(obj);
        }

        ArrayList<String[]> traders = parseCSV(Path.of("src/main/resources/traders.csv"));
        for (String[] row : traders) {
            Traders obj = new Traders(row[0], row[1], row[2], row[3], new HashMap<>(), 0.0);
            detail.addTraders(obj);
        }
        JsonNode transaction = parseJsonFile("src/main/resources/small_transaction.json");
        executeTransactions(transaction, new CountDownLatch(transaction.size()));

    }

    public static void executeTransactions(JsonNode jsonNode, CountDownLatch latch) {
        for (JsonNode transaction : jsonNode) {
            ExecuteTransaction executeTransaction = new ExecuteTransaction(transaction, latch,detail);
            executeTransaction.run();
            getBlockHash();
        }
    }

    static String getBlockHash() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder transactionHash = new StringBuilder();
        Random rnd = new Random();
        /**
         * Introducing delay mimicking complex
         * calculation being performed.
         */
        for (double i = 0; i < 199999999; i++) {
            i = i;
        }
        while (transactionHash.length() < 128) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            transactionHash.append(SALTCHARS.charAt(index));
        }
        String hashCode = transactionHash.toString();
        return "0x" + hashCode.toLowerCase();
    }
}
