package org.example.config;

import org.example.beans.TyreService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public class TyreConfig {
    @Bean("Bridgestone")
    TyreService tyre1(){
        TyreService tyre1 = new TyreService();
        tyre1.setName("Bridgestone");
        tyre1.setPrice(5000.0);
        return tyre1;
    }

    @Bean("MRF")
    TyreService tyre2(){
        TyreService tyre2 = new TyreService();
        tyre2.setName("MRF");
        tyre2.setPrice(6000.0);
        return tyre2;
    }
}
