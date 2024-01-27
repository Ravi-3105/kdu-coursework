package com.caching.service;

import com.caching.constant.Fixed;
import com.caching.exception.custom.MyCustomException;
import com.caching.model.Coordinate;
import com.caching.repository.BackendAPI;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import static com.caching.constant.Fixed.API_KEY;
import static com.caching.constant.Fixed.FORWARD;

public class CoordinateResponseService {

    private final String api= API_KEY.getValue();

    /**
     * gives addres form co-ordinates
     * @param address get address from co-ordinates
     * @return co-ordinate object
     */

    private final Logger logger = LoggerFactory.getLogger(CoordinateResponseService.class);
    public Coordinate getCoordinate(String address) {
        String apiUrl = FORWARD.getValue()+api+"&query="+address;
        Coordinate coordinate = null;
        try {
            String response = BackendAPI.sendHttpRequest(apiUrl);

            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(response);

            // Get the "data" array
            JSONArray dataArray= jsonResponse.getJSONArray("data");

            // Get the "results" array
            JSONObject result = dataArray.getJSONObject(0);

            // Assuming there is only one result in the array

            // Get the value of the "region" field
            double latitude = result.getDouble("latitude");
            double longitude = result.getDouble("longitude");

            logger.info("Latitude: ".concat(String.valueOf(latitude)));
            logger.info("Longitude: ".concat(String.valueOf(longitude)));

            coordinate = new Coordinate(latitude , longitude);

        } catch (Exception e) {
            logger.info("Exception invalid address");
            throw new MyCustomException("Invalid Address");
        }

        return coordinate;
    }
}
