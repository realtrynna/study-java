package com.realtrynna.spring_start.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.realtrynna.spring_start.domain.Board;
import com.realtrynna.spring_start.repositories.BoardRepository;
import com.realtrynna.spring_start.repositories.JdbcTemplateBoardRepository;
import com.realtrynna.spring_start.repositories.JpaRepository;
import com.realtrynna.spring_start.repositories.DataJpaRepository;

import java.util.Optional;

@Service
@Qualifier("mainPublicationService")
public class BoardService implements PublicationInterface {
    private final BoardRepository boardRepository;
    private final JdbcTemplateBoardRepository jdbcTemplateBoardRepository;
    private final JpaRepository jpaRepository;
    private final DataJpaRepository dataJpaRepository;

    BoardService(BoardRepository boardRepository, JdbcTemplateBoardRepository jdbcTemplateBoardRepository, JpaRepository jpaRepository, DataJpaRepository dataJpaRepository) {
        this.boardRepository = boardRepository;
        this.jdbcTemplateBoardRepository = jdbcTemplateBoardRepository;
        this.jpaRepository = jpaRepository;
        this.dataJpaRepository = dataJpaRepository;
    }

    @Override
    @Transactional
    public String create(String title) {
        Board board = new Board();

        board.setTitle(title);

        dataJpaRepository.save(board);

        Optional<Board> result = dataJpaRepository.findByIdAndTitle(1L, "윤승근");

        System.out.println("결과" + result);

        return title;
    }
}