package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        //구현체 선택
//        MemberService memberService = new MemberServiceImpl();

        //직접 생성 하지 않고 생성후 호출만 해주면 -
        // MemoryMemberService 를 구현체로한 memberService 인터페이스를 반환한다
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        //spring 생성 방법 - spring은 ApplicationContext(스프링 컨테이너)으로 부터 모든게 시작된다
        // - 모든 객체를 관리해준다 - @Bean 으로 등록된 객체

        // AnnotationConfigApplicationContext 는 어노테이션이 붙은 인스턴스들을 관리한다
        // AppConfig에 모두 어노테이션을 걸어 두었다 - 어노테이션 기반으로 config를 지정해주었다
        // -> 스프링 컨테이너에 객체 생성한것을 다 넣어서 관리해준다 - @Bean 붙은것들
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        //AppConfig 의 bean 으로 등록된 메소드 명을 name 에 적어준다 - 해당 객체를 가져온다, 반환 객체
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA); // 저장

        Member findMember = memberService.findMember(1L);

        System.out.println("newMember = " + memberA.getName());
        System.out.println("findMember = " + findMember.getName());

    }
}
