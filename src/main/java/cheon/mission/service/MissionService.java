package cheon.mission.service;

import cheon.mission.domain.Mission;
import cheon.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

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
}
