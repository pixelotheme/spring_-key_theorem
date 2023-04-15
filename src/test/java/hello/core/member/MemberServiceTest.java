package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//테스트 실패시 눈으로 비교하는것이 아니라 어디가 틀렸는지 자세히 나온다
public class MemberServiceTest {

//    MemberService memberService = new MemberServiceImpl();

    MemberService memberService;

    //테스트 실행 이전에 실행된다
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join(){
        //given
        Member member = new Member(1L, "memberA",Grade.VIP);
        //when
        memberService.join(member);
        Member findMember = memberService.findMember(2L);

        //then - 검증
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
