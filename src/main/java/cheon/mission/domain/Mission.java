package cheon.mission.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Mission {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    @Column(nullable = false)
    private String context;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    public Mission(String name, String context, LocalDate startDate, LocalDate endDate, String code) {
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
