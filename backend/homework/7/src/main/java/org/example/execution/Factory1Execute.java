package org.example.execution;

import org.example.beans.Factory1;
import org.example.config.Factory1Config;
import org.example.inventry.Inventry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Factory1Execute {

    private static final double BASE = 2000000.0;

    private static final Logger logger = LoggerFactory.getLogger(Factory1.class);

    public static void execution() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Factory1Config.class);


        Factory1 vehicle;

        for(int index =0; index < 3;index++) {

            vehicle = context.getBean(Factory1.class);
            vehicle.setPrice(BASE*1.1);
            Inventry.setVehicleSFactory1(vehicle);
        }

        List<Factory1> vehicles= Inventry.getVehicleSFactory1();
        Factory1 mostExpensive = vehicles.get(0);
        double expensive = 0.0 ;

        Factory1 leastExpensive = vehicles.get(0);
        double least = Double.MAX_VALUE ;

        for(Factory1 listVehicles : vehicles){
            double value = listVehicles.getPrice();
            if(expensive < value){
                expensive = value;
                mostExpensive = listVehicles;
            }
            if(least > value){
                least = value;
                leastExpensive = listVehicles;
            }
        }
        if(logger.isInfoEnabled()) {
            logger.info("Factory1:::");
            logger.info("Most Expensive vehicle details");
            logger.info("Vehicle Price: ".concat(String.valueOf(mostExpensive.getPrice())));
            logger.info("Vehicle Tyre: ".concat(String.valueOf(mostExpensive.getTyreService().getName())));
            logger.info("Vehicle Speaker: ".concat(String.valueOf(mostExpensive.getSpeakerService().getName())));
            logger.info("-------------------------------------------------------------------------------------");
            logger.info("Least Expensive vehicle details");
            logger.info("Vehicle Price: ".concat(String.valueOf(leastExpensive.getPrice())));
            logger.info("Vehicle Tyre: ".concat(String.valueOf(leastExpensive.getTyreService().getName())));
            logger.info("Vehicle Speaker: ".concat(String.valueOf(leastExpensive.getSpeakerService().getName())));
            logger.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }

        context.close();
    }
}
