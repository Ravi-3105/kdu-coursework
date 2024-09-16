package org.example.config;

import org.example.beans.SpeakerService;
import org.springframework.context.annotation.Bean;
public class SpeakerConfig {

    @Bean("Sony")
    SpeakerService speaker1(){
        SpeakerService speaker1 = new SpeakerService();
        speaker1.setName("Sony");
        speaker1.setPrice(1500.0);
        return speaker1;
    }

    @Bean("Bose")
    SpeakerService speaker2(){
        SpeakerService speaker2 = new SpeakerService();
        speaker2.setName("Bose");
        speaker2.setPrice(1800.0);
        return speaker2;
    }
}
