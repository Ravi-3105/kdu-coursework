package org.example.beans;

import org.example.blueprint.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Factory1 implements VehicleService {


    double price;

    TyreService tyreService;

    @Autowired @Qualifier("sony")
    SpeakerService speakerService;

    public SpeakerService getSpeakerService() {
        return speakerService;
    }

    public TyreService getTyreService() {
        return tyreService;
    }


    @Autowired @Qualifier("MRF")
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

}
