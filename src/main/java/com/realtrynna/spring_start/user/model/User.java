package com.realtrynna.spring_start.user.model;

import com.realtrynna.spring_start.board.model.Board;
import com.realtrynna.spring_start.common.exception.BaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter @Setter
public class User extends BaseEntity {
    @Column(length = 32, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Board> boardList = new ArrayList<Board>();
}




