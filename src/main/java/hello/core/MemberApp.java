package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        //구현체 선택
//        MemberService memberService = new MemberServiceImpl();

        //직접 생성 하지 않고 생성후 호출만 해주면 -
        // MemoryMemberService 를 구현체로한 memberService 인터페이스를 반환한다
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA); // 저장

        Member findMember = memberService.findMember(1L);

        System.out.println("newMember = " + memberA.getName());
        System.out.println("findMember = " + findMember.getName());

    }
}
