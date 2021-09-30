package cheon.mission.service;

import cheon.mission.domain.Mission;
import cheon.mission.repository.MissionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

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
    public void 미션생성(){
        Mission mission1 = new Mission("미션 1", "미션 내용");
        Mission mission2 = new Mission("미션 2", "미션 내용");

        Long saveId1 = missionService.join(mission1);
        Long saveId2 = missionService.join(mission2);

        assertEquals(mission1, missionRepository.findById(saveId1));
        assertNotEquals(mission2, missionRepository.findById(saveId1));
    }

}