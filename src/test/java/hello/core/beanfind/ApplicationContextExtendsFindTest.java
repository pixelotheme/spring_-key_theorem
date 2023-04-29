package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생")
    void findBeanByParentTypeDuplicate(){
        //given
        //부모타입인 인터페이스를 조회시 자식 까지 나온다
//        ac.getBean(DiscountPolicy.class);
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                () ->  ac.getBean(DiscountPolicy.class));
        //then
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanByParentTypeBeanName(){
        //given
        //타입은 DiscountPolicy 지만 구현객체는 rateDiscountPolicy 로 나온다 - 부모타입을 가져오면 자식까지 가져온다
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);

        org.assertj.core.api.Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(rateDiscountPolicy).isInstanceOf(DiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType(){
        //좋은 방법은 아니지만 특정 하위 클래스로 직접 가져올수도 있다
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);

        for (String Key : beansOfType.keySet()) {
            System.out.println("Key = " + Key + " value = " + beansOfType.get(Key));
        }

    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    void findAllBeanByObjectType(){
        //스프링 자체적으로 등록되는 빈들도 모두 나온다
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);

        for (String Key : beansOfType.keySet()) {
            System.out.println("Key = " + Key + " value = " + beansOfType.get(Key));
        }

    }


    @Configuration
    static class TestConfig{
        //RateDiscountPolicy 지정해도 같은 결과다
        // 다만 역할과 구현을 분리하여 부모객체를 타입으로 선언하여 분리 시킨 것이다
        //다른곳에서 DI 할때 DiscountPolicy 만 의존하면 연결이 가능하니 OCP의 원칙을 지킬수 있다
//        @Bean
//        public RateDiscountPolicy rateDiscountPolicy2(){
//            return new RateDiscountPolicy();
//        }
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }
        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }

}
