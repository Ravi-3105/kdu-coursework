package org.example;


import org.example.beans.SpeakerService;
import org.example.beans.TyreService;
import org.example.beans.VehicleService;
import org.example.config.VehicleConfig;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {


    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        List<VehicleService> vehicles = new ArrayList<>();

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(VehicleConfig.class);

        TyreService[] tyre = new TyreService[2];
        tyre[0] = context.getBean("Bridgestone",TyreService.class);
        tyre[1] = context.getBean("MRF",TyreService.class);

        SpeakerService[] speaker = new SpeakerService[2];
        speaker[0] = context.getBean("Sony",SpeakerService.class);
        speaker[1] = context.getBean("Bose",SpeakerService.class);


        Random rand = new Random();

        VehicleService vehicle;

        for(int index =0; index < 3;index++) {

            vehicle = context.getBean(VehicleService.class);

            vehicle.setSpeakerService(speaker[rand.nextInt(2)]);
            vehicle.setTyreService(tyre[rand.nextInt(2)]);
            vehicle.setPrice(2000000.0);
            vehicles.add(vehicle);

        }


        VehicleService mostExpensive = vehicles.get(0);
        double expensive = 0.0 ;

        for(VehicleService listVehicles : vehicles){

            if(expensive < listVehicles.getPrice()){
                expensive = listVehicles.getPrice();
                mostExpensive = listVehicles;
            }
        }

        logger.info("Vehicle Price: ".concat(String.valueOf(mostExpensive.getPrice())));
        logger.info("Vehicle Tyre: ".concat(String.valueOf(mostExpensive.getTyreService().getName())));
        logger.info("Vehicle Speaker: ".concat(String.valueOf(mostExpensive.getSpeakerService().getName())));
    }
}