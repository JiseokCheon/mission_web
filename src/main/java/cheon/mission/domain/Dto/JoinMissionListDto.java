package cheon.mission.domain.Dto;

import cheon.mission.domain.MissionStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class JoinMissionListDto {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime joinDate;
    private MissionStatus missionStatus;

    public JoinMissionListDto(String name, LocalDate startDate, LocalDate endDate, LocalDateTime joinDate, MissionStatus missionStatus) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.joinDate = joinDate;
        this.missionStatus = missionStatus;
    }
}
