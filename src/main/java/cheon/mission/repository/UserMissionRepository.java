package cheon.mission.repository;

import cheon.mission.domain.Dto.Participant;
import cheon.mission.domain.UserMission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserMissionRepository {

    private final EntityManager em;

    public UserMission save(UserMission userMission) {
        em.persist(userMission);
        return userMission;
    }

    public Optional<UserMission> findByMissionIdAndUserId(Long missionId, Long userId) {
        List<UserMission> resultList = em.createQuery("" +
                        "select um from UserMission um " +
                        "where um.mission.id = :missionId " +
                        "and um.user.id = :userId", UserMission.class)
                .setParameter("missionId", missionId)
                .setParameter("userId", userId)
                .getResultList();
        return resultList.stream().findAny();
    }

    public List<Participant> findUserByMissionId(Long missionId) {
        List<Participant> resultList = em.createQuery("" +
                        "select new cheon.mission.domain.Dto.Participant(u.name, u.email, um.joinTime) " +
                        "from UserMission um join um.user u where um.mission.id = :missionId",
                        Participant.class)
                .setParameter("missionId", missionId)
                .getResultList();
        return resultList;
    }

}
