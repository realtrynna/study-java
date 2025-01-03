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
    private BoardRepository boardRepository;
    private JdbcTemplateBoardRepository jdbcTemplateBoardRepository;
    private JpaRepository jpaRepository;
    private DataJpaRepository dataJpaRepository;
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