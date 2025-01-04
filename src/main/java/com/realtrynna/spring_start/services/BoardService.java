package com.realtrynna.spring_start.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("mainPublicationService")
public class BoardService implements PublicationInterface {
    private final SomethingInterface somethingInterface;

    public BoardService(@Qualifier("doSomethingService") SomethingInterface somethingInterface) {
        this.somethingInterface = somethingInterface;
    }

    @Override
//    @Transactional
    public void create(String title) {
        Boolean result = somethingInterface.getIs();
        System.out.println(result);
    }
}