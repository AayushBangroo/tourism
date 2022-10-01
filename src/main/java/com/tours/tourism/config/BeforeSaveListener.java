package com.tours.tourism.config;

import com.tours.tourism.entities.Tour;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public class BeforeSaveListener extends AbstractMongoEventListener<Tour> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Tour> event){
        System.out.println(event.getSource());
    }
}
