package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity", "street", "12345"));
            
            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("old1", "street", "12345"));
            member.getAddressHistory().add(new AddressEntity("old2", "street", "12345"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("========== START ==========");
            Member findMember = em.find(Member.class, member.getId());

            // homeCity -> newCity
//            findMember.getHomeAddress().setCity("newCity");   // 잘못된 방법 (각 타입의 필드 하나만 갈아끼운다 -> 불가)
            Address oldAddress = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", oldAddress.getStreet(), oldAddress.getZipcode())); // 새로운 인스턴스로 갈아끼워야 함 -> 교체

            // 치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

//            findMember.getAddressHistory().remove(new Address("old1", "street", "12345"));
//            findMember.getAddressHistory().add(new Address("newCity1", "street", "12345"));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

}
