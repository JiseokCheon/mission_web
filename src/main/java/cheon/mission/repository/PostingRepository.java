package cheon.mission.repository;

import cheon.mission.domain.Posting;
import cheon.mission.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostingRepository {

    private final EntityManager em;

    public Long save(Posting posting) {
        em.persist(posting);
        return posting.getId();
    }

    public Optional<Posting> findById(Long id) {
        return Optional.ofNullable(em.find(Posting.class, id));
    }

    public List<Posting> findPostingByMissionId(Long missionId) {
        return em.createQuery("select p from Posting p where p.mission.id = :missionId")
                .setParameter("missionId", missionId)
                .getResultList();
    }

    public List<Posting> findPostingByUserId(Long UserId) {
        return em.createQuery("select p from Posting p where p.user.id = :UserId")
                .setParameter("UserId", UserId)
                .getResultList();
    }

    public int deletePosting(Long postingId) {
        return em.createQuery("delete from Posting p where p.id = :postingId")
                .setParameter("postingId", postingId)
                .executeUpdate();
    }

    public int deletePostingByMissionId(Long missionId) {
        return em.createQuery("delete from Posting p where p.mission.id = :missionId")
                .setParameter("missionId", missionId)
                .executeUpdate();
    }

}
