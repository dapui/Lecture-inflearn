package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();

            // 엔티티 직접 사용 - 기본 키 값
            // 엔티티를 파라미터로 전달
//            String query = "select m from Member m where m = :member";
//            Member findMember = em.createQuery(query, Member.class)
//                    .setParameter("member", member1)
//                    .getSingleResult();
//            System.out.println("findMember = " + findMember);

            // 식별자를 직접 전달
//            String query = "select m from Member m where m.id = :memberId";
//            Member findMember = em.createQuery(query, Member.class)
//                    .setParameter("memberId", member1.getId())
//                    .getSingleResult();
//            System.out.println("findMember = " + findMember);
            
            // 엔티티 직접 사용 - 외래 키 값
            // 엔티티를 파라미터로 전달
//            String query = "select m from Member m where m.team = :team";
//            List<Member> members = em.createQuery(query, Member.class)
//                    .setParameter("team", teamA)
//                    .getResultList();
//
//            for (Member member : members) {
//                System.out.println("member = " + member);
//            }

            // 식별자를 직접 전달
            String query = "select m from Member m where m.team.id = :teamId";
            List<Member> members = em.createQuery(query, Member.class)
                    .setParameter("teamId", teamA.getId())
                    .getResultList();

            for (Member member : members) {
                System.out.println("member = " + member);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}