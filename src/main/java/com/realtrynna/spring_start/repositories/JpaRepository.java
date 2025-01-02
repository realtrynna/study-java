package com.realtrynna.spring_start.repositories;

import com.realtrynna.spring_start.domain.Board;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class JpaRepository {
    private final EntityManager em;

    public JpaRepository(EntityManager em) {
        this.em = em;
    }

    public Board createBoard(Board board) {
        em.persist(board);
        return board;
    }
}
