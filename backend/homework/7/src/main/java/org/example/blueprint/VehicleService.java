package org.example.blueprint;

import jakarta.annotation.PostConstruct;
import org.example.Main;
import org.example.beans.SpeakerService;
import org.example.beans.TyreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public interface VehicleService {

    double price = 0;

    TyreService tyreService = null;

    SpeakerService speakerService = null;

    public SpeakerService getSpeakerService() ;

    public TyreService getTyreService() ;


    public void setTyreService(TyreService tyreService) ;

    public void setSpeakerService(SpeakerService speakerService);

    public void setPrice(double price) ;

    public double getPrice() ;

}
