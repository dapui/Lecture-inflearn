package hello.core_v2.member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}
