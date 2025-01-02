package com.realtrynna.spring_start.controllers;

import com.realtrynna.spring_start.services.BoardService;
import com.realtrynna.spring_start.services.PublicationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardController {
    private final PublicationInterface publicationInterface;

    public BoardController(@Qualifier("mainPublicationService") PublicationInterface publicationInterface) {
//        this.boardService = boardService;
//
//        System.out.println("Proxy" + boardService.getClass());
        this.publicationInterface = publicationInterface;
        System.out.println("인터페이스" + this.publicationInterface);
    }

    @PostMapping("board")
    public void createBoard(
            @RequestParam(required = true, value="name") String name,
            @RequestParam(required = true, value ="age") String age
        )  {
        String str = publicationInterface.create("ssss");
        System.out.println(str);
//        boardService.create(name);
    }
}
