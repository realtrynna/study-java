package com.realtrynna.spring_start.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class S3Service {
    public void upload(MultipartFile file) throws IOException {
        List<String> filenameList = new ArrayList<>();

        try {
            System.out.println(file.getInputStream());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return;
    }
}
