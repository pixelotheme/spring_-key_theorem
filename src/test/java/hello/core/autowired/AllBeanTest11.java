package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 7.7 조회한 빈이 모두 필요할 때
 * static 제거시 스프링 빈 등록 방법
 * */

public class AllBeanTest11 {


    @Test
    void findAllBean(){
        //DiscountService - 스프링 컨테이너에서 inner class 인스턴스를 빈에 등록하려 할때
        // outer class의 인스턴스가 필요한데( 외부 클래스에 종속적 )
        // 컨테이너에서 관리하는 outer class 빈이 없어 인스턴스 생성 불가 + inner class 생성 불가
        ApplicationContext ac = new AnnotationConfigApplicationContext(AllBeanTest11.class, DiscountService.class);

    }

    //DiscountService 테스트용 클래스 생성 - static이 아닐때 왜 에러가 날까?
    class DiscountService{
        //할인을 선택할수 있게 하기위한 빈 을 담을 Map - key = value, List - value

       private Map<String, DiscountPolicy> policyMap;
       private List<DiscountPolicy> policies;

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
