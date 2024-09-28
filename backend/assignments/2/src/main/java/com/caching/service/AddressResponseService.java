package com.caching.service;

import com.caching.exception.custom.MyCustomException;
import com.caching.model.Address;
import com.caching.repository.BackendAPI;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.caching.constant.Fixed.API_KEY;
import static com.caching.constant.Fixed.REVERSE;

public class AddressResponseService {


    private final String api= API_KEY.getValue();

    /**
     * get co-ordinates from address
     * @param latitude geocoding latitude
     * @param longitude geocoding longitude
     * @return  address object
     */

    private final Logger logger = LoggerFactory.getLogger(AddressResponseService.class);
    public Address getAddress(double latitude, double longitude) {
        String apiUrl = REVERSE.getValue()+api+"&query="+latitude+","+longitude;
        Address address = null;
        try {
            String response = BackendAPI.sendHttpRequest(apiUrl);

            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(response);

            // Get the "data" array
            JSONArray dataObject= jsonResponse.getJSONArray("data");

            // Get the "results" array
            JSONObject result = dataObject.getJSONObject(0);
            address = new Address(result.getString("label"));

            logger.info("Address: ".concat(address.getAddress()));

        } catch (Exception e) {
            logger.info("Invalid Co-ordinates");
            throw new MyCustomException("Invalid Coordinates");
        }

        return address;
    }
}
