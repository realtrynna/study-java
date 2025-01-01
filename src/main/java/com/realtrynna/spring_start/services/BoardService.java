package com.realtrynna.spring_start.services;

import com.realtrynna.spring_start.Domain.Board;
import com.realtrynna.spring_start.repositories.BoardRepository;
import com.realtrynna.spring_start.repositories.JdbcTemplateBoardRepository;
import com.realtrynna.spring_start.repositories.JpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final JdbcTemplateBoardRepository jdbcTemplateBoardRepository;
    private final JpaRepository jpaRepository;

    BoardService(BoardRepository boardRepository, JdbcTemplateBoardRepository jdbcTemplateBoardRepository, JpaRepository jpaRepository) {
        this.boardRepository = boardRepository;
        this.jdbcTemplateBoardRepository = jdbcTemplateBoardRepository;
        this.jpaRepository = jpaRepository;
    }

    @Transactional
    public String createBoard(String title) {
        Board board = new Board();

        board.setTitle(title);

        jpaRepository.createBoard(board);

        return title;
    }
}