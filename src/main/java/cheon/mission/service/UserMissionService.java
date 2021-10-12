package cheon.mission.service;

import cheon.mission.auth.Dto.SessionUser;
import cheon.mission.domain.Dto.JoinMissionListDto;
import cheon.mission.domain.Dto.Participant;
import cheon.mission.domain.Mission;
import cheon.mission.domain.User;
import cheon.mission.domain.UserMission;
import cheon.mission.repository.PostingRepository;
import cheon.mission.repository.UserMissionRepository;
import cheon.mission.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserMissionService {

    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;
    private final PostingRepository postingRepository;

    @Transactional
    public void save(UserMission userMission){
        validateDuplicate(userMission);
        userMissionRepository.save(userMission);
    }

    public List<Participant> findByMissionId(Long missionId){
        return userMissionRepository.findParticipantByMissionId(missionId);
    }


    public List<JoinMissionListDto> findJoinMissionByUserId(SessionUser user){
        Optional<User> findUser = userRepository.findByEmail(user.getEmail());

        return userMissionRepository.findMissionListByUserId(findUser.get().getId());
    }

    private void validateDuplicate(UserMission userMission) {
        userMissionRepository.findByMissionIdAndUserId(userMission.getMission().getId(), userMission.getUser().getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 참가한 미션입니다.");
                });
    }

    @Transactional
    public int deleteUserMission(SessionUser user, Long missionId){
        Optional<User> findUser = userRepository.findByEmail(user.getEmail());
        postingRepository.deletePostingByMissionId(missionId);
        return userMissionRepository.deleteOneUserMission(findUser.get().getId(), missionId);
    }

}
