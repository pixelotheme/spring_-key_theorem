package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl();

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();

        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);

        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000);
        Assertions.assertThat(order.getDiscountPrice()).isNotEqualTo(1000);


    }

//    @Test
//    @DisplayName("필드 Autowired 시 문제점")
//    void fieldInjectionTest(){
//
//        OrderServiceImpl orderService = new OrderServiceImpl();
//        //orderService의 repository를 바꾸고 싶을때 넣을수 없다
//        //즉 더미데이터를 만들어 넣어주고 싶은데 주입된 객체를 바꿔줄수 없다
//        //OrderServiceImpl 글래스에 setter를 만들어 호출해줘야 한다
//        orderService.setMemberRepository(new MemoryMemberRepository());
//        orderService.setDiscountPolicy(new FixDiscountPolicy());
//
//        orderService.createOrder(1L,"iteamA",10000);
//    }

}
