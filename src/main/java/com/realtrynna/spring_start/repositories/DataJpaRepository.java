package com.realtrynna.spring_start.repositories;

import com.realtrynna.spring_start.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DataJpaRepository extends JpaRepository<Board, Long> {
    Board save(Board board);
    Optional<Board> findByTitle(String title);
    Optional<Board> findByIdAndTitle(Long id, String title);
}
