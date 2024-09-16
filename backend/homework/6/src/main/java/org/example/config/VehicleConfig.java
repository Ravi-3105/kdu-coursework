package org.example.config;

import org.example.beans.SpeakerService;
import org.example.beans.TyreService;
import org.example.beans.VehicleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({TyreConfig.class,SpeakerConfig.class})
@ComponentScan(basePackageClasses = VehicleService.class)
public class VehicleConfig {

}
