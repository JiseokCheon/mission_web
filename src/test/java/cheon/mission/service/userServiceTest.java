package cheon.mission.service;


import cheon.mission.domain.User;
import cheon.mission.domain.Role;
import cheon.mission.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
public class userServiceTest {

    @Autowired
    userService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void 회원가입() {
        User user = new User("천지석", "cjswltjr", Role.USER);

        Long saveId = userService.join(user);

        User findUser = userRepository.findById(saveId).get();

        System.out.println("findMember = " + findUser.toString());
        System.out.println("member = " + user.toString());

        assertEquals(user, findUser);
    }

    @Test
    @DisplayName("로그인아이디로 찾기")
    public void 아이디로찾기() {
        User user = new User("천지석", "cjswltjr", Role.USER);

        userService.join(user);

        User findUser = userRepository.findByEmail(user.getEmail()).get();

        System.out.println("findMember = " + findUser.toString());

        assertEquals(user, findUser);
    }


    @Test
    @DisplayName("아이디 중복이면 예외")
    public void 아이디중복() {
        User user1 = new User("천지석", "cjswltjr", Role.USER);
        User user2 = new User("천지석", "cjswltjr", Role.USER);

        userService.join(user1);

        System.out.println(user1.toString());
        System.out.println(user2.toString());

        assertThrows(IllegalStateException.class, () -> {
            userService.join(user2);
        });
    }
}