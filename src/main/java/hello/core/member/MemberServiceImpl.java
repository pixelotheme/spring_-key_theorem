package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //인터페이스가 필요하다 (MemberRepository), 구현체도 필요하다 (MemoryMemberRepository)
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        //단순 인터페이스가 호출되는 것이 아니라 MemoryMemberRepository로 override 한 구현체가 실행된다
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
