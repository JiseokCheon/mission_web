package cheon.mission.service;

import cheon.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SchedulerService {

    private final UserMissionRepository userMissionRepository;

    // 오늘 미션 현황 초기화
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void todayCheckUpdate(){
        userMissionRepository.everyPostingCheckfalse();
    }

}
