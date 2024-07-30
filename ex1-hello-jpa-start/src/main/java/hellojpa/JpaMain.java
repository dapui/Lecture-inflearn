package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            // 저장
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
//            member.changeTeam(team); // 주의! 연관관계 편의 메소드가 양쪽에 다 있으면 무한루프에 걸릴 수 있음.
            em.persist(member);

            // 역방향(주인이 아닌 방향)만 연관관계 설정
            // but 영속성 초기화를 하지 않을경우(or 테스트코드) 테이블 생성할때 이미 1차 캐시로 들어가가 때문에 select가 되지 않으므로 양쪽에 모두 설정해 주는 것이 좋다
            // 순수 객체 상태를 고려해서 항상 양쪽에 값을 설정하자
            // 연관관계 편의 메소드 사용 -> 1에 넣어도 되고, N에 넣어도 상관없다
            team.addMember(member);

            // 영속성 초기화
            em.flush(); // 현재까지의 변경사항을 데이터베이스에 즉시 반영
            em.clear(); // 영속성 컨텍스트에서 모든 엔티티를 분리

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();
            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
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
