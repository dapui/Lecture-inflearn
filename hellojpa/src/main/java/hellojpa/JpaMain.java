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
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("관리자");
            member.setAge(10);
            member.setTeam(team);
            member.setType(MemberType.ADMIN);
            em.persist(member);

            em.flush();
            em.clear();

            /**
             * JPQL 기본 함수
             * CONCAT
             * SUBSTRING
             * TRIM
             * LOWER, UPPER
             * LENGTH
             * LOCATE
             * ABS, SQRT, MOD
             * SIZE, INDEX(JPA 용도)
             */
//            String query = "select concat('a', 'b') from Member m";
//            String query = "select 'a' || 'b' from Member m"; // 하이버네이트에서 제공
//            String query = "select substring(m.username, 2, 3) from Member m";
//            String query = "select trim(m.username) from Member m";
//            String query = "select lower(m.username) from Member m";
//            String query = "select locate('de','abcdef') from Member m";
//            String query = "select size(t.members) from Team t";
//            String query = "select index(t.members) from Team t";   // 사용 안하는걸 추천
//            String query = "select index(t.members) from Team t";

            // 사용자 정의 함수 호출
//            String query = "select function('group_concat', m.username) from Member m";
            String query = "select group_concat(m.username) from Member m"; // 하이버네이트에서 제공

            List<String> result = em.createQuery(query, String.class).getResultList();

            for (String s : result) {
                System.out.println("s = " + s);
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