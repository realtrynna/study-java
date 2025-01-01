package com.realtrynna.spring_start.repositories;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class BoardRepository {
    private final DataSource dataSource;

    public BoardRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createBoard(String title) {
        String sql = "insert into member (name) values (?)";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

       try {
           con = dataSource.getConnection();
           ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

           ps.setString(1, title);
           rs = ps.getGeneratedKeys();
           ps.executeUpdate();

           System.out.println("결과값" + rs);
       } catch (Exception e) {
         throw new IllegalStateException(e);
       } finally {
       }
    }
}
