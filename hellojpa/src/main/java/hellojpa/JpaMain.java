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
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);  // 반환타입이 명확할 때
            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);
            Query query3 = em.createQuery("select m.username, m.age from Member m");  // 반환타입이 명확하지 않을 때

            List<Member> resultList = query1.getResultList();   // getResultList -> 컬렉션이 반환됨 :: 결과가 없으면 빈 리스트 반환
            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1);
            }

            Member result = query1.getSingleResult();   // getSingleResult -> 반환값이 하나일 때 :: 결과가 없거나 둘 이상이면 Exception (주의!)

            // 파라미터 바인딩
//            TypedQuery<Member> query4 = em.createQuery("select m from Member m where m.username = :username", Member.class);
//            query4.setParameter("username", "member1");
//            Member singleResult = query4.getSingleResult();
//            System.out.println("singleResult = " + singleResult.getUsername());
            // 메서드 체인
            Member result2 = em.createQuery("select m from Member m where m.username = :username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();
            System.out.println("result2 = " + result2.getUsername());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}