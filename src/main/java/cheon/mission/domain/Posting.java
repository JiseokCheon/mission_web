package cheon.mission.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
public class Posting {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String context;

    @Column(nullable = false)
    private LocalDate writeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Posting(String title, String context, LocalDate writeDate) {
        this.title = title;
        this.context = context;
        this.writeDate = writeDate;
    }

    // 양방향 연관관계
    public void setPostingList(Mission mission, User user) {
        this.mission = mission;
        this.user = user;
        mission.getPostingList().add(this);
        user.getPostingList().add(this);
    }
}
