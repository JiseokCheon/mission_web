package cheon.mission.repository;

import cheon.mission.domain.Mission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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

}
