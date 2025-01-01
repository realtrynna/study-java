package com.realtrynna.spring_start.repositories;

import com.realtrynna.spring_start.Domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateBoardRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateBoardRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Optional<Board> findByName(String name) {
        System.out.println("레포지토리 호출");

        List<Board> result = jdbcTemplate.query("SELECT * FROM member WHERE name = ?", boardMapper(), name);
        return result.stream().findAny();
    }

    private RowMapper<Board> boardMapper() {
        return new RowMapper<Board>() {
            @Override
            public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
                Board board = new Board();

                System.out.println("게시글 조회 결과" + rs.getString("name"));

                board.setTitle(rs.getString("name"));
                return board;
            }
        };
    }
}
