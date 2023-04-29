package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);



    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복오류가 발생한다")
    public void findBeanByTypeDuplicate() throws Exception {
        //타입으로 조회시 같은 타입의 스프링 빈이 둘 이상이면 오류가 발생
        //-> 빈 이름을 지정하자
//        ac.getBean(MemberRepository.class);

        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
        //then
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanbyName(){
        MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);

        org.assertj.core.api.Assertions.assertThat(memberRepository1).isInstanceOf(MemberRepository.class);

    }


    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {

        // Map 으로 반환된다
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key) );
        }

        System.out.println("beansOfType = " + beansOfType);
        //2개가 나온지 확인
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }

    //클래스 안에 클래스를 쓴이유
    // 현재 클래스 안에서만 SameBeanConfig 클래스를 사용하겠다
    @Configuration
    static class SameBeanConfig{
        //서로 파라미터가 다를 경우를 가정하여 진행
        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }
        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }

    }

}
