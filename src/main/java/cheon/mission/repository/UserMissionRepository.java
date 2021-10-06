package cheon.mission.repository;

import cheon.mission.domain.Dto.JoinMissionListDto;
import cheon.mission.domain.Dto.Participant;
import cheon.mission.domain.Mission;
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

    public List<Participant> findParticipantByMissionId(Long missionId) {
        List<Participant> resultList = em.createQuery("" +
                                "select new cheon.mission.domain.Dto.Participant(u.name, u.email, um.joinTime) " +
                                "from UserMission um join um.user u where um.mission.id = :missionId",
                        Participant.class)
                .setParameter("missionId", missionId)
                .getResultList();
        return resultList;
    }

    public List<JoinMissionListDto> findMissionListByUserId(Long userId) {
        List<JoinMissionListDto> resultList = em.createQuery("" +
                                "select new cheon.mission.domain.Dto.JoinMissionListDto(m.id, m.name, m.startDate,m.endDate, um.joinTime, m.missionStatus) " +
                                "from UserMission um join um.mission m where um.user.id = :userId",
                        JoinMissionListDto.class)
                .setParameter("userId", userId)
                .getResultList();
        return resultList;
    }

    public List<Mission> findMissionByUserId(Long userId) {
        List<Mission> resultList = em.createQuery("" +
                                "select m " +
                                "from UserMission um " +
                                "join um.mission m " +
                                "where um.user.id = :userId",
                        Mission.class)
                .setParameter("userId", userId)
                .getResultList();
        return resultList;
    }

    public int deleteOneUserMission(Long userId, Long missionId) {
        return em.createQuery("delete from UserMission um where um.mission.id = : missionId and um.user.id = :userId")
                .setParameter("missionId", missionId)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    public int deleteUserMissions(Long missionId) {
        return em.createQuery("delete from UserMission um where um.mission.id = : missionId")
                .setParameter("missionId", missionId)
                .executeUpdate();
    }
}
