package org.example.beans;

import org.example.blueprint.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Factory2 implements VehicleService {


    double price;


    @Autowired @Qualifier("Bridgestone")
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

    @Autowired @Qualifier("bose")    public void setSpeakerService(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    public void setPrice(double price) {
        this.price = price+ tyreService.getPrice()+ speakerService.getPrice();
    }

    public double getPrice() {
        return price;
    }

}
