package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            em.persist(member2);

            em.flush();
            em.clear();

            Member m1 = em.find(Member.class, member1.getId()); // DB에서 조회
//            Member m2 = em.find(Member.class, member2.getId());
            Member m2 = em.getReference(Member.class, member2.getId()); // 프록시 클래스

            System.out.println("isLoaded : " + emf.getPersistenceUnitUtil().isLoaded(m2));  // 프록시 인스턴스의 초기화 여부 확인
            Hibernate.initialize(m2);   // 프록시 강제 초기화

            logic(m1, m2);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void logic(Member m1, Member m2) {
        System.out.println("m1 == m2 : " + (m1.getClass() == m2.getClass()));   // m1, m2 = em.find일경우 true -> m1 = em.find, m2 = em.getReference일경우 false
        System.out.println("m1 == m2 : " + (m1 instanceof Member)); // 타입비교를 할 때는 == 비교를 하지말고 instanceof로 비교해야한다!
        System.out.println("m1 == m2 : " + (m2 instanceof Member));
    }

}
