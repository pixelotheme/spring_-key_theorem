package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * 7.7 조회한 빈이 모두 필요할 때
 * */

public class AllBeanTest {

    @Test
    void findAllBean(){
        //DiscountService - static 이 아닐때는 에러가 난다  - 내부클래스 빈 등록 문제

        //DiscountService.class - 현재 스프링 빈으로 등록한 것은 allBeanTest.DiscountService 밖에 없다
        // 실제 DisCountPolicy를 가져와야한다
//        ApplicationContext ac = new AnnotationConfigApplicationContext(DiscountService.class);

        // 클래스 2개를 모두 스캔하여 스프링 컨테이너에 등록
        // AutoAppConfig - fix, rate 가 component scan 대상이라 모두 빈으로 등록
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);

        Member member = new Member(1L, "userA", Grade.VIP);
        //할인율 선택
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        //빈이 잘 나왔나? 확인
        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        //할인 금액확인
        Assertions.assertThat(discountPrice).isEqualTo(1000);

        //rate 로 설정
        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");

        Assertions.assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    //DiscountService 테스트용 클래스 생성 - static이 아닐때 왜 에러가 날까?
    static class DiscountService{
        //할인을 선택할수 있게 하기위한 빈 을 담을 Map - key = value, List - value
       private final Map<String, DiscountPolicy> policyMap;
       private final List<DiscountPolicy> policies;

       @Autowired //- 생성자 하나라 생략 가능
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);

            // 해당 인터페이스의 구현체마다 내부 로직이 변경된다
            return discountPolicy.discount(member, price);
        }
    }

}
