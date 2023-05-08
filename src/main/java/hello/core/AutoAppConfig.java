package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration // 설정 정보 선언
// 컴포넌트 스캔은 이름 그대로 @Component 애노테이션이 붙은 클래스를
// 스캔해서 스프링 빈으로 등록한다
// - 구현체에 붙여준다 MemoryMemberRepository, RateDiscountPolicy, MemberServiceImpl....
@ComponentScan (
        basePackages = "hello.core.member",
        basePackageClasses = AutoAppConfig.class,
        //컴포넌트 스캔 대상에서 제외 - type은 Annotaion 붙은것을 제외 - @Configuration 이 붙은 대상을 제외시칸다
        //classes = Configuration.class - 미리 만들어둔 AppConfig.class를 제외하기 위함
        // - TestAppConfig 로 만든것도 있다
//        @Configuration 에는 @Component 가 붙어있다 - 중복 에러를 제거하기 위함
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

//    @Autowired
//    MemberRepository memberRepository;
//    @Autowired
//    DiscountPolicy discountPolicy;
//
//
//    //스프링에서만 사용하는 클래스라 가능
//    @Bean
//    OrderService orderService(){
//        //생성자 주입 - 수동으로 넣어줄 인스턴스 지정할때 Autowired 사용 가능
//        return new OrderServiceImpl(memberRepository, discountPolicy);
//    }

    //아무것도 없이 component 만 붙이면 끝인가? 그 대상은 어떻게 지정하나?
    // = @Autowired를 생성자에 붙여준다


    //스프링 빈 충돌 테스트 - 스프링 부트는 빈 중복시 수동 등록 빈이어도 덮어쓰지 않고
    // 에러 발생 시킨다 - BeanDefinitionOverrideException
//    @Bean("memoryMemberRepository")
//    MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }
}
