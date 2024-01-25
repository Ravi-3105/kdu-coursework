package com.example.springexercise.respository;

import com.example.springexercise.model.Vehicle;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Inventry {


    public static List<Vehicle> vehicles = new ArrayList<>();

    public static Vehicle mostExpensive(){
        Vehicle temp = vehicles.get(0);
        for(Vehicle vehicle: vehicles){
            if(vehicle.getPrice() > temp.getPrice()){
                temp = vehicle;
            }
        }
        return temp;
    }

    public static Vehicle leastExpensive(){
        Vehicle temp = vehicles.get(0);
        for(Vehicle vehicle: vehicles){
            if(vehicle.getPrice() < temp.getPrice()){
                temp = vehicle;
            }
        }
        return temp;
    }
}
