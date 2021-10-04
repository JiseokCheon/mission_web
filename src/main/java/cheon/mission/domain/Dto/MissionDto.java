package cheon.mission.domain.Dto;

import lombok.Data;

@Data
public class MissionDto {
    private Long id;
    private String name;
    private String code;
    private String context;
    private String startDate;
    private String endDate;
}
