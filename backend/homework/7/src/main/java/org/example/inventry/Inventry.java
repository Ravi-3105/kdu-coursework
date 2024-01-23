package org.example.inventry;

import org.example.beans.Factory1;
import org.example.beans.Factory2;
import org.example.blueprint.VehicleService;

import java.util.ArrayList;
import java.util.List;

public class Inventry {
    static List<Factory1> vehicleFactory1 = new ArrayList<>();
    static List<Factory2> vehicleFactory2 = new ArrayList<>();

    public static List<Factory2> getVehicleSFactory2() {
        return vehicleFactory2;
    }

    public static void setVehicleSFactory2(Factory2 vehicle) {
        vehicleFactory2.add(vehicle);
    }

    public static List<Factory1> getVehicleSFactory1() {
        return vehicleFactory1;
    }

    public static void setVehicleSFactory1(Factory1 vehicle) {
        vehicleFactory1.add(vehicle);
    }
}
