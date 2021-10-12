package cheon.mission.domain.Dto;

import lombok.Data;

@Data
public class PostingDto {
    private String title;
    private String context;
    private Long missionId;
}
