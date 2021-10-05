package cheon.mission.service;

import cheon.mission.auth.Dto.SessionUser;
import cheon.mission.domain.Mission;
import cheon.mission.domain.User;
import cheon.mission.repository.MissionRepository;
import cheon.mission.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(Mission mission){
        missionRepository.save(mission);
        return mission.getId();
    }

    public List<Mission> findMissions(String name){
        return missionRepository.findByName(name);
    }

    public List<Mission> findAll(){
        return missionRepository.findAll();
    }

    public Mission findById(Long id){
        return missionRepository.findById(id);
    }

    public List<Mission> findByUserId(SessionUser user){
        Optional<User> findUser = userRepository.findByEmail(user.getEmail());

        return missionRepository.findByUserId(findUser.get().getId());
    }
}
