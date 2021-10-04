package cheon.mission.service;

import cheon.mission.domain.User;
import cheon.mission.domain.UserMission;
import cheon.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserMissionService {

    private final UserMissionRepository userMissionRepository;

    @Transactional
    public void save(UserMission userMission){
        validateDuplicate(userMission);
        userMissionRepository.save(userMission);
    }

    private void validateDuplicate(UserMission userMission) {
        userMissionRepository.findByMissionIdAndUserId(userMission.getMission().getId(), userMission.getUser().getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 참가한 미션입니다.");
                });
    }
}
