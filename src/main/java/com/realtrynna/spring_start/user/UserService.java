package com.realtrynna.spring_start.user;

import com.realtrynna.spring_start.user.model.User;
import com.realtrynna.spring_start.user.model.request.CreateUserDto;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EntityManager em;

    public Boolean existUser(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public void create(CreateUserDto createUserDto) {
        User user = new User();
        user.setEmail(createUserDto.getEmail());
        user.setPassword(createUserDto.getPassword());
        user.setName(createUserDto.getName());

        /**
         * 변경 감지를 통해 엔티티 스냅샷(최초 상태)과 현재 상태를 비교합니다.
         * 변경 사항이 발견되면 지연 저장소에 업데이트 쿼리를 생성해둡니다.
         * ORM의 성능을 판단하는 지표 중 하나는 데이터베이스의 접근 횟수.
         */
        em.persist(user);
        user.setEmail("윤석열 탄핵");
        Optional<User> userOptional = userRepository.findByEmail("윤석열 탄핵");

        System.out.println(userOptional.isPresent());
    }
}
