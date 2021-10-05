package cheon.mission.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MissionStatus {
    BEFORE("BEFORE", "진행전"),
    PROGRESS("PROGRESS", "진행중"),
    TERMINATE("TERMINATE", "종료");

    private final String key;
    private final String title;

}
