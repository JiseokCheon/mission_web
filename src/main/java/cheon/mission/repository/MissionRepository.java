package cheon.mission.repository;

import cheon.mission.domain.Mission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MissionRepository {

    private final EntityManager em;

    public Long save(Mission mission) {
        em.persist(mission);
        return mission.getId();
    }

    public Mission findById(Long id) {
        return em.find(Mission.class, id);
    }

    public List<Mission> findAll() {
        return em.createQuery("select m from Mission m", Mission.class)
                .getResultList();
    }

    public List<Mission> findByName(String name) {
        return em.createQuery("select m from Mission m where m.name like :name", Mission.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    public List<Mission> findByUserId(Long userId) {
        return em.createQuery("select m from Mission m where m.owner.id =:userId", Mission.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public Optional<Mission> findByOwnerIdAndMissionId(Long missionId, Long ownerId) {
        return em.createQuery("select m from Mission m where m.owner.id =:ownerId and m.id =:missionId", Mission.class)
                .setParameter("ownerId", ownerId)
                .setParameter("missionId", missionId)
                .getResultList()
                .stream().findAny();
    }

    public int deleteMission(Long missionId) {
        return em.createQuery("delete from Mission m where m.id = :missionId")
                .setParameter("missionId", missionId)
                .executeUpdate();
    }
}
