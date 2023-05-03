package hello.core;

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

    //아무것도 없이 component 만 붙이면 끝인가? 그 대상은 어떻게 지정하나?
    // = @Autowired를 생성자에 붙여준다

}
