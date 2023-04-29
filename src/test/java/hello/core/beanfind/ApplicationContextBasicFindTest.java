package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);

        System.out.println("memberService.getClass = " + memberService.getClass());

        MemberRepository memberRepository = new MemoryMemberRepository();
        MemberService memberService1 = new MemberServiceImpl(memberRepository);



        //memberService 가 MemberServiceImpl 객체(인스턴스)인지?, MemberService 인터페이스의 인스턴스 인지
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
//        Assertions.assertThat(memberService).isInstanceOf(MemberRepository.class); // 실패
    }
    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType(){
        //타입으로 조회시 같은 타입 여러개가 조회되는 경우도 있다
        MemberService memberService = ac.getBean(MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass = " + memberService.getClass());

        //memberService 가 MemberServiceImpl 객체(인스턴스)인지?
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        //AppConfig의 memberService 메소드가 실행되고 반환된 MemberServiceImpl는 스프링 컨테이너에 스프링 빈으로 등록
        // 구체적으로 적으면 좋지는 않다 - 역할과 구현을 분리해야하는데 구현까지 지정한것
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService.getClass = " + memberService.getClass());

        //memberService 가 MemberServiceImpl 객체(인스턴스)인지?, MemberService 인터페이스의 인스턴스 인지?
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX(){
//        ac.getBean("xxxx", MemberService.class);

        //juint 5 로 오면서 Lamda Stream을 사용하여 실패를 검증한다
        //예외 발생시 테스트 성공
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxx", MemberService.class));

    }
}
