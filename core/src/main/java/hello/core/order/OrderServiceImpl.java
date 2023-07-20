package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
    주문 서비스 구현체
 */
@Component
public class OrderServiceImpl implements OrderService {

    /*
        인터페이스에만 의존하도록 설계와 코드를 변경했다.
        그런데 구현체가 없는데 어떻게 코드를 실행할 수 있을까?
        실제 실행을 해보면 NPE(null pointer exception)가 발생한다.

        # 해결방안
        이 문제를 해결하려면 누군가가 클라이언트인 OrderServiceImpl 에 DiscountPolicy 의 구현 객체를 대신 생성하고 주입해주어야 한다
        AppConfig 등장
     */

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

/*
    @Autowired(required = false)  // 수정자 주입(setter 주입), 'required = false' 주입할 대상이 없어도 동작 가능해 진다.
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired  // 수정자 주입(setter 주입)
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
*/

    @Autowired  // 생성자 주입
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /*
        설계 변경으로 OrderServiceImpl 은 FixDiscountPolicy 를 의존하지 않는다!
        단지 DiscountPolicy 인터페이스만 의존한다
        OrderServiceImpl 입장에서 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)는 알 수 없다.
        OrderServiceImpl 의 생성자를 통해서 어떤 구현 객체을 주입할지는 오직 외부( AppConfig )에서 결정한다.
        OrderServiceImpl 은 이제부터 실행에만 집중하면 된다.
        OrderServiceImpl 에는 MemoryMemberRepository , FixDiscountPolicy 객체의 의존관계가 주입된다.
     */
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
