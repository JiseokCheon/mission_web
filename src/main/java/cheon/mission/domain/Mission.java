package cheon.mission.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Mission {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String context;

    public Mission(String name, String context) {
        this.name = name;
        this.context = context;
    }

    // 양방향 연관 관계
    public void setUser(User user){
        this.user = user;
        user.getMissions().add(this);
    }
}
