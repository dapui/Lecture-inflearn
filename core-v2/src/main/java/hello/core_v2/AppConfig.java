package hello.core_v2;

import hello.core_v2.discount.FixDiscountPolicy;
import hello.core_v2.member.MemberService;
import hello.core_v2.member.MemberServiceImpl;
import hello.core_v2.member.MemoryMemberRepository;
import hello.core_v2.order.OrderService;
import hello.core_v2.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
