package org.example.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({TyreConfig.class,SpeakerConfig.class})
@ComponentScan({"org.example.beans"})
public class Factory2Config {

}
