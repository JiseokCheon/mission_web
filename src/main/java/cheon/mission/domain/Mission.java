package cheon.mission.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@ToString
public class Mission {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(mappedBy = "mission")
    private List<UserMission> userMissionList = new ArrayList<>();

    @Column(nullable = false)
    private String context;

    @Column(nullable = false)
    private String code;

    @OneToMany(mappedBy = "mission")
    private List<Posting> postingList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MissionStatus missionStatus;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    public Mission(String name, String context, LocalDate startDate, LocalDate endDate, String code, MissionStatus missionStatus) {
        this.name = name;
        this.context = context;
        this.startDate = startDate;
        this.endDate = endDate;
        this.code = code;
        this.missionStatus = missionStatus;
    }

    public void missionUpdate(String name, String context, String code, LocalDate startDate, LocalDate endDate){
            this.name = name;
            this.context = context;
            this.startDate = startDate;
            this.endDate = endDate;
            this.code = code;
    }

    // 양방향 연관 관계
    public void setOwner(User user) {
        this.owner = user;
        user.getMissions().add(this);
    }

    public String getOwnerName() {
        return owner.getName();
    }
}
