package cheon.mission.service;

import cheon.mission.domain.Mission;
import cheon.mission.domain.MissionStatus;
import cheon.mission.repository.MissionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MissionServiceTest {

    @Autowired
    MissionRepository missionRepository;

    @Autowired
    MissionService missionService;

    @Test
    public void 미션생성() {
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();

        Mission mission1 = new Mission("미션 1", "미션 내용", start, end, "code", MissionStatus.PROGRESS);
        Mission mission2 = new Mission("미션 2", "미션 내용", start, end, "code", MissionStatus.PROGRESS);

        Long saveId1 = missionService.save(mission1);
        Long saveId2 = missionService.save(mission2);

        assertEquals(mission1, missionRepository.findById(saveId1));
        assertNotEquals(mission2, missionRepository.findById(saveId1));
    }

    @Test
    public void 미션수정() {
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        Mission mission = new Mission("미션 1", "미션 내용", start, end, "code", MissionStatus.PROGRESS);
        Long saveId = missionService.save(mission);

        missionService.updateMission(saveId, "수정된 미션", "수정된 내용", start, end, "수정코드");

        assertEquals(missionService.findById(saveId).getName(), "수정된 미션");
        assertEquals(missionService.findById(saveId).getCode(), "수정코드");
        assertEquals(missionService.findById(saveId).getContext(), "수정된 내용");

    }
}