package org.example.blueprint;

import org.example.beans.SpeakerService;
import org.example.beans.TyreService;

public interface VehicleService {

    double PRICE = 0;

    TyreService tyreService = null;

    SpeakerService speakerService = null;

    public SpeakerService getSpeakerService() ;

    public TyreService getTyreService() ;


    public void setTyreService(TyreService tyreService) ;

    public void setSpeakerService(SpeakerService speakerService);

    public void setPrice(double price) ;

    public double getPrice() ;

}
