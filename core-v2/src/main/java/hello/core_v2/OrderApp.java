package hello.core_v2;

import hello.core_v2.member.Grade;
import hello.core_v2.member.Member;
import hello.core_v2.member.MemberService;
import hello.core_v2.member.MemberServiceImpl;
import hello.core_v2.order.Order;
import hello.core_v2.order.OrderService;
import hello.core_v2.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }
}
