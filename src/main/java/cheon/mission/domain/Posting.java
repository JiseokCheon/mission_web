package cheon.mission.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@ToString
public class Posting {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String context;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Posting(String title, String context) {
        this.title = title;
        this.context = context;
    }

    // 양방향 연관관계
    public void setPostingList(Mission mission, User user) {
        this.mission = mission;
        this.user =user;
        mission.getPostingList().add(this);
        user.getPostingList().add(this);
    }
}
