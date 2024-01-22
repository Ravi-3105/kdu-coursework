package org.example.beans;

import jakarta.annotation.PostConstruct;
import org.example.Main;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class VehicleService {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Main.class);

    double price;

    TyreService tyreService;

    SpeakerService speakerService;

    public SpeakerService getSpeakerService() {
        return speakerService;
    }

    public TyreService getTyreService() {
        return tyreService;
    }

    public void setTyreService(TyreService tyreService) {
        this.tyreService = tyreService;
    }

    public void setSpeakerService(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    public void setPrice(double price) {
        this.price = price+ tyreService.getPrice()+ speakerService.getPrice();
    }

    public double getPrice() {
        return price;
    }

    @PostConstruct
    public void defaultValue(){
        logger.info("Vehicle Service Created");
    }
}
