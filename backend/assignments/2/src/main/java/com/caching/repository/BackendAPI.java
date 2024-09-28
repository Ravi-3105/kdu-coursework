package com.caching.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BackendAPI {

    /**
     * Implements the HTTP connection
     * @param apiUrl position stack api
     * @return response string
     */
    private static final Logger logger = LoggerFactory.getLogger(BackendAPI.class);
    public static String sendHttpRequest(String apiUrl) throws Exception {

        logger.info("Backend API call initiated");

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set request method
        connection.setRequestMethod("GET");

        // Get the response
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        connection.disconnect();

        return response.toString();
    }
}
