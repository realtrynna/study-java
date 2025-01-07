package com.realtrynna.spring_start.user;

import com.realtrynna.spring_start.board.model.Board;
import com.realtrynna.spring_start.user.model.User;
import com.realtrynna.spring_start.user.model.request.CreateUserDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final DataSource dataSource;
    private final EntityManager em;

    public UserController(final DataSource dataSource, final EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @PostMapping("sign-up")
    @Transactional
    public String signUp(@RequestBody CreateUserDto createUserDto) {
//        User user = new User();
//        user.setName("윤승근");
//        user.setEmail("realtrynna@gmail.com");
//        user.setPassword("123456");
//
//        em.persist(user);
//
//        Board board = new Board();
//        board.setTitle("윤승근이 작성한 게시글");
//        board.setContent("게시글 본문");
//        /**
//         * 단방향 연관관계 참조 저장
//         */
//        board.setUser(user);
//
//        em.persist(board);

        User findUser = em.find(User.class, 1);

        List<Board> boardList = findUser.getBoardList();
        for (Board board : boardList) {
            System.out.println(board.getTitle());
        }

        return "here";
    }
}
