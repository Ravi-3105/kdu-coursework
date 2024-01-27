package com.caching.service;

import com.caching.exception.custom.MyCustomException;
import com.caching.model.Address;
import com.caching.repository.BackendAPI;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import static com.caching.constant.Fixed.API_KEY;

public class AddressResponseService {


    private final String api= API_KEY.getValue();

    public Address getAddress(double latitude, double longitude) {
        String apiUrl = "http://api.positionstack.com/v1/reverse?access_key="+api+"&query="+latitude+","+longitude;
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

        } catch (Exception e) {
            throw new MyCustomException("Invalid Coordinates");
        }

        return address;
    }
}
