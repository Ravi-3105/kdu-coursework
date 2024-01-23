package org.example.execution;


import org.example.beans.Factory1;
import org.example.beans.Factory2;
import org.example.config.Factory2Config;
import org.example.inventry.Inventry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Factory2Execute {


    private static final Logger logger = LoggerFactory.getLogger(Factory1.class);
    private static final double basePrice = 2000000.0;

    public static void execution() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Factory2Config.class);


        Factory2 vehicle;

        for(int index =0; index < 3;index++) {

            vehicle = context.getBean(Factory2.class);
            vehicle.setPrice(basePrice);
            Inventry.setVehicleSFactory2(vehicle);
        }

        List<Factory2> vehicles= Inventry.getVehicleSFactory2();
        Factory2 mostExpensive = vehicles.get(0);
        double expensive = 0.0 ;

        Factory2 leastExpensive = vehicles.get(0);
        double least = Double.MAX_VALUE ;

        for(Factory2 listVehicles : vehicles){
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
            logger.info("Factory2:::");
            logger.info("Most Expensive vehicle details");
            logger.info("Vehicle Price: ".concat(String.valueOf(mostExpensive.getPrice())));
            logger.info("Vehicle Tyre: ".concat(String.valueOf(mostExpensive.getTyreService().getName())));
            logger.info("Vehicle Speaker: ".concat(String.valueOf(mostExpensive.getSpeakerService().getName())));
            logger.info("-------------------------------------------------------------------------------------");
            logger.info("Least Expensive vehicle details");
            logger.info("Vehicle Price: ".concat(String.valueOf(leastExpensive.getPrice())));
            logger.info("Vehicle Tyre: ".concat(String.valueOf(leastExpensive.getTyreService().getName())));
            logger.info("Vehicle Speaker: ".concat(String.valueOf(leastExpensive.getSpeakerService().getName())));
        }

        context.close();
    }
}
