package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//애플리케이션 설정을 구현한다
@Configuration // 애플리케이션의 구성정보(설정정보)가 어떻게 구성되어있는지를 담당한다는 뜻의 정보  어노테이션 추가
public class AppConfig {

    // 이전에는 인터페이스에 어떤 객체가 들어갈지 serviceImpl 에서 직접 설정해 줬었다
    //생성자를 통해 구현체를 선택시킨다 - 생성자 주입
    @Bean // 스프링 컨테이너에 등록이 된다
    public MemberService memberService(){

//        return new MemberServiceImpl(new MemoryMemberRepository());
        return new MemberServiceImpl(memberRepository());
    }

    //회원 저장소 역할을 보여준다 - 역할에 따른 구현이 잘보인다
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    //정량 으로 생성자 주입
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    //할인정책 명시적으로 확실히 볼수있다 - 역할에 따른 구현이 잘보인다
    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
