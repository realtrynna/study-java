package com.realtrynna.spring_start.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class CommentService implements PublicationInterface {
    @Override
    public void create(String title) {
        String comment = "This is a comment";

        return;
    }
}
