package cheon.mission.domain.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MissionDto {
    private Long id;
    private String name;
    private String code;
    private String context;
    private String startDate;
    private String endDate;
}
