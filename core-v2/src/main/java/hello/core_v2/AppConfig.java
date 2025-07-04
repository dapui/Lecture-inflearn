package hello.core_v2;

import hello.core_v2.discount.DiscountPolicy;
import hello.core_v2.discount.FixDiscountPolicy;
import hello.core_v2.discount.RateDiscountPolicy;
import hello.core_v2.member.MemberRepository;
import hello.core_v2.member.MemberService;
import hello.core_v2.member.MemberServiceImpl;
import hello.core_v2.member.MemoryMemberRepository;
import hello.core_v2.order.OrderService;
import hello.core_v2.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
