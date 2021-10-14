package cheon.mission.service;

import cheon.mission.security.dto.SessionUser;
import cheon.mission.domain.Mission;
import cheon.mission.domain.User;
import cheon.mission.repository.MissionRepository;
import cheon.mission.repository.PostingRepository;
import cheon.mission.repository.UserMissionRepository;
import cheon.mission.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;
    private final PostingRepository postingRepository;

    @Transactional
    public Long save(Mission mission) {
        missionRepository.save(mission);
        return mission.getId();
    }

    public List<Mission> findMissions(String name) {
        return missionRepository.findByName(name);
    }

    public List<Mission> findAll() {
        return missionRepository.findAll();
    }

    public Mission findById(Long id) {
        return missionRepository.findById(id);
    }

    public Mission findByMissionIdAndOwnerId(Long missionId, Long ownerId) {
        Mission mission = missionRepository.findByOwnerIdAndMissionId(missionId, ownerId).orElseThrow(() -> {
            throw new IllegalStateException("미션 수정할 권한 없음");
        });
        return mission;
    }

    public List<Mission> findByUserId(SessionUser user) {
        Optional<User> findUser = userRepository.findByEmail(user.getEmail());

        return missionRepository.findByUserId(findUser.get().getId());
    }

    @Transactional
    public int deleteMission(Long missionId) {
        userMissionRepository.deleteUserMissions(missionId);
        postingRepository.deletePostingByMissionId(missionId);
        return missionRepository.deleteMission(missionId);
    }

    @Transactional
    public Mission updateMission(Long missionId, String name, String context, LocalDate startDate, LocalDate endDate, String code) {
        Mission mission = missionRepository.findById(missionId);
        mission.missionUpdate(name, context, code, startDate, endDate);
        return mission;
    }

}
