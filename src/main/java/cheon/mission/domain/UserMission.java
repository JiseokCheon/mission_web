package cheon.mission.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 다대다 관계를 위한 연결 테이블
* */
@Getter
@Entity
@NoArgsConstructor
public class UserMission {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "mission_id")
    private Mission mission;

    public void setUserMission(User user) {
        this.user = user;
        user.getUserMissionList().add(this);
    }

    public void setMissionUser(Mission mission) {
        this.mission = mission;
        mission.getUserMissionList().add(this);
    }

}
