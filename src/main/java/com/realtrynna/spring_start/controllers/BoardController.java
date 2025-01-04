package com.realtrynna.spring_start.controllers;

import com.realtrynna.spring_start.domain.request.CreateBoardRequest;
import com.realtrynna.spring_start.services.BoardService;
import com.realtrynna.spring_start.services.PublicationInterface;
import com.realtrynna.spring_start.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class BoardController {
    private final PublicationInterface publicationInterface;
    private final S3Service s3Service;

    public BoardController(@Qualifier("mainPublicationService") PublicationInterface publicationInterface, S3Service s3Service) {
        this.publicationInterface = publicationInterface;
        this.s3Service = s3Service;
    }

    @PostMapping("board")
    public void createBoard(
        @RequestBody CreateBoardRequest createBoardRequest
    ) {
        CreateBoardRequest board = new CreateBoardRequest("게시판 제목입니다.");

        System.out.println(board);
    }
}
