package com.realtrynna.spring_start.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class DoSomethingService implements SomethingInterface {
    @Override
    public Boolean getIs() {
        return true;
    }
}
