package com.realtrynna.spring_start.services;

import org.springframework.stereotype.Service;

@Service
public class SomethingService implements SomethingInterface {
    @Override
    public Boolean getIs() {
        return false;
    }
}
