package study.data_jpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import study.data_jpa.entity.Member;

@Repository
public class MemberJpaRepository {

    @PersistenceContext     // 스프링부트(스프링컨테이너)가 JPA에 있는 영속성 컨텍스트에 있는 EntityManager를 가져다 준다.
    private EntityManager em;
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}

