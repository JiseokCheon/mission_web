package cheon.mission.security.dto;

import cheon.mission.domain.User;
import lombok.Getter;

import java.io.Serializable;


/**
 * User 엔티티를 세션에 저장하려고 하면 직렬화를 구현하지 않았다는 에러 발생
 * 직렬화 기능을 가진 Dto
 */

@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;


    public SessionUser(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
