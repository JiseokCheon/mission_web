package cheon.mission.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

    @Scheduled(cron = "*/5 * * * * *")
    public void test(){
        System.out.println("스케줄링 테스트");
    }

}
