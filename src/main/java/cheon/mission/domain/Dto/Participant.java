package cheon.mission.domain.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Participant {
    private String name;
    private String email;
    private LocalDateTime joinTime;

    public Participant(String name, String email, LocalDateTime joinTime) {
        this.name = name;
        this.email = email;
        this.joinTime = joinTime;
    }
}
