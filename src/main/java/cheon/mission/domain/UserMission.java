package cheon.mission.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(nullable = false)
    private LocalDateTime joinTime;

    @Column(nullable = false)
    private boolean postingCheck;

    public void setUserMission(User user) {
        this.user = user;
        user.getUserMissionList().add(this);
    }

    public void setMissionUser(Mission mission) {
        this.mission = mission;
        mission.getUserMissionList().add(this);
    }

    public void updatePostingCheck(){
        this.postingCheck = true;
    }

    public UserMission(LocalDateTime joinTime, boolean postingCheck) {
        this.joinTime = joinTime;
        this.postingCheck = postingCheck;
    }
}
