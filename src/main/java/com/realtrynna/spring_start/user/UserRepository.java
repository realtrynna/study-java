package com.realtrynna.spring_start.user;

import com.realtrynna.spring_start.user.model.User;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

    Optional<User> findByEmail(String email);



    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from User u where u.email = ?1")
    void deleteByEmail(String email);
}
