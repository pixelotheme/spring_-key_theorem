
package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    //인터페이스가 필요하다 (MemberRepository), 구현체도 필요하다 (MemoryMemberRepository)
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //추상화에만 의존하게 생성자를 만들어준다
    private final MemberRepository memberRepository;

    //v1. 생성자를 통해 구현체를 선택시킨다 - 생성자 주입
    //v2. MemberRepository 타입에 맞는 스프링 빈을 찾아와서 주입해준다
    // = ac.getBean(MemberRepository.class) 와 같은 기능을 해준다
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        //단순 인터페이스가 호출되는 것이 아니라 MemoryMemberRepository로 override 한 구현체가 실행된다
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }

}
