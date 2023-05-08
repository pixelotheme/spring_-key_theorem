package hello.core.order;

import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//스프링 DI 기능 없는 순수한 단위 테스트 할경우
class OrderServiceImplTest {

    @Test
    void createOrder(){
        //만약 수정자(setter)주입으로 스프링 빈등록을 했다면 컴파일 시점에 에러가 나지않아
        // 실행해보고 나서야 문제를 알것
        // 그래서 생성자 주입을 선호한다
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService =
                new OrderServiceImpl(memberRepository, new RateDiscountPolicy() );
        //회원가입을 하지 않아 에러 나기는 한다
        Order iteamA = orderService.createOrder(1L, "iteamA", 10000);
        Assertions.assertThat(iteamA.getDiscountPrice()).isEqualTo(1000);

    }
}