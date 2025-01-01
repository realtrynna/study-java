package com.realtrynna.spring_start.controllers;

import com.realtrynna.spring_start.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("board")
    public void createBoard(
            @RequestParam(required = true, value="name") String name,
            @RequestParam(required = true, value ="age") String age
        )  {
        boardService.createBoard(name);
    }
}
