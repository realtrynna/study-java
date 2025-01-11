package com.realtrynna.spring_start.user;

import com.realtrynna.spring_start.user.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
